package com.craftsteamg.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class DatabaseHelper {

    public static class JoinLeaveHelpers {
        public static List<String> getJoinLeaveChannels(MongoDatabase database, Guild guild) {
            MongoCollection<Document> collection = database.getCollection("joinLeave");
            Document doc = collection.find(eq("guildID", guild.getId())).first();
            return doc != null ? doc.getList("channels", String.class, new ArrayList<>()) : new ArrayList<>();
        }

        public static void addJoinLeaveChannel(MongoDatabase database, Guild guild, MessageChannel channel) {
            MongoCollection<Document> collection = database.getCollection("joinLeave");
            Document document = collection.find(eq("guildID", guild.getId())).first();
            if(document == null || document.isEmpty()) {
                Document newDoc = new Document()
                        .append("guildID", guild.getId())
                        .append("channels", Arrays.asList(channel.getId()));
                collection.insertOne(newDoc);
            }else{
                collection.updateOne(document, Updates.addToSet("channels", channel.getId()));
            }
        }

        public static void deleteJoinLeaveChannel(MongoDatabase database, Guild guild, MessageChannel channel) {
            MongoCollection<Document> collection = database.getCollection("joinLeave");
            Document document = collection.find(eq("guildID", guild.getId())).first();
            if(document == null || document.isEmpty()) return;
            collection.updateOne(document, Updates.pull("channels", channel.getId()));
        }
    }
}
