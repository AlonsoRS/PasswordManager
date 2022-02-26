package persistance;

import model.CollectionManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    CollectionManager test;



    @Test
    //Modified code from JsonReaderTest class in https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            test = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    //Modified code from JsonReaderTest class in https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCollectionManager.json");
        try {
            test = reader.read();
            assertEquals(0, test.getCollections().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    //Modified code from JsonReaderTest class in https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCollectionManager.json");
        try {
            test = reader.read();
            //assert equals
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
