package com.server.service.product;

import com.server.dto.products.ServicesDto;
import com.server.model.product.Services;
import com.server.repository.product.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceForServices {

    @Autowired
    private final ServicesRepository servicesRepository;


    public ServiceForServices(ServicesRepository servicesRepository) {

        this.servicesRepository = servicesRepository;

    }


    public Services create(ServicesDto servicesDto) {

        Services services = new Services();
        services.setProductName(servicesDto.productName());
        services.setAmount(servicesDto.amount());

        servicesRepository.save(services);

        return services;

    }


    public List<Services> readAll() {

        return servicesRepository.findAll();

    }


    public Services read(Integer id) {

        return servicesRepository.getReferenceById(id);

    }


    public Services update(ServicesDto servicesDto, Integer id) {

        if (servicesRepository.existsById(id)) {

            Services services = new Services();
            services.setId(id);
            services.setProductName(servicesDto.productName());
            services.setAmount(servicesDto.amount());

            servicesRepository.save(services);

            return services;

        } else {

            return null;

        }

    }


    public boolean delete(Integer id) {

        if (servicesRepository.existsById(id)) {

            servicesRepository.deleteById(id);

            return true;

        } else {

            return false;

        }

    }

}
