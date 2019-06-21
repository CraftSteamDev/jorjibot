package com.craftsteamg.commands.cmds;

import com.craftsteamg.api.MsgWrapper;
import com.craftsteamg.arguments.Argument;
import com.craftsteamg.arguments.args.ArgumentString;
import com.craftsteamg.commands.Command;
import net.dv8tion.jda.api.entities.Activity;

import java.util.Arrays;
import java.util.List;

public class ArgumentTest implements Command {

    private List<Argument<?>> args = Arrays.asList(
            new ArgumentString()
    );

    @Override
    public String getTrigger() {
        return "play";
    }

    @Override
    public List<Argument<?>> getArguments() {
        return args;
    }

    @Override
    public void execute(MsgWrapper msg)  {
        String message = (String) getArguments().get(0).getParsed();
        msg.getMessage().getJDA().getPresence().setActivity(Activity.playing(message));
    }

}
