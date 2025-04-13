package kr.ac.hnu.dao.dao.mybatis;

import kr.ac.hnu.dao.global.entity.Items;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository {

    private final ItemMapper mapper;

    public Items save(Items items) {
        mapper.save(items);
        return items;
    }

    public void updatePrice(String itemCode, Integer price) {
        mapper.update(itemCode, price);
    }

    public Optional<Items> findByItemCode(String itemCode) {
        return mapper.findByItemCode(itemCode);
    }

    public void delete(Items items) {
        mapper.delete(items);
    }
}
