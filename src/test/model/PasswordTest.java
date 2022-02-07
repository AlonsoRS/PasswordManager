package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordTest {
    Password testPass;

    @BeforeEach
    void runBefore() {
        testPass = new Password("test");
    }

    @Test
    void testConstructor() {
        assertEquals("test" ,testPass.getPassword());

        testPass.setPassword("n2ew1");
        assertEquals("n2ew1",testPass.getPassword());
    }
}
