package umc.mydiagram.com.service.UserMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.mydiagram.com.domain.enums.MissionStatus;
import umc.mydiagram.com.domain.mapping.UserMission;
import umc.mydiagram.com.repository.UserMissionRepository.UserMissionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionQueryServiceImpl implements UserMissionQueryService {

    private final UserMissionRepository userMissionRepository;

    @Override
    public Optional<UserMission> findUserMissions(Long userId, Pageable pageable) {
        return userMissionRepository.findById(userId);
    }

    @Override
    public Page<UserMission> findUserMissionsByStatuses(
            Long userId,
            List<MissionStatus> statuses,
            Pageable pageable
    ) {
        return userMissionRepository.findByUserIdAndStatuses(userId, statuses, pageable);
    }
}