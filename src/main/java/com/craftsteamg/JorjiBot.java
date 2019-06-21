package com.craftsteamg;

import com.craftsteamg.commands.CommandManager;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import net.dv8tion.jda.api.JDA;

public class JorjiBot {

    private MongoClient dbClient;
    private MongoDatabase db;

    public JorjiBot(JDA jda, String[] args) {
        dbClient = createDBClient(args[1], args[2], args[3], args[4]);
        db = dbClient.getDatabase(args[4]);
        setupListeners(jda);
    }


    private void setupListeners(JDA jda) {
        jda.addEventListener(new CommandManager());
    }


    private MongoClient createDBClient(String userName, String pwd, String ip, String dbName) {
        return MongoClients.create("mongodb://" + userName + ":" + pwd + "@" + ip + "/" + dbName);
    }


}
