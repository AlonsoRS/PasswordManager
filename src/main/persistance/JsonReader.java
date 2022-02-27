package persistance;

import model.CollectionManager;
import model.Collection;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


//Represents a reader that reads CollectionManager from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    // Code taken from JsonReader class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JsonReader(String jsonFile) {
        source = jsonFile;
    }

    // EFFECTS: reads CollectionManager from file and returns it;
    // throws IOException if an error occurs reading data from file
    // Code taken from JsonReader class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public CollectionManager read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCollectionManager(jsonObject);
    }

    // EFFECTS: parses CollectionManager from JSON object and returns it
    // Code taken from JsonReader class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private CollectionManager parseCollectionManager(JSONObject json) {
        CollectionManager cm = new CollectionManager();
        addCollections(cm, json);
        return cm;
    }

    // EFFECTS: reads source file as string and returns it
    // Code taken from JsonReader class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private String readFile(String fileName) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: cm
    // EFFECTS: parses Collections from JSON object and adds them to CollectionManager
    // Code taken from JsonReader class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void addCollections(CollectionManager cm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        for (Object json : jsonArray) {
            JSONObject nextCollection = (JSONObject) json;
            addCollection(cm, nextCollection);
        }
    }

    // MODIFIES: cm
    // EFFECTS: parses Collection from JSON object and adds it to CollectionManager
    // Code taken from JsonReader class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void addCollection(CollectionManager cm, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Collection c = new Collection(name);
        addUsers(c, jsonObject);
        cm.addCollection(c);
    }

    // MODIFIES: c
    // EFFECTS: parses Users from JSON object and adds them to collection
    // Code taken from JsonReader class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void addUsers(Collection c,JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("users");
        for (Object json : jsonArray) {
            JSONObject nextUser = (JSONObject) json;
            addUser(c, nextUser);
        }
    }


    // MODIFIES: c
    // EFFECTS: parses User from JSON object and adds it to collection
    // Code taken from JsonReader class: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void addUser(Collection c, JSONObject jsonObject) {
        String name = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String website = jsonObject.getString("website");
        User u = new User(name, password);
        u.setWebsite(website);
        c.addUser(u);
    }
}
