package dev.rollczi.averagecatboyenjoyer;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.description.Description;
import dev.rollczi.litecommands.annotations.execute.Execute;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

@Command(name = "catboy")
@Description("Get a random catboy image")
class CatboyCommand {

    private final CatboyService catboyService;

    CatboyCommand(CatboyService catboyService) {
        this.catboyService = catboyService;
    }

    @Execute
    void executeCatboy(@Context SlashCommandInteractionEvent event) {
        event.reply("Getting a catboy image...")
            .setEphemeral(true)
            .queue();

        event.getChannel().sendMessage(catboyService.nextImage()).queue();
    }

}
