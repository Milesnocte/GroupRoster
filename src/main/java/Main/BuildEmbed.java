package Main;
import Database.DataBase;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuildEmbed extends ListenerAdapter {

    String id;
    String[] args;
    String host;
    String date;
    ArrayList<String> going;
    ArrayList<String> maybe;
    EmbedBuilder embed = new EmbedBuilder();

    public BuildEmbed(MessageReceivedEvent event, String[] args) throws Exception {

        String desc = event.getMessage().getContentRaw();
        int membersInRoster = 0;
        // Assign all the information from the command to the variables
        this.args = args;
        String time = args[1];
        String timeZone = args[2];
        String rosterEvent = args[3];
        String limit = args[4];
        String rosterDesc = event.getMessage().getContentRaw().substring(desc.indexOf("(")+1, desc.indexOf(")"));
        if(event.getMessage().getMentionedUsers().isEmpty()){
            host = event.getMember().getAsMention();
        }else{
            host = event.getMessage().getMentionedUsers().get(0).getAsMention();
        }

        // Add all the above fields to the embed
        embed.setTitle(rosterEvent);
        embed.setDescription(rosterDesc);
        embed.addField("Host", ("" + host), true);
        embed.addField("Time", time + " " + timeZone, true);
        embed.addField("Going - `" + membersInRoster + "/" + limit + "`", "Placeholder", false);
        embed.addField("Maybe", "Placeholder", false);

        // Build, send the embed, and add the reactions
        // Rest action waits until the message has sent to perform the actions
        RestAction<Message> ra = event.getChannel().sendMessage(embed.build());
        Message messageEmbed = ra.complete();
        messageEmbed.addReaction("\u270B").queue();
        messageEmbed.addReaction("\uD83E\uDD14").queue();
        messageEmbed.addReaction("\uD83D\uDEAB").queue();
        id = messageEmbed.getId();

        DataBase.newRoster(id, date, time, timeZone, rosterEvent, limit, rosterDesc, host);
    }
}
