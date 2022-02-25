package persistance;

import model.CollectionManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Class taken from...
//Represents a writer that writes JSON representation of CollectionManager
public class JsonWriter {
    private String destination;
    private PrintWriter writer;


    // EFFECTS: constructs writer to write to destination file
    // Code taken from JsonWriter class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JsonWriter(String jsonFile) {
        destination = jsonFile;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot be open for writing
    // Code taken from JsonWriter class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON file representing CollectionManager
    // Code taken from JsonWriter class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void write(CollectionManager manager) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    // Code taken from JsonWriter class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void close() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    // Code taken from JsonWriter class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void saveToFile(String json) {
        writer.print(json);
    }

}
