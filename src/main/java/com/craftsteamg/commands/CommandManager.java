package com.craftsteamg.commands;

import com.craftsteamg.api.MsgWrapper;
import com.craftsteamg.arguments.ArgumentParseException;
import com.craftsteamg.commands.cmds.ArgumentTest;
import com.mongodb.client.MongoDatabase;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {

    public static final String PREFIX = "!";

    private MongoDatabase database;

    public CommandManager(MongoDatabase database) {
        this.database = database;
        registerCommands();
    }


    private List<Command> commands = new ArrayList<>();

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;

        String messageString = event.getMessage().getContentRaw();

        if(!messageString.startsWith(PREFIX) && messageString.length() <= 1) return;
        String trigger = messageString.substring(1).split(" ")[0];
        MsgWrapper wrapper = new MsgWrapper(event.getMessage());

        for(Command command : commands) {
            if(command.getTrigger().equalsIgnoreCase(trigger)) {
                try {
                    if (wrapper.parse(command)) {
                        command.execute(wrapper);
                        break;
                    }
                } catch (ArgumentParseException e) {
                    wrapper.reply(e.getMessage());
                }
            }
        }

    }

    private void registerCommands() {
        this.commands.add(new ArgumentTest());
    }
}
