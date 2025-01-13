package com.example.ProductMenager_service.Api;

import com.example.ProductMenager_service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalUserService {

    private final FakeStoreApiClient fakeStoreApiClient;

    public ExternalUserService(FakeStoreApiClient fakeStoreApiClient) {
        this.fakeStoreApiClient = fakeStoreApiClient;
    }

    /**
     * Pobiera listę użytkowników z FakeStoreApi
     *
     * @return lista użytkowników jako obiekty UserDto
     */
    public List<UserDto> getAllUsersFromApi() {
        return fakeStoreApiClient.getAllUsers();
    }
}
