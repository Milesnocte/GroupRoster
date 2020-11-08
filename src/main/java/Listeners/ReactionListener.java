package Listeners;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import Commands.*;

public class ReactionListener extends ListenerAdapter {
    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        Commands.getEmbed();
        System.out.printf("\n[%s][%s] %s added by %s aka %s", event.getGuild().getName(),
                event.getChannel().getName(), event.getReactionEmote().getName(),
                event.getMember().getEffectiveName(), event.getMember().getNickname());
    }

    @Override
    public void onGuildMessageReactionRemove(@NotNull GuildMessageReactionRemoveEvent event) {
        System.out.printf("\n[%s][%s] %s removed by %s", event.getGuild().getName(),
                event.getChannel().getName(), event.getReactionEmote().getName(),
                event.getMember().getEffectiveName());
    }
}
