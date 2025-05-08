package umc.mydiagram.com.repository.HomeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.mydiagram.com.web.dto.MissionInfoDto;

public interface HomeRepositoryCustom {
    /**
     * 현재 userId가 아직 도전하지 않은,
     * selectedRegionId 지역 내 마감일이 지나지 않은 미션을
     * daysRemaining 오름차순으로 페이징 조회
     */
    Page<MissionInfoDto> findAvailableMissions(
            Long currentMemberId,
            Long selectedRegionId,
            Pageable pageable
    );
}
