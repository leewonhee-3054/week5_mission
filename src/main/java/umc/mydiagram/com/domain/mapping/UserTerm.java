package umc.mydiagram.com.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.mydiagram.com.domain.Terms;
import umc.mydiagram.com.domain.User;
import umc.mydiagram.com.domain.base.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserTerm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id")
    private Terms terms;
}