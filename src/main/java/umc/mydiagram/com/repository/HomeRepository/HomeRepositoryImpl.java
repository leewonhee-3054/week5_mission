package umc.mydiagram.com.repository.HomeRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.mydiagram.com.domain.QMission;
import umc.mydiagram.com.domain.QRegion;
import umc.mydiagram.com.domain.QStore;
import umc.mydiagram.com.domain.mapping.QUserMission;
import umc.mydiagram.com.web.dto.MissionInfoDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.querydsl.core.types.dsl.DateTimeExpression.currentDate;

@Repository
@RequiredArgsConstructor
public class HomeRepositoryImpl implements HomeRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;
    private final QRegion region = QRegion.region;
    private final QUserMission userMission = QUserMission.userMission;

    @Override
    public Page<MissionInfoDto> findAvailableMissions(
            Long currentMemberId,
            Long selectedRegionId,
            Pageable pageable
    ) {
        // 남은 일수 계산 (MySQL datediff)

        NumberExpression<Integer> daysRemaining =
                Expressions.numberTemplate(
                        Integer.class,
                        "datediff({0}, {1})",
                        mission.deadline,
                        Expressions.constant(LocalDate.now())
                );

        // 동적 WHERE
        BooleanBuilder builder = new BooleanBuilder()
                // 1) 선택된 지역
                .and(store.region.id.eq(selectedRegionId))
                // 2) 마감일이 아직 안 지난 것
                .and(mission.deadline.goe(LocalDateTime.now()))
                // 3) 아직 도전하지 않은 미션
                .and(mission.id.notIn(
                        JPAExpressions
                                .select(userMission.mission.id)
                                .from(userMission)
                                .where(userMission.user.id.eq(currentMemberId))
                ));

        // 1) content 조회
        List<MissionInfoDto> content = queryFactory
                .select(com.querydsl.core.types.Projections.fields(
                        MissionInfoDto.class,
                        store.storeName.as("storeName"),
                        store.address.as("storeAddress"),
                        region.name.as("regionName"),
                        mission.title,
                        mission.description,
                        mission.points.as("points"),
                        mission.deadline,
                        daysRemaining.as("daysRemaining")
                ))
                .from(mission)
                .innerJoin(mission.store, store)
                .innerJoin(store.region, region)
                .where(builder)
                .orderBy(daysRemaining.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 2) total count
        Long total = queryFactory
                .select(mission.count())
                .from(mission)
                .innerJoin(mission.store, store)
                .innerJoin(store.region, region)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0L);
    }
}
