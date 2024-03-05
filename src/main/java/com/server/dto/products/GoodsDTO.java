package com.server.dto.products;

import com.server.model.products.CategoryGoods;

public record GoodsDTO(
        String productName,
        CategoryGoods category,
        int amount

) {
}
