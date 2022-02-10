package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionManagerTest {
    CollectionManager test;

    @BeforeEach
    void runBefore() {
        test = new CollectionManager();
    }

    @Test
    void testConstructor() {
        assertEquals(0, test.getCollections().size());
    }

    @Test
    void testAddCollection() {
        test.addCollection(new Collection("Academic"));
        assertEquals(1, test.getCollections().size());
        assertEquals("Academic", test.getCollections().get(0).getCollectionName());

        test.addCollection(new Collection("Banking"));
        assertEquals("Banking", test.getCollections().get(1).getCollectionName());
    }

    @Test
    void testFindUser() {
        assertEquals(0, test.findUser("Emp").size());

        CollectionManager manager = new CollectionManager();

        Collection collection1 = new Collection("Entertainment");
        collection1.addUser(new User("Pablo8", "890"));
        collection1.addUser(new User("Juan57", "123"));

        Collection collection2 = new Collection("E-shop");
        collection2.addUser(new User("Jim47", "666"));
        collection2.addUser(new User("Pablo8", "123"));

        manager.addCollection(collection1);
        manager.addCollection(collection2);

        ArrayList<Collection> foundIn2 = manager.findUser("Pablo8");
        ArrayList<Collection> foundIn1 = manager.findUser("Jim47");
        ArrayList<Collection> foundIn0 = manager.findUser("Empty");

        assertEquals(0, foundIn0.size());

        assertEquals(1, foundIn1.size());
        assertEquals("E-shop", foundIn1.get(0).getCollectionName());
        assertEquals("Jim47", foundIn1.get(0).getUsers().get(0).getUsername());

        assertEquals(2, foundIn2.size());
        assertEquals("Entertainment", foundIn2.get(0).getCollectionName());
        assertEquals("E-shop", foundIn2.get(1).getCollectionName());
        assertEquals("Pablo8", foundIn2.get(0).getUsers().get(0).getUsername());
        assertEquals("890", foundIn2.get(0).getUsers().get(0).getPassword());
        assertEquals("Pablo8", foundIn2.get(1).getUsers().get(1).getUsername());
        assertEquals("123", foundIn2.get(1).getUsers().get(1).getPassword());

    }

    @Test
    void testFindCollection() {
        assertEquals(null, test.findCollection("Banking"));

        test.addCollection(new Collection("Banking"));
        test.addCollection(new Collection("Entertainment"));
        test.addCollection(new Collection("Academics"));
        assertEquals("Banking",test.findCollection("Banking").getCollectionName());

    }
}
