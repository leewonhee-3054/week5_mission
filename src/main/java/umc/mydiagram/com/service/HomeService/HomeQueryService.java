package umc.mydiagram.com.service.HomeService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.mydiagram.com.web.dto.MissionInfoDto;

public interface HomeQueryService {
    Page<MissionInfoDto> getAvailableMissions(
            Long currentMemberId,
            Long selectedRegionId,
            Pageable pageable
    );
}
