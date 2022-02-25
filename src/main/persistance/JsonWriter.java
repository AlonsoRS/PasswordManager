package persistance;

import model.CollectionManager;

import java.io.FileNotFoundException;

//Class taken from...
//WHAT DOES THIS CLASS DOES
public class JsonWriter {
    String destination;

    // EFFECTS: constructs writer to write to destination file
    // Code taken from JsonWriter class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JsonWriter(String jsonFile) {
        destination = jsonFile;
    }

    // MODIFIES:
    // EFFECTS:
    // CITATION
    public void open() throws FileNotFoundException {
        //stub
    }

    // MODIFIES:
    // EFFECTS:
    // CITATION
    public void write(CollectionManager manager) {
        //stub
    }

    // MODIFIES:
    // EFFECTS:
    // CITATION
    public void close() {
        //stub
    }

}
