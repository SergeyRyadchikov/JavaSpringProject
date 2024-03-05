package com.server.controller.restControllers.users;

import com.server.dto.users.ModeratorDTO;
import com.server.dto.users.ModeratorRegDTO;
import com.server.model.users.ApiUsers;
import com.server.model.users.Moderator;
import com.server.model.users.Role;
import com.server.service.users.moderatorService.ModeratorServiceFacadeImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Tag(
        name = "Администраторы",
        description = "Все методы для работы с администраторами системы"
)
public class RestModeratorController {

    private final ModeratorServiceFacadeImpl moderatorService;

    @Autowired
    public RestModeratorController(ModeratorServiceFacadeImpl moderatorService) {
        this.moderatorService = moderatorService;
    }


    @RequestMapping(value = "/new-moderator", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ModeratorRegDTO moderatorRegDTO) {

        ApiUsers apiUser = new ApiUsers();
        apiUser.setPhone(moderatorRegDTO.phone());
        apiUser.setRole(Role.ADMIN);
        apiUser.setPassword(moderatorRegDTO.password());

        Moderator moderator = new Moderator();
        moderator.setName(moderatorRegDTO.name());
        moderator.setPhone(moderatorRegDTO.phone());

        moderatorService.create(moderator, apiUser);

        return new ResponseEntity<>(moderator, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/moderators", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Moderator>> read() {
        final List<Moderator> moderators = moderatorService.readAll();

        return moderators != null &&  !moderators.isEmpty()
                ? new ResponseEntity<>(moderators, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/moserators/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Moderator> read(@PathVariable(name = "id") int id) {
        final Moderator moderator = moderatorService.read(id);

        return moderator != null
                ? new ResponseEntity<>(moderator, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/moderators/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(
            @PathVariable(name = "id") int id,
            @RequestBody ModeratorDTO moderatorDTO)
    {
        Moderator moderator = new Moderator();
        moderator.setName(moderatorDTO.name());
        moderator.setPhone(moderatorDTO.phone());

        final boolean updated = moderatorService.update(moderator, id);

        return updated
                ? new ResponseEntity<>(moderator, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(value = "/moderators/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = moderatorService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
