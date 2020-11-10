package Main;
import Credentials.Credentials;
import Database.DataBase;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;
import java.sql.SQLException;
import java.util.function.Consumer;

public class BuildEmbed extends ListenerAdapter {

    String id;
    String[] args;
    int numOfRoles;
    boolean runners;
    String roleList;
    String roles[][];
    EmbedBuilder embed = new EmbedBuilder();

    public BuildEmbed(MessageReceivedEvent event, String[] args) throws SQLException, ClassNotFoundException {

        // Assign all the information from the command to the variables
        this.args = args;
        String time = args[1];
        String timeZone = args[2];
        String rosterEvent = args[3];
        roles = new String[numOfRoles][2];
        int numTanks = Integer.parseInt(args[4]);
        int numHealers = Integer.parseInt(args[5]);
        int numDps = Integer.parseInt(args[6]);
        int numRunners = Integer.parseInt(args[7]);
        String commander = event.getMember().getAsMention();
        numOfRoles = numTanks + numHealers + numDps + numRunners;
        roles = NumOfRoles(args);


        // Put the array into a string so that it can be set as a field in the embed
        roleList = "";
        for(int p = 0; p < numOfRoles; p++){
            roleList += roles[p][0] + " " + roles[p][1] + "\n";
        }

        // Add all the above fields to the embed
        embed.setDescription("React the role you want to join to join the roster\nReact :no_entry_sign: to remove your role");
        embed.setFooter("Group Roster created by MilesNocte", event.getJDA().getUserById(Credentials.OWNER_ID).getAvatarUrl());
        embed.addField("Commander", (":crown: " + commander), false);
        embed.addField("Time", time + " " + timeZone, false);
        embed.addField("Event", rosterEvent, false);
        embed.addField("Roles", roleList, false);

        // Build, send the embed, and add the reactions
        // Rest action waits until the message has sent to perform the actions
        RestAction<Message> ra = event.getChannel().sendMessage(embed.build());
        Message messageEmbed = ra.complete();
        messageEmbed.addReaction("Tank:705672828468330516").queue();
        messageEmbed.addReaction("Healer:705672828522987540").queue();
        messageEmbed.addReaction("StaminaDPS:705672828292169790").queue();
        messageEmbed.addReaction("MagicaDPS:705672828254552104").queue();
        messageEmbed.addReaction("MagicaDPS:705672828254552104").queue();
        if (runners) messageEmbed.addReaction("Runner:705672828258877450").queue();
        messageEmbed.addReaction("\uD83D\uDEAB").queue();
        id = messageEmbed.getId();

        // Save the info to the database
        DataBase db = new DataBase();
        String roleString = numTanks+"-"+numHealers+"-"+numDps+"-"+numRunners;
        String[] toDB = new String[]{roleList,roleString,rosterEvent,time,timeZone, commander};
        db.addUser(id, toDB);
    }

    public String[][] NumOfRoles(String[] args){

        // GET THE ARGUMENTS FROM THE ROSTER COMMAND
        roleList = "";
        int numTanks = Integer.parseInt(args[4]);
        int numHealers = Integer.parseInt(args[5]);
        int numDps = Integer.parseInt(args[6]);
        int numRunners = Integer.parseInt(args[7]);
        numOfRoles = numTanks + numHealers + numDps + numRunners;
        roles = new String[numOfRoles][2];

        // ADD A COLUMN TO THE ARRAY FOR EACH ROLE
        for(int k = 0; k < numTanks; k++){
            roles[k][0] = "<:Tank:705672828468330516>";
            roles[k][1] = "Empty";
        }
        for(int k = numTanks; k < numTanks + numHealers; k++){
            roles[k][0] = "<:Healer:705672828522987540>";
            roles[k][1] = "Empty";
        }
        for(int k = numTanks + numHealers; k < numTanks + numHealers + numDps; k++){
            roles[k][0] = "<:StaminaDPS:705672828292169790>";
            roles[k][1] = "Empty";
        }
        if(numRunners > 0) {
            runners = true;
            for (int k = numTanks + numHealers + numDps; k < numOfRoles; k++) {
                roles[k][0] = "<:Runner:705672828258877450>";
                roles[k][1] = "Empty";
            }
        }
        return roles;
    }
}
