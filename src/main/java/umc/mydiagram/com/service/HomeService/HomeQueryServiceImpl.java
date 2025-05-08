package umc.mydiagram.com.service.HomeService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.mydiagram.com.repository.HomeRepository.HomeRepository;
import umc.mydiagram.com.web.dto.MissionInfoDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeQueryServiceImpl implements HomeQueryService {

    private final HomeRepository missionRepository;

    @Override
    public Page<MissionInfoDto> getAvailableMissions(
            Long currentMemberId,
            Long selectedRegionId,
            Pageable pageable
    ) {
        return missionRepository
                .findAvailableMissions(currentMemberId, selectedRegionId, pageable);
    }
}

