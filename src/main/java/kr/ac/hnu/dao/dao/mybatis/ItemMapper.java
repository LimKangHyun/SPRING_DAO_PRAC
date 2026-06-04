package kr.ac.hnu.dao.dao.mybatis;

import kr.ac.hnu.dao.global.entity.Items;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface ItemMapper {
    void save(Items items);
    int update(@Param("itemCode") String itemCode, @Param("price") Integer price);
    Optional<Items> findByItemCode(@Param("itemCode") String itemCode);
    void delete(Items items);

    List<Items> findAll();
}
