package com.server.repository.products;

import com.server.model.products.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
}
