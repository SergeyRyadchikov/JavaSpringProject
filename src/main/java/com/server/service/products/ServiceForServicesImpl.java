package com.server.service.products;

import com.server.model.products.Services;
import com.server.repository.products.ServicesRepository;
import com.server.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceForServicesImpl implements AppService<Services, Integer> {

    @Autowired
    private final ServicesRepository servicesRepository;

    public ServiceForServicesImpl(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    @Override
    public void create(Services services) {
        servicesRepository.save(services);
    }

    @Override
    public List<Services> readAll() {
        return servicesRepository.findAll();
    }

    @Override
    public Services read(Integer id) {
        return servicesRepository.getReferenceById(id);
    }

    @Override
    public boolean update(Services services, Integer id) {
        if (servicesRepository.existsById(id)) {
            services.setId(id);
            servicesRepository.save(services);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        if (servicesRepository.existsById(id)) {
            servicesRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
