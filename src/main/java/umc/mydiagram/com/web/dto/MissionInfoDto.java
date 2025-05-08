package umc.mydiagram.com.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionInfoDto {
    private String    storeName;
    private String    storeAddress;
    private String    regionName;
    private String    title;          // 미션 제목
    private String    description;    // 미션 설명
    private int     points;         // 적립 포인트
    private LocalDateTime deadline;       // 마감일
    private Integer   daysRemaining;  // 남은 일수
}
