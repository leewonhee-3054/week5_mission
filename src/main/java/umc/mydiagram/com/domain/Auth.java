package umc.mydiagram.com.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.mydiagram.com.domain.base.BaseEntity;
import umc.mydiagram.com.domain.enums.AuthType;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Auth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthType authType;

    private String provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
