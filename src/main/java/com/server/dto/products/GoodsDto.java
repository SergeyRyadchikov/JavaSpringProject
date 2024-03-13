package com.server.dto.products;

import com.server.model.product.CategoryGoods;

public record GoodsDto(
        String productName,
        CategoryGoods category,
        int amount

) {
}
