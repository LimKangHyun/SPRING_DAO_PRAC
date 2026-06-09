package kr.ac.hnu.dao.dao.jpa;

import kr.ac.hnu.dao.global.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DataJpaOrderRepository extends JpaRepository<Orders, Long> {

    @Query("select o from Orders o where o.orderCode = :orderCode")
    Optional<Orders> findByOrderCode(String orderCode);
}
