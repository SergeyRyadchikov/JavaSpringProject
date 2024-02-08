package com.server.controller.restControllers;

import com.server.model.ApiUsers;
import com.server.model.Client;
import com.server.model.Moderator;
import com.server.model.Role;
import com.server.service.ApiUsersService;
import com.server.service.ModeratorServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/moderators")
@Tag(
        name = "Администраторы",
        description = "Все методы для работы с администраторами системы"
)
public class RestModeratorController {

    private final ModeratorServiceImpl moderatorService;

    private final ApiUsersService apiUsersService;

    @Autowired
    public RestModeratorController(ModeratorServiceImpl moderatorService, ApiUsersService apiUsersService) {
        this.moderatorService = moderatorService;
        this.apiUsersService = apiUsersService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Moderator moderator, @RequestParam("Пароль") String password) {
        ApiUsers apiUser = new ApiUsers();
        apiUser.setPhone(moderator.getPhone());
        apiUser.setRole(Role.ADMIN);
        apiUser.setPassword(password);
        apiUsersService.create(apiUser);
        moderatorService.create(moderator);
        return new ResponseEntity<>(moderator, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Moderator>> read() {
        final List<Moderator> moderators = moderatorService.readAll();

        return moderators != null &&  !moderators.isEmpty()
                ? new ResponseEntity<>(moderators, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Moderator> read(@PathVariable(name = "id") int id) {
        final Moderator moderator = moderatorService.read(id);

        return moderator != null
                ? new ResponseEntity<>(moderator, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Moderator moderator) {
        final boolean updated = moderatorService.update(moderator, id);

        return updated
                ? new ResponseEntity<>(moderator, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = moderatorService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
