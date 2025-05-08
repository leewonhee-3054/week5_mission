package umc.mydiagram.com.repository.HomeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mydiagram.com.domain.Mission;

public interface HomeRepository
        extends JpaRepository<Mission,Long>, HomeRepositoryCustom {
}
