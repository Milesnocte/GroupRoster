package Listeners;

import Credentials.Credentials;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import Main.*;

import java.util.Arrays;
import java.util.List;

public class ReactionListener extends ListenerAdapter {
    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        List<String> reacts = Arrays.asList("RE:Tank(705672828468330516)","RE:Healer(705672828522987540)","RE:StaminaDPS(705672828292169790)","RE:MagicaDPS(705672828254552104)","RE:Runner(705672828258877450)","U+1f6ab");
        if(!event.getUser().isBot() && reacts.contains(event.getReactionEmote().toString())) {

        }

    }
}
