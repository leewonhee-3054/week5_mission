package umc.mydiagram.com.repository.StoreRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mydiagram.com.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
