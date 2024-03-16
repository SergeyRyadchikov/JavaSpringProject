package com.server.controller.rest.user;

import com.server.dto.user.ModeratorDto;
import com.server.dto.user.UserRegistrationDto;
import com.server.entity.user.Moderator;
import com.server.service.user.userServiceFacade.ModeratorServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RestModeratorController implements IModeratorController{

    private final ModeratorServiceFacade moderatorService;

    @Autowired
    public RestModeratorController(ModeratorServiceFacade moderatorService) {
        this.moderatorService = moderatorService;
    }


    @Override
    public ResponseEntity<?> create(UserRegistrationDto userRegistrationDto) {

        Moderator moderator = moderatorService.create(userRegistrationDto);

        return new ResponseEntity<>(moderator, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<List<Moderator>> readAll() {

        final List<Moderator> moderators = moderatorService.readAll();

        return moderators != null &&  !moderators.isEmpty()
                ? new ResponseEntity<>(moderators, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @Override
    public ResponseEntity<Moderator> readId(int id) {

        final Moderator moderator = moderatorService.read(id);

        return moderator != null
                ? new ResponseEntity<>(moderator, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<?> update(int id, ModeratorDto moderatorDto) {

        final Moderator moderator = moderatorService.update(moderatorDto, id);

        return moderator != null
                ? new ResponseEntity<>(moderator, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @Override
    public ResponseEntity<?> delete(int id) {

        final boolean deleted = moderatorService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @Override
    public ResponseEntity<Moderator> filterByPhone(String phone) {

        final Moderator moderator = moderatorService.findByPhone(phone);

        return moderator != null
                ? new ResponseEntity<>(moderator, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
