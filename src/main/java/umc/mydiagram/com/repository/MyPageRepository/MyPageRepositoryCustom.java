package umc.mydiagram.com.repository.MyPageRepository;

import umc.mydiagram.com.web.dto.MemberInfoDto;

public interface MyPageRepositoryCustom {
    MemberInfoDto findMemberInfo(Long currentMemberId);
}
