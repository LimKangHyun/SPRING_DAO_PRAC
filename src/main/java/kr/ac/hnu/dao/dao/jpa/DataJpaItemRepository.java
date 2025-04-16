package kr.ac.hnu.dao.dao.jpa;

import kr.ac.hnu.dao.global.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DataJpaItemRepository extends JpaRepository<Items, Long> {
    Optional<Items> findByItemCode(String itemCode);
}
