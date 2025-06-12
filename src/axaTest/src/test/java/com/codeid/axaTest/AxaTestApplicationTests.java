package com.codeid.axaTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codeid.axaTest.model.dto.UserDto;
import com.codeid.axaTest.service.UserService;

@SpringBootTest
class AxaTestApplicationTests {

	@Autowired
    private UserService userService;

    @Test
    void testCreateUser() {
        UserDto dto = new UserDto(null, "john", "pass123", 1L);
        UserDto saved = userService.save(dto);
        assertNotNull(saved.userId());
        assertEquals("john", saved.username());
    }

}
