package umc.mydiagram.com.repository.UserMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.mydiagram.com.domain.enums.MissionStatus;
import umc.mydiagram.com.domain.mapping.UserMission;

import java.util.List;

public interface UserMissionRepositoryCustom {
    /**
     * 특정 사용자(userId)에 대해 상태(statuses)에 포함된
     * UserMission을 페이지 단위로 조회
     */
    Page<UserMission> findByUserIdAndStatuses(Long userId, List<MissionStatus> statuses, Pageable pageable);
}
