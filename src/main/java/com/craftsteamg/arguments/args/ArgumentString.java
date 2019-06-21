package com.craftsteamg.arguments.args;

import com.craftsteamg.api.MsgWrapper;
import com.craftsteamg.arguments.Argument;
import com.craftsteamg.arguments.ArgumentParseException;

public class ArgumentString extends Argument<String> {

    int limit = -1;

    public ArgumentString() {

    }

    public ArgumentString(int limit) {
        this.limit = limit;
    }

    @Override
    protected boolean matches(MsgWrapper msg) {
        return !msg.getMessageContent().isEmpty();
    }

    @Override
    public int parse(MsgWrapper msg, String current) throws ArgumentParseException {

        if(current.isEmpty())
            throw new ArgumentParseException("You did not provide a valid string!");

        if(limit == -1) {
            setArg(current);
            return current.length();
        }

        int index = 0;
        StringBuilder builder = new StringBuilder();

        for(char c : current.toCharArray()) {
            builder.append(c);
            index++;
            if(builder.length() >= limit) {
                setArg(builder.toString());
                return builder.length();
            }
        }

        throw new ArgumentParseException("No Acceptable String Found!");

    }
}
