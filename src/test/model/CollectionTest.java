package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionTest {
    Collection testCollection;

    @BeforeEach
    void runBefore() {
        testCollection = new Collection("Entertainment");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testCollection.getCollection().size());
        assertEquals("Entertainment", testCollection.getCollectionName());

        testCollection.setCollectionName("Banking");
        assertEquals("Banking", testCollection.getCollectionName());
    }

    @Test
    void testAddUser() {
        testCollection.addUser(new User("Joe6", "123"));
        assertEquals(1, testCollection.getCollection().size());

        testCollection.addUser(new User("Moe3", "124"));
        assertEquals(2, testCollection.getCollection().size());

        assertEquals("Moe3", testCollection.getCollection().get(1).getUsername());

    }

    @Test
    void testGetUserByUsername() {
        User find1 = new User("Om4456", "3232");
        User find2 = new User("Om4456", "1246");
        testCollection.addUser(new User("Joe6", "123"));
        testCollection.addUser(find1);
        testCollection.addUser(new User("Moe3", "124"));
        testCollection.addUser(find2);

        assertEquals(2,testCollection.getUserByUsername("Om4456").size());
        assertEquals("Om4456", testCollection.getUserByUsername("Om4456").get(0).getUsername());
        assertEquals("Om4456", testCollection.getUserByUsername("Om4456").get(1).getUsername());

        assertEquals("3232", testCollection.getUserByUsername("Om4456").get(0).getPassword());
        assertEquals("1246", testCollection.getUserByUsername("Om4456").get(1).getPassword());
    }
}
