package umc.mydiagram.com.service.MyPageService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.mydiagram.com.repository.MyPageRepository.MyPageRepository;
import umc.mydiagram.com.web.dto.MemberInfoDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageServiceImpl implements MyPageService {

    private final MyPageRepository myPageRepository;

    @Override
    public MemberInfoDto getMemberInfo(Long currentMemberId) {
        return myPageRepository.findMemberInfo(currentMemberId);
    }
}