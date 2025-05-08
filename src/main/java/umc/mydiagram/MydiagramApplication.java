package umc.mydiagram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.mydiagram.com.domain.enums.MissionStatus;
import umc.mydiagram.com.domain.mapping.UserMission;
import umc.mydiagram.com.service.HomeService.HomeQueryService;
import umc.mydiagram.com.service.MyPageService.MyPageService;
import umc.mydiagram.com.service.UserMissionService.UserMissionQueryService;
import umc.mydiagram.com.web.dto.MemberInfoDto;
import umc.mydiagram.com.web.dto.MissionInfoDto;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class MydiagramApplication {

	public static void main(String[] args) {
		SpringApplication.run(MydiagramApplication.class, args);
	}

	@Bean
	public CommandLineRunner logUserMissionsByStatuses(UserMissionQueryService missionService) {
		return args -> {
			Long userId = 1L;
			// 페이지 요청: 0페이지, 크기 20, modifiedDate 내림차순
			PageRequest pageReq = PageRequest.of(
					0,
					20,
					Sort.by(Sort.Direction.DESC, "modifiedDate")
			);

			// 조회할 상태 리스트: 진행중과 완료
			List<MissionStatus> statuses = List.of(
					MissionStatus.CHALLENGING,
					MissionStatus.COMPLETE
			);

			log.info(">>> findUserMissionsByStatuses 호출: userId={}, statuses={}, page={}",
					userId, statuses, pageReq);

			Page<UserMission> page = missionService
					.findUserMissionsByStatuses(userId, statuses, pageReq);

			log.info("총 조회 건수: {}", page.getTotalElements());
			page.getContent().forEach(um -> {
				log.info("  • id={}, status={}, title={}",
						um.getId(),
						um.getStatus(),
						um.getMission().getTitle()
				);
			});
		};
	}

	@Bean
	public CommandLineRunner runHomeQuery(HomeQueryService homeQueryService) {
		return args -> {
			// 1) 파라미터 설정
			Long currentUserId    = 1L; // 로그인한 사용자 ID
			Long selectedRegionId = 2L; // 선택된 Region ID
			PageRequest pageReq   = PageRequest.of(
					0,  // 페이지 번호
					10,  // 페이지 크기
					Sort.by("daysRemaining").ascending()  // daysRemaining 오름차순
			);

			// 2) 서비스 호출
			Page<MissionInfoDto> page = homeQueryService
					.getAvailableMissions(currentUserId, selectedRegionId, pageReq);

			// 3) 결과 출력
			log.info("=== 홈 화면 도전 가능 미션 조회 결과 ===");
			log.info("총 건수: {}", page.getTotalElements());
			page.getContent().forEach(dto ->
					log.info(dto.toString())
			);
		};
	}

	@Bean
	public CommandLineRunner runMyPageQuery(MyPageService myPageService) {
		return args -> {
			Long currentUserId = 1L;  // 확인할 회원 ID

			log.info("=== My Page 조회 테스트 START ===");
			MemberInfoDto info = myPageService.getMemberInfo(currentUserId);
			log.info("회원 정보: {}", info);
			log.info("=== My Page 조회 테스트 END ===");
		};
	}

}
