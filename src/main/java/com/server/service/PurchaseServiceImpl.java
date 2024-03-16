package com.server.service;

import com.server.config.UserDetailsConfig;
import com.server.model.Purchase;
import com.server.repository.PurchaseRepository;
import com.server.service.user.userDetailService.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.List;

@Service
public class PurchaseServiceImpl implements AppService<Purchase, Integer> {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public void create(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> readAll() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        System.out.println(STR."Пользователь: \{securityContext.getAuthentication().getName()}");
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase read(Integer id) {
        return (Purchase) purchaseRepository.getReferenceById(id);
    }

    @Override
    public boolean update(Purchase purchase, Integer id) {
        if (purchaseRepository.existsById(id)) {
            purchase.setId(id);
            purchaseRepository.save(purchase);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean delete(Integer id) {
        if (purchaseRepository.existsById(id)) {
            purchaseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
