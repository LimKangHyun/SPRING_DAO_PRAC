package kr.ac.hnu.dao.dao.jpa;

import kr.ac.hnu.dao.global.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DataJpaOrderItemRepository extends JpaRepository<OrderItems, Long> {

    // PK가 아닌 UNIQUE키로 데이터를 조회하고 싶을때, 쿼리 메서드 사용
    @Query("select oi from OrderItems oi where oi.orders.orderCode = :orderCode")
    List<OrderItems> findAllByOrderCode(String orderCode);
}
