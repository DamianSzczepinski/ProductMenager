package com.example.ProductMenager_api;

import com.example.ProductMenager_service.Api.ExternalUserService;
import com.example.ProductMenager_service.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users") // Ustawienie bazy endpointów dla użytkowników
public class UserController {

    private final ExternalUserService externalUserService;

    // Wstrzyknięcie serwisu
    public UserController(ExternalUserService externalUserService) {
        this.externalUserService = externalUserService;
    }

    /**
     * Endpoint do pobierania listy użytkowników z zewnętrznego API.
     */
    @GetMapping("/external")
    public ResponseEntity<List<UserDto>> getAllUsersFromApi() {
        List<UserDto> users = externalUserService.getAllUsersFromApi();
        return ResponseEntity.ok(users);
    }
}