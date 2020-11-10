package Commands;
import Listeners.BotEventsListener;

import Main.BuildEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.sql.SQLException;

public class Commands {

    private static BuildEmbed embed;

    public Commands(String[] args, MessageReceivedEvent event) throws SQLException, ClassNotFoundException {
        switch(args[0].toLowerCase()){
            case "help":
                event.getChannel().sendMessage("`Command hasn't been implemented yet`").queue();
                break;
            case "embed":
                new BuildEmbed(event, args);
                break;
            case "servers":
                event.getChannel().sendMessage("Im currently watching `" + BotEventsListener.guildCount + "` servers for roster events").queue();
                break;
        }
    }
}
