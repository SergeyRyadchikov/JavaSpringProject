package com.server.service.products;

import com.server.model.products.Goods;
import com.server.repository.products.GoodsRepository;
import com.server.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GoodsServiceImpl implements AppService<Goods, Integer> {

    @Autowired
    private final GoodsRepository goodsRepository;

    public GoodsServiceImpl(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Override
    public void create(Goods goods) {
        goodsRepository.save(goods);
    }

    @Override
    public List<Goods> readAll() {
        return goodsRepository.findAll();
    }

    @Override
    public Goods read(Integer id) {
        return goodsRepository.getReferenceById(id);
    }

    @Override
    public boolean update(Goods goods, Integer id) {
        if (goodsRepository.existsById(id)) {
            goods.setId(id);
            goodsRepository.save(goods);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        if (goodsRepository.existsById(id)) {
            goodsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
