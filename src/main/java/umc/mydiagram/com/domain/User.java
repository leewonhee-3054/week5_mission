package umc.mydiagram.com.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.mydiagram.com.domain.base.BaseEntity;
import umc.mydiagram.com.domain.enums.Gender;
import umc.mydiagram.com.domain.enums.MemberStatus;
import umc.mydiagram.com.domain.mapping.UserCategory;
import umc.mydiagram.com.domain.mapping.UserMission;
import umc.mydiagram.com.domain.mapping.UserTerm;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    // 워크북을 참고하여 User 필드값을 좀 더 추가하였습니다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String profile_img;

    @Column(nullable = false, length = 40)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private MemberStatus status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserTerm> userTermList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserCategory> userCategoryList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Point> Point_List = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notification notice;
    // 원래 notification 테이블에 user_id를 외래키로 하고 참조를 하였는데,
    // User 테이블에서 notification 테이블을 참조하는 것이 더 좋을거 같아서 바꾸게 되었습니다
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setting_id")
    private User_Setting userSetting;
}
