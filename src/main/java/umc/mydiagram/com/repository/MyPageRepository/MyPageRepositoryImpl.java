package umc.mydiagram.com.repository.MyPageRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.mydiagram.com.domain.QUser;
import umc.mydiagram.com.domain.QUser_Setting;
import umc.mydiagram.com.web.dto.MemberInfoDto;

@Repository
@RequiredArgsConstructor
public class MyPageRepositoryImpl implements MyPageRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QUser qUser = QUser.user;
    private final QUser_Setting qUser_setting = QUser_Setting.user_Setting;

    @Override
    public MemberInfoDto findMemberInfo(Long currentMemberId) {

        BooleanBuilder builder = new BooleanBuilder();
        if (currentMemberId != null) {
            builder.and(qUser.id.eq(currentMemberId));
        }

        return queryFactory
                .select(Projections.fields(
                        MemberInfoDto.class,
                        qUser.username.as("name"),
                        qUser.email.as("email"),
                        qUser.address.as("address"),
                        qUser_setting.all_points.as("point")
                ))
                .from(qUser)
                .leftJoin(qUser.userSetting, qUser_setting)
                .where(builder)
                .fetchOne();
    }
}
