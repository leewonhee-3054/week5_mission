package umc.mydiagram.com.repository.UserMissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.mydiagram.com.domain.QMission;
import umc.mydiagram.com.domain.QStore;
import umc.mydiagram.com.domain.enums.MissionStatus;
import umc.mydiagram.com.domain.mapping.QUserMission;
import umc.mydiagram.com.domain.mapping.UserMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserMissionRepositoryImpl implements UserMissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QUserMission userMission = QUserMission.userMission;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;

    @Override
    public Page<UserMission> findByUserIdAndStatuses(Long userId, List<MissionStatus> statuses, Pageable pageable) {
        // 1) 데이터 조회 (fetchJoin 으로 mission, store 등 연관 엔티티도 미리 가져올 수 있습니다)

        BooleanBuilder builder = new BooleanBuilder()
                .and(userMission.user.id.eq(userId))
                .and(userMission.status.in(statuses));

        List<UserMission> content = queryFactory
                .selectFrom(userMission)
                .innerJoin(userMission.mission, mission).fetchJoin()
                .innerJoin(mission.store, store).fetchJoin()
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 2) 전체 카운트 조회
        Long total = queryFactory
                .select(userMission.count())
                .from(userMission)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0L);
    }
}
