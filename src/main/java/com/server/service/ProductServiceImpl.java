package com.server.service;

import com.server.model.Product;
import com.server.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements AppService<Product, Integer> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void create(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> readAll() {
        return productRepository.findAll();
    }

    @Override
    public Product read(Integer id) {
        return (Product) productRepository.getReferenceById(id);
    }

    @Override
    public boolean update(Product product, Integer id) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            productRepository.save(product);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean delete(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
