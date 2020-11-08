package Listeners;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import Main.*;

public class ReactionListener extends ListenerAdapter {
    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        new UpdateEmbed(event);
        System.out.printf("\n[%s][%s] %s added by %s aka %s", event.getGuild().getName(),
                event.getChannel().getName(), event.getReactionEmote().getName(),
                event.getMember().getEffectiveName(), event.getMember().getNickname());
    }
}
