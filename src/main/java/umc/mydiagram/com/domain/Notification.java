package umc.mydiagram.com.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.mydiagram.com.domain.base.BaseEntity;
import umc.mydiagram.com.domain.enums.NoticeType;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NoticeType noticeType;

    private String message;

    private Boolean is_read;

}
