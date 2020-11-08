package Commands;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class OwnerCommands {
    public OwnerCommands(String[] args, MessageReceivedEvent event) throws InterruptedException {
        switch(args[0].toLowerCase()){
            case "verify":
                System.out.println("Verification complete - Owner Commands operational");
                event.getMessage().delete().queue();
                break;
            case "leave":
                event.getMessage().delete().queue();
                event.getGuild().leave().queue();
                break;
            case "emote":
                Emote emote = event.getMessage().getEmotes().get(0);
                event.getChannel().sendMessage(emote.getAsMention() + " " + event.getMessage().getEmotes().toString()).queue();
                break;
        }
    }
}
