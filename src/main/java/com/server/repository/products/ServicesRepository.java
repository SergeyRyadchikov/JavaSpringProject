package com.server.repository.products;

import com.server.model.products.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository  extends JpaRepository<Services, Integer> {
}
