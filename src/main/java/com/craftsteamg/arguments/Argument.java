package com.craftsteamg.arguments;

import com.craftsteamg.api.MsgWrapper;

public abstract class Argument<T> {

    private T arg;

    protected abstract boolean matches(MsgWrapper msg);

    public abstract int parse(MsgWrapper msg, String current) throws ArgumentParseException;

    public T getParsed() {
        return arg;
    }

    public void setArg(T parsed) {
        arg = parsed;
    }

}
