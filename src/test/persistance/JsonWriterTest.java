package persistance;

import model.Collection;
import model.CollectionManager;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    CollectionManager test;

    @BeforeEach
    void runBefore() {
        CollectionManager test = new CollectionManager();
        Collection collection1 = new Collection("Work");
        Collection collection2 = new Collection("Entertainment");

        User user1 = new User("user1", "123");
        user1.setWebsite("www.gmail.com");
        User user2 = new User("user2", "564");
        user2.setWebsite("www.netflix.com");
        User user3 = new User("user3", "341");
        user3.setWebsite("www.youtube.com");

        collection1.addUser(user1);
        collection2.addUser(user2);
        collection2.addUser(user3);

        test.addCollection(collection1);
        test.addCollection(collection2);
    }


    @Test
    //Modified code from JsonWriterTest class in https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    void testWriterInvalidFile() {
        try{
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("Expected IOException, test failed");
        }
        catch (IOException e) {
            // pass
        }
    }

    @Test
    //Modified code from JsonWriterTest class in https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    void testWriterEmptyWorkroom() {
        try {
            CollectionManager empty = new CollectionManager();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCollectionManager.json");
            writer.open();
            writer.write(empty);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCollectionManager.json");
            empty = reader.read();
            assertEquals(0, empty.getCollections().size());
        }
        catch (IOException e) {
            fail("IOException thrown but not expected, test failed");
        }
    }

    @Test
    //Modified code from JsonWriterTest class in https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    void testWriterGeneralWorkroom() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCollectionManager.json");
            writer.open();
            writer.write(test);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCollectionManager.json");
            test = reader.read();
            assertEquals(2, test.getCollections().size());
            assertEquals("Work", test.getCollections().get(0).getCollectionName());
            assertEquals("Entertainment", test.getCollections().get(1).getCollectionName());

            assertEquals("user1", test.getCollections().get(0).getUsers().get(0).getUsername());
            assertEquals("123", test.getCollections().get(0).getUsers().get(0).getPassword());
            assertEquals("www.gmail.com", test.getCollections().get(0).getUsers().get(0).getWebsite());

            assertEquals("user2", test.getCollections().get(1).getUsers().get(0).getUsername());
            assertEquals("564", test.getCollections().get(1).getUsers().get(0).getPassword());
            assertEquals("www.netflix.com", test.getCollections().get(1).getUsers().get(0).getWebsite());

            assertEquals("user3", test.getCollections().get(1).getUsers().get(1).getUsername());
            assertEquals("341", test.getCollections().get(1).getUsers().get(1).getPassword());
            assertEquals("www.youtube.com", test.getCollections().get(1).getUsers().get(1).getWebsite());

        }
        catch (IOException e) {
            fail("IOException thrown but not expected, test failed");
        }
    }
}
