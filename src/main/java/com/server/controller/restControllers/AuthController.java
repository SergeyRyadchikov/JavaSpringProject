package com.server.controller.restControllers;
import com.server.dto.UserDTO;
import com.server.model.Role;
import com.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController()
@RequestMapping()
@Tag(
        name = "Авторизация",
        description = "Метод авторизации пользователя"
)
public class AuthController {

    private final JwtTokenService jwtTokenService;
    private final LoginService loginService;

    private final ApiUsersService apiUsersService;
    private final ClientService clientService;
    private final ModeratorServiceImpl moderatorService;

    @Autowired
    public AuthController(
            JwtTokenService jwtTokenService,
            LoginService loginService,
            ApiUsersService apiUsersService,
            ClientService clientService,
            ModeratorServiceImpl moderatorService
    ) {
        this.jwtTokenService = jwtTokenService;
        this.loginService = loginService;
        this.apiUsersService = apiUsersService;
        this.clientService = clientService;
        this.moderatorService = moderatorService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {

        return loginService.login(userDTO.phone(), userDTO.password())
                .map(optional -> {
                    int userId;
                    if (apiUsersService.findApiUsersByPhone(userDTO.phone()).getRole().equals(Role.ADMIN)) {
                        userId = moderatorService.findByPhone(userDTO.phone()).getId();

                    } else {
                        userId = clientService.findByPhone(userDTO.phone()).getId();
                    }
                    String token = jwtTokenService.generateToken(
                            userId,
                            apiUsersService.findApiUsersByPhone(userDTO.phone()).getRole().name());
                    return new ResponseEntity<>("Bearer " + token, HttpStatus.OK);
                }).orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));

    }
}
