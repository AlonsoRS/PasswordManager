package persistance;

import model.CollectionManager;

import java.io.IOException;

//Class taken from...
//WHAT DOES THIS CLASS DOES
public class JsonReader {
    String source;

    // EFFECTS: constructs reader to read from source file
    // Code taken from JsonReader class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JsonReader(String jsonFile) {
        source = jsonFile;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    // Code taken from JsonReader class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public CollectionManager read() throws IOException {
        return null; // stub
    }
}
