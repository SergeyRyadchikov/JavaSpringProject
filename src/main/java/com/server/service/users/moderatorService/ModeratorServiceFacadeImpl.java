package com.server.service.users.moderatorService;

import com.server.model.users.ApiUsers;
import com.server.model.users.Moderator;
import com.server.service.users.ApiUsersServiceImpl;
import com.server.service.users.UserServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModeratorServiceFacadeImpl implements UserServiceFacade <Moderator, ApiUsers, Integer> {

//    public static ModeratorServiceFacadeImpl instance;


    private final ApiUsersServiceImpl apiUsersService;

    private final ModeratorServiceImpl moderatorService;

    @Autowired
    public ModeratorServiceFacadeImpl(ApiUsersServiceImpl apiUsersService, ModeratorServiceImpl moderatorService) {
        this.apiUsersService = apiUsersService;
        this.moderatorService = moderatorService;
    }


//    public static ModeratorServiceFacadeImpl getInstance() {
//        if (ModeratorServiceFacadeImpl.instance == null){
//            return new ModeratorServiceFacadeImpl(new ApiUsersServiceImpl(), new ModeratorServiceImpl());
//        } else {
//            return instance;
//        }

//    }

    @Override
    public void create(Moderator moderator, ApiUsers apiUsers) {
        moderatorService.create(moderator);
        apiUsersService.create(apiUsers);
    }

    @Override
    public List<Moderator> readAll() {
        return moderatorService.readAll();
    }

    @Override
    public Moderator read(Integer id) {
        return moderatorService.read(id);
    }

    @Override
    public boolean update(Moderator moderator, Integer id) {
        return moderatorService.update(moderator, id);
    }

    @Override
    public boolean delete(Integer id) {
        return moderatorService.delete(id);
    }


}
