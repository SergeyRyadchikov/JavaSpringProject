package com.server.service.user.moderatorService;


import com.server.dto.user.ModeratorDto;
import com.server.entity.user.ApiUsers;
import com.server.entity.user.Moderator;
import com.server.repository.user.ModeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModeratorService{

    @Autowired
    private ModeratorRepository moderatorRepository;



    public void create(Moderator moderator) {

        moderatorRepository.save(moderator);

    }

    public List<Moderator> readAll() {

        return moderatorRepository.findAll();

    }

    public Moderator readId(Integer id) {

        return moderatorRepository.getReferenceById(id);

    }

    public Moderator update(ModeratorDto moderatorDto, Integer id) {

        if (moderatorRepository.existsById(id)) {

            Moderator moderator = moderatorRepository.getReferenceById(id);

            if (!moderatorDto.name().isBlank()){

                moderator.setName(moderatorDto.name());

            }

            moderatorRepository.save(moderator);

            return moderator;

        } else {

            return null;

        }

    }

    public boolean delete(Integer id) {

        if (moderatorRepository.existsById(id)) {

            moderatorRepository.deleteById(id);

            return true;

        } else {

            return false;

        }

    }

    public Moderator findByApiUsers(ApiUsers apiUsers){

        return moderatorRepository.findModeratorByApiUsers(apiUsers);

    }

}
