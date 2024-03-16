package com.server.controller.user;

import com.server.dto.user.ModeratorDto;
import com.server.dto.user.UserRegistrationDto;
import com.server.entity.user.Moderator;
import io.swagger.v3.oas.annotations.tags.Tag;
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
public interface IModeratorController {

    /**
     *
     * @param userRegistrationDto
     * @return
     */
    @RequestMapping(value = "/new-moderator", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody UserRegistrationDto userRegistrationDto);


    /**
     *
     * @return
     */
    @RequestMapping(value = "/moderator", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Moderator>> readAll();


    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/moserator/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Moderator> readId(@PathVariable(name = "id") int id);


    /**
     *
     * @param id
     * @param moderatorDto
     * @return
     */
    @RequestMapping(value = "/moderator/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(
            @PathVariable(name = "id") int id,
            @RequestBody ModeratorDto moderatorDto
    );


    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/moderator/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id);


    /**
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "moderator/filter-by-phone",method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Moderator> filterByPhone(@RequestParam("phone") String phone);
}
