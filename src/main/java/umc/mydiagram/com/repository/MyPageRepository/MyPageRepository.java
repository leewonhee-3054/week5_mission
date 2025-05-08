package umc.mydiagram.com.repository.MyPageRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mydiagram.com.domain.User;

public interface MyPageRepository
        extends JpaRepository<User,Long>, MyPageRepositoryCustom {
}
