package com.craftsteamg.commands;

import com.craftsteamg.api.MsgWrapper;
import com.craftsteamg.arguments.Argument;

import java.util.List;

public interface Command {

    String getTrigger();

    List<Argument<?>> getArguments();

    void execute(MsgWrapper msg);

}
