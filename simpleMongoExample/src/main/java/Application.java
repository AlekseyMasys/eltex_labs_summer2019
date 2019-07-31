import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Application {
    public static void main(String[] args) {
            MongoClient mongoClient = MongoClients.create();
            MongoDatabase db = mongoClient.getDatabase("users_db");
            MongoCollection<Document> table = db.getCollection("users");
            Document document1 = new Document(Map.of("name", "mr.White", "money", 90000, "weaponts", Arrays.asList("gun", "knife", "shotgun")));
            Document document2 = new Document(Map.of("name", "mr.Blond", "money", 77000, "weaponts", Arrays.asList("desert eagle", "dynamite", "rifle")));
            List<Document> documents = new ArrayList<>();
            documents.add(document1);
            documents.add(document2);
            table.insertMany(documents);
            table.find().forEach((Consumer<? super Document>) System.out::println); // show all documents from collection
            table.updateOne(new Document(Map.of("name", "mr.Blond")), new Document(Map.of("$set", new Document("money", 888))));
            System.out.println("------------------------------------------");
            table.find().forEach((Consumer<? super Document>) System.out::println); // show all documents from collection after update
            table.deleteOne(new Document(Map.of("name", "mr.White")));
            System.out.println("-------------------------------------------");
            table.find().forEach((Consumer<? super Document>) System.out::println); // show all documents after delete
            mongoClient.close();

    }
}
