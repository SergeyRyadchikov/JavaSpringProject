package com.server.service.purchase;

import com.server.entity.purchase.Purchase;
import com.server.entity.product.Goods;
import com.server.entity.product.Services;
import com.server.entity.user.Role;
import com.server.repository.purchase.PurchaseRepository;
import com.server.service.product.GoodsService;
import com.server.service.product.ServiceForServices;
import com.server.service.user.apiUserService.ApiUsersService;
import com.server.service.user.clientsService.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final ClientService clientService;

    private final ApiUsersService apiUsersService;

    private final GoodsService goodsService;

    private final ServiceForServices serviceForServices;

    private final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository,
                           ClientService clientService,
                           ApiUsersService apiUsersService,
                           GoodsService goodsService,
                           ServiceForServices serviceForServices) {
        this.purchaseRepository = purchaseRepository;
        this.clientService = clientService;
        this.apiUsersService = apiUsersService;
        this.goodsService = goodsService;
        this.serviceForServices = serviceForServices;
    }


    public Purchase create(int[] goodsId, int[] servicesId) {

        return this.purchaseCreater(goodsId, servicesId, authentication.getName());

    }

    public Purchase manualCreate(int[] goodsId, int[] servicesId, String phone) {

            return this.purchaseCreater(goodsId, servicesId, phone);

    }


    public List<Purchase> readAll() {

        String phone = authentication.getName();

        Role role = apiUsersService.findApiUsersByPhone(phone).getRole();

        if(role == Role.ADMIN){

            return purchaseRepository.findAll();

        } else {

            return purchaseRepository.findAllByClient(clientService.findByPhone(phone));

        }

    }

    public Purchase readId(int id) {

        return purchaseRepository.getReferenceById(id);

    }

    public Purchase update(int id, int[] goodsId, int[] servicesId) {

        if (purchaseRepository.existsById(id)) {

            Purchase purchase = purchaseRepository.getReferenceById(id);
            purchase.setGoodsList(this.addGoodsInPurchase(goodsId));
            purchase.setServicesList(this.addServicesInPurchase(servicesId));

            purchaseRepository.save(purchase);

            return purchase;

        } else {

            return null;

        }

    }

    public boolean delete(Integer id) {
        if (purchaseRepository.existsById(id)) {
            purchaseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private List<Goods> addGoodsInPurchase(int[] listId){

        List<Goods> goodsList;

        if(listId.length != 0){

            goodsList = new ArrayList<>();

            for (int j : listId) {
                goodsList.add(goodsService.readId(j));
            }

            return goodsList;

        } else {

            return null;

        }

    }

    private List<Services> addServicesInPurchase(int[] listId){

        List<Services> servicesList;

        if(listId.length != 0){

            servicesList = new ArrayList<>();

            for (int j : listId) {
                servicesList.add(serviceForServices.readId(j));
            }

            return servicesList;

        } else {

            return null;

        }

    }

    private Purchase purchaseCreater(int[] goodsId, int[] servicesId, String phoneNumber){

        List<Services> servicesList = this.addServicesInPurchase(servicesId);

        List<Goods> goodsList = this.addGoodsInPurchase(goodsId);


        if (servicesList != null && goodsList != null){

            Purchase purchase = new Purchase(
                    goodsList,
                    servicesList,
                    clientService.findByPhone(phoneNumber)
            );

            purchaseRepository.save(purchase);

            return purchase;

        } else if (goodsList != null){

            Purchase purchase = new Purchase(
                    goodsList,
                    clientService.findByPhone(phoneNumber)
            );

            purchaseRepository.save(purchase);

            return purchase;

        } else {

            return null;

        }

    }

}
