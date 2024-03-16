package com.server.controller.user;

import com.server.dto.user.ClientDto;
import com.server.dto.user.UserRegistrationDto;
import com.server.entity.user.Client;
import com.server.entity.user.Gender;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("v1")
@Tag(
        name = "Пользователи",
        description = "Все методы для работы с пользователями системы"
)
public interface IClientController {

    /**
     *
     * @param clientRegDto
     * @return
     */
    @RequestMapping(value = "/new-client",method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody UserRegistrationDto clientRegDto);


    /**
     *
     * @return
     */
    @RequestMapping(value = "/client",method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    ResponseEntity<List<Client>> readAll();


    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT', 'LEAD')")
    ResponseEntity<Client> readId(@PathVariable(name = "id") int id);


    /**
     *
     * @param id
     * @param clientDto
     * @return
     */
    @RequestMapping(value = "/client/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT', 'LEAD')")
    ResponseEntity<?> update(
            @PathVariable(name = "id") int id,
            @RequestBody ClientDto clientDto
    );


    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/client/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<?> delete(@PathVariable(name = "id") int id);


    /**
     *
     * @param gender
     * @return
     */
    @RequestMapping(value = "client/filter-by-gender",method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    ResponseEntity<List<Client>> filterByGender(@RequestParam("gender") Gender gender);


    /**
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "client/filter-by-phone",method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    ResponseEntity<Client> filterByPhone(@RequestParam("phone") String phone);
}
