package com.craftsteamg.arguments.args;

import com.craftsteamg.api.MsgWrapper;
import com.craftsteamg.arguments.Argument;
import com.craftsteamg.arguments.ArgumentParseException;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class ArgumentTextChannel extends Argument<TextChannel> {

    @Override
    protected boolean matches(MsgWrapper msg) {
        return msg.getMessage().getMentionedChannels().size() > 0;
    }

    @Override
    public int parse(MsgWrapper msg, String current) throws ArgumentParseException {

        String[] args = current.split(" ");

        int currentIndex = 0;
        for(String arg : args) {
            currentIndex += arg.length();
            if(Message.MentionType.CHANNEL.getPattern().matcher(arg).find()) {
                String s = arg.replaceAll("\\D+", "");
                TextChannel channel = msg.getMessage().getJDA().getTextChannelById(s);
                if (channel != null) {
                    this.setArg(channel);
                    return currentIndex + 1;
                }
            }
        }
        throw new ArgumentParseException("No TextChannel Mentioned!");

    }
}
