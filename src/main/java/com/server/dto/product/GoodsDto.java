package com.server.dto.product;

import com.server.model.product.CategoryGoods;

public record GoodsDto(
        String productName,
        CategoryGoods category,
        int amount

) {
}
