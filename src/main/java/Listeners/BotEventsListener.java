package Listeners;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class BotEventsListener extends ListenerAdapter {

    // NUMBER OF GUILDS THE BOT IS IN
    public static int guildCount;

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        updateActivity(event);
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        updateActivity(event);
    }

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event) {
        updateActivity(event);
    }

    public void updateActivity(Event event){
        guildCount = event.getJDA().getGuilds().size();
        System.out.println(guildCount);
        event.getJDA().getPresence().setActivity(Activity.watching(guildCount + " Guilds, use &Help"));
    }
}

