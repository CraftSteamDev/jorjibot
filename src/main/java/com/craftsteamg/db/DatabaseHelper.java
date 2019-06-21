package com.craftsteamg.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.dv8tion.jda.api.entities.Guild;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class DatabaseHelper {

    public static List<String> getJoinLeaveChannels(MongoDatabase database, Guild guild) {
        MongoCollection<Document> collection = database.getCollection("joinLeave");
        Document doc = collection.find(eq("guildID", guild.getId())).first();
        return doc != null ? doc.getList("channels", String.class, new ArrayList<>()) : new ArrayList<>();
    }
}
