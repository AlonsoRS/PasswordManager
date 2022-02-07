package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User testUser;

    @BeforeEach
    void runBefore() {
        testUser = new User("Pablo33", "123456");
    }

    @Test
    void testConstructor() {
        assertEquals("Pablo33",testUser.getUsername());
        assertEquals("123456", testUser.getPassword());
        assertEquals("", testUser.getWebsite());
        assertEquals(LocalDate.now(), testUser.getDate());

        testUser.setWebsite("www.google.com");
        assertEquals("www.google.com", testUser.getWebsite());
        testUser.setUsername("A");
        assertEquals("A", testUser.getUsername());
        testUser.setPassword("477");
        assertEquals("477", testUser.getPassword());


    }




}