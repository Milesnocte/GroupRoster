package Main;

import Credentials.Credentials;
import Listeners.BotEventsListener;
import Listeners.MessageListener;
import Listeners.ReactionListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import javax.security.auth.login.LoginException;

public class JDA extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, InterruptedException {

        JDABuilder jda = JDABuilder.createDefault(Credentials.TOKEN);

        // Disable parts of the cache
        jda.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);

        // Enable the bulk delete event
        jda.setBulkDeleteSplittingEnabled(false);

        // Set event manager
        jda.addEventListeners(new MessageListener());
        jda.addEventListeners(new ReactionListener());
        jda.addEventListeners(new BotEventsListener());

        // build the bot
        jda.build().awaitReady();

        System.out.println("https://discord.com/api/oauth2/authorize?client_id=" + Credentials.BOT_ID + "&permissions=1544023120&scope=bot");
    }
}