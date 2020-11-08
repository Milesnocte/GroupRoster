package Listeners;

import Credentials.Credentials;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import Commands.*;

public class MessageListener extends ListenerAdapter
{
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event)
    {
        if(!event.getAuthor().isBot()) {
            String[] args = event.getMessage().getContentRaw().split(" ");
            boolean owner = event.getAuthor().getId().equals(Credentials.OWNER_TOKEN);

            // USER COMMANDS
            if (args[0].startsWith("$")) {
                args[0] = args[0].replace("$", "");
                try {
                    new Commands(args, event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            // OWNER COMMANDS
            if (owner && args[0].startsWith("^")) {
                args[0] = args[0].replace("^", "");
                try {
                    new OwnerCommands(args, event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            // DEBUG LOGGING
            //if (event.isFromType(ChannelType.TEXT)) {
            //    System.out.printf("[%s][%s] %#s: %s%n", event.getGuild().getName(),
            //            event.getChannel().getName(), event.getAuthor(), event.getMessage().getContentDisplay());
            //} else {
            //    System.out.printf("[PM] %#s: %s%n", event.getAuthor(), event.getMessage().getContentDisplay());
            //}
        }
    }
}
