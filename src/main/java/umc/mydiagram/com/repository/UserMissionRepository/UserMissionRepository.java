package umc.mydiagram.com.repository.UserMissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mydiagram.com.domain.mapping.UserMission;

public interface UserMissionRepository
        extends JpaRepository<UserMission, Long>, UserMissionRepositoryCustom {
}