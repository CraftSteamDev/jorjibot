package com.craftsteamg.api;

import com.craftsteamg.arguments.Argument;
import com.craftsteamg.arguments.ArgumentParseException;
import com.craftsteamg.commands.Command;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import javax.annotation.Nullable;

@Getter
public class MsgWrapper {

    /**
     * Non-Persistent Data
     */
    private transient Message message;
    private transient User author;
    @Nullable
    private transient Guild guild;
    private transient MessageChannel channel;

    /**
     * Persistent Data
     */
    private long messageID;
    private long channelID;
    private String messageContent;
    private String authorName;
    private short authorDiscrim;
    private long epochCreated;

    public MsgWrapper() {} //For Gson
    public MsgWrapper(Message message) {
        this.message = message;
        if(this.message.isFromGuild())
            this.guild = message.getGuild();
        this.channel = message.getChannel();
        this.channelID = this.channel.getIdLong();
        this.author = message.getAuthor();
        this.authorName = this.author.getName();
        this.messageID = message.getIdLong();
        this.messageContent = message.getContentRaw();
        this.authorDiscrim = Short.parseShort(message.getAuthor().getDiscriminator().substring(1));
        this.epochCreated = this.message.getTimeCreated().toEpochSecond();
    }

    @Override
    public String toString() {
        return this.authorName + "#" + this.authorDiscrim + ": " + this.messageContent;
    }

    public void reply(String message) {
        this.channel.sendMessage(message).queue();
    }

    public boolean parse(Command cmd) throws ArgumentParseException {
        String current = this.getMessageContent().substring(cmd.getTrigger().length() + 1).trim();
        for (Argument<?> argument : cmd.getArguments()) {
            current = current.substring(argument.parse(this, current));
        }
        return true;
    }
}
