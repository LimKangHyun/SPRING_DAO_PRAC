package kr.ac.hnu.dao.dao.jpa;

import kr.ac.hnu.dao.global.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DataJpaOrderRepository extends JpaRepository<Orders, Long> {

    // Orders 엔티티 중에서 orderCode가 입력값과 같은 데이터를 찾아서, 그 객체(Orders)를 리턴
    @Query("select o from Orders o where o.orderCode = :orderCode")
    Optional<Orders> findByOrderCode(String orderCode);
}
