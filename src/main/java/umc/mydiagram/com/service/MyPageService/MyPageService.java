package umc.mydiagram.com.service.MyPageService;

import umc.mydiagram.com.web.dto.MemberInfoDto;

public interface MyPageService {
    MemberInfoDto getMemberInfo(Long currentMemberId);
}
