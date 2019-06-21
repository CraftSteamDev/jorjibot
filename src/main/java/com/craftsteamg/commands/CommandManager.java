package com.craftsteamg.commands;

import com.mongodb.client.MongoDatabase;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class CommandManager extends ListenerAdapter {

    public static final String PREFIX = "!";

    private MongoDatabase database;

    public CommandManager(MongoDatabase database) {
        this.database = database;
    }


    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;

        String messageString = event.getMessage().getContentRaw();

        if(!messageString.startsWith(PREFIX) && messageString.length() <= 1) return;
        messageString = messageString.substring(1);

        if(messageString.equalsIgnoreCase("ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        }


    }
}
