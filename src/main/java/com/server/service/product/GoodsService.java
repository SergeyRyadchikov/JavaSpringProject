package com.server.service.product;

import com.server.dto.products.GoodsDto;
import com.server.model.product.Goods;
import com.server.repository.product.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GoodsService{

    @Autowired
    private final GoodsRepository goodsRepository;

    public GoodsService(GoodsRepository goodsRepository) {

        this.goodsRepository = goodsRepository;

    }

    public Goods create(GoodsDto goodsDto) {

        Goods goods = new Goods();
        goods.setProductName(goodsDto.productName());
        goods.setCategory(goodsDto.category());
        goods.setAmount(goodsDto.amount());

        goodsRepository.save(goods);

        return goods;
    }

    public List<Goods> readAll() {
        return goodsRepository.findAll();
    }

    public Goods read(Integer id) {
        return goodsRepository.getReferenceById(id);
    }

    public Goods update(GoodsDto goodsDto, Integer id) {

        if (goodsRepository.existsById(id)) {

            Goods goods = new Goods();
            goods.setId(id);
            goods.setProductName(goodsDto.productName());
            goods.setCategory(goodsDto.category());
            goods.setAmount(goodsDto.amount());

            goodsRepository.save(goods);

            return goods;

        } else {

            return null;

        }
    }

    public boolean delete(Integer id) {

        if (goodsRepository.existsById(id)) {

            goodsRepository.deleteById(id);

            return true;

        } else {

            return false;

        }
    }
}
