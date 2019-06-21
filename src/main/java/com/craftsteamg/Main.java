package com.craftsteamg;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {

    /**
     *
     * @param args Index 0: Bot Token
     *             Index 1: Mongo Username
     *             Index 2: Mongo Password
     *             Index 3: Mongo Host
     *             Index 4: Mongo DB Name
     */
    public static void main(String[] args) throws LoginException {
        JDA jda = new JDABuilder().setToken(args[0])
                .build();
        new JorjiBot(jda, args);
    }
}
