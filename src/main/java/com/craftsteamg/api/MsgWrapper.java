package com.craftsteamg.api;

import lombok.Getter;
import net.dv8tion.jda.api.entities.*;

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
    private int authorDiscrim;
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
        this.authorDiscrim = Integer.parseInt(message.getAuthor().getDiscriminator().substring(1));
        this.epochCreated = this.message.getTimeCreated().toEpochSecond();
    }





}
