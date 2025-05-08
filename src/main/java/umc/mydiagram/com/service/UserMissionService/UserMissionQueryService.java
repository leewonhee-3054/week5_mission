package umc.mydiagram.com.service.UserMissionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.mydiagram.com.domain.enums.MissionStatus;
import umc.mydiagram.com.domain.mapping.UserMission;

import java.util.List;
import java.util.Optional;

public interface UserMissionQueryService {

    Optional<UserMission> findUserMissions(Long userId, Pageable pageable);

    /**
     * 원하는 상태 리스트로 조회
     */
    Page<UserMission> findUserMissionsByStatuses(
            Long userId,
            List<MissionStatus> statuses,
            Pageable pageable
    );
}