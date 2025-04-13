package kr.ac.hnu.dao.global.entity;

import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

public class Items {

    private Long id;
    private String name;
    private String itemCode;

    @Setter
    private Integer price;

    private LocalDateTime createdAt;

    @Builder
    public Items(String name, Integer price, LocalDateTime createdAt) {
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return Objects.equals(id, items.id) && Objects.equals(name, items.name) && Objects.equals(itemCode, items.itemCode) && Objects.equals(price, items.price) && Objects.equals(createdAt, items.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, itemCode, price, createdAt);
    }
}
