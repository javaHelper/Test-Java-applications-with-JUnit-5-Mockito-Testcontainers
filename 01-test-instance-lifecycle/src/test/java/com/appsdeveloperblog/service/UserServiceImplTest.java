package com.appsdeveloperblog.service;

import com.appsdeveloperblog.io.UsersDatabase;
import com.appsdeveloperblog.io.UsersDatabaseMapImpl;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest {
    UsersDatabase usersDatabase;
    UserService userService;
    String createdUserId = "";

    @BeforeAll
    void setup() {
        // Create & initialize database
        usersDatabase = new UsersDatabaseMapImpl();
        usersDatabase.init();
        userService = new UserServiceImpl(usersDatabase);
    }

    @AfterAll
    void cleanup() {
        // Close connection
        // Delete database
        usersDatabase.close();
    }

    @Test
    @Order(1)
    @DisplayName("Create User works")
    void testCreateUser_whenProvidedWithValidDetails_returnsUserId() {
        // Arrange
        Map<String, String> user = new HashMap<>();
        user.put("firstName","Prateek");
        user.put("lastName","Ninawe");

        createdUserId = userService.createUser(user);

        // Assert
        assertNotNull(createdUserId, "User Id should not be null");
    }


    @Test
    @Order(2)
    @DisplayName("Update user works")
    void testUpdateUser_whenProvidedWithValidDetails_returnsUpdatedUserDetails() {
        Map<String, String> newUserDetails = new HashMap<>();
        newUserDetails.put("firstName","Prateek");
        newUserDetails.put("lastName","Ninawe");

        Map updateUserDetails = userService.updateUser(createdUserId, newUserDetails);

        assertEquals(newUserDetails.get("firstName"), updateUserDetails.get("firstName"),
                "Returned value of user's first name is incorrect");
        assertEquals(newUserDetails.get("lastName"), updateUserDetails.get("lastName"),
                "Returned value of user's last name is incorrect");
    }

    @Test
    @Order(3)
    @DisplayName("Find user works")
    void testGetUserDetails_whenProvidedWithValidUserId_returnsUserDetails() {
        // Act
        Map userDetails = userService.getUserDetails(createdUserId);

        // Assert
        assertNotNull(userDetails, "User Details should not be null");
        assertEquals(createdUserId, userDetails.get("userId"),
                "Return user details contains incorrect user id");
    }

    @Test
    @Order(4)
    @DisplayName("Delete user works")
    void testDeleteUser_whenProvidedWithValidUserId_returnsUserDetails() {
        // Act
        userService.deleteUser(createdUserId);

        // Assert
        assertNull(userService.getUserDetails(createdUserId),"User should not be null");
    }

}