package com.server.service;


import com.server.model.Moderator;
import com.server.repository.ModeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModeratorServiceImpl implements AppService<Moderator, Integer> {

    @Autowired
    private ModeratorRepository moderatorRepository;


    @Override
    public void create(Moderator moderator) {
        moderatorRepository.save(moderator);
    }

    @Override
    public List<Moderator> readAll() {
        return moderatorRepository.findAll();
    }

    @Override
    public Moderator read(Integer id) {
        return (Moderator) moderatorRepository.getReferenceById(id);
    }

    @Override
    public boolean update(Moderator moderator, Integer id) {
        if (moderatorRepository.existsById(id)) {
            moderator.setId(id);
            moderatorRepository.save(moderator);
            return true;
        } else {
            return false;
        }

    }

    @Override
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
        }
        else{
            return null;
        }
    }
}
