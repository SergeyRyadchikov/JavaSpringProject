package com.server.service.user.moderatorService;


import com.server.dto.user.ModeratorDto;
import com.server.model.user.Moderator;
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

    public Moderator read(Integer id) {

        return (Moderator) moderatorRepository.getReferenceById(id);

    }

    public Moderator update(ModeratorDto moderatorDto, Integer id) {

        if (moderatorRepository.existsById(id)) {

            Moderator moderator = moderatorRepository.getReferenceById(id);

            if (!moderatorDto.name().isBlank()){

                moderator.setName(moderatorDto.name());

            }

            if (!moderatorDto.phone().isBlank()){

                moderator.setPhone(moderatorDto.phone());

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

    public Moderator findByPhone(String phone){

        Moderator moderator = moderatorRepository.findByPhone(phone);

        if (moderator != null){

            return moderator;

        } else {

            return null;

        }

    }

}
