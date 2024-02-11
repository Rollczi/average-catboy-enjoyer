package dev.rollczi.averagecatboyenjoyer;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.jda.LiteJDAFactory;
import java.io.File;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

public class AverageCatboyEnjoyer {

    public static void main(String[] args) {
        String token = System.getenv("DISCORD_TOKEN");
        JDA jda = JDABuilder.createDefault(token)
            .build();

        CatboyService catboyService = new CatboyService(new File("config.yml"));

        // update commands
        Guild guildById = jda.getGuildById(System.getenv("GUILD_ID"));

        LiteCommands<User> liteCommands = LiteJDAFactory.builder(jda)
            .commands(new CatboyCommand(catboyService))
            .build();

    }

}
