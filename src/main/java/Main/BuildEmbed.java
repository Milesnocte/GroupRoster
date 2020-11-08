package Main;
import Credentials.Credentials;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class BuildEmbed extends ListenerAdapter {

    long id;
    int numOfRoles;
    boolean runners;
    String roles[][];
    EmbedBuilder embed = new EmbedBuilder();
    String roleList, rosterEvent, time, timeZone, commander;
    String[] args;

    public BuildEmbed(MessageReceivedEvent event, String[] args) {
        this.args = args;
        rosterEvent = args[3];
        time = args[1];
        timeZone = args[2];
        commander = event.getMember().getAsMention();
        roleList = NumOfRoles(args);

        // ADD ALL THE FIELDS TO THE EMBED
        embed.setDescription("React the role you want to join to join the roster\nReact :no_entry_sign: to remove your role");
        embed.setFooter("Group Roster created by MilesNocte", event.getJDA().getUserById(Credentials.OWNER_ID).getAvatarUrl());
        embed.addField("Commander", (":crown: " + commander), false);
        embed.addField("Time", time + " " + timeZone, false);
        embed.addField("Event", rosterEvent, false);
        embed.addField("Roles", roleList, false);

        // BUILD AND SEND THE EMBED
        event.getChannel().sendMessage(embed.build()).queue(
                Sent -> {
                    id = Sent.getIdLong();
                    Sent.addReaction("Tank:705672828468330516").queue();
                    Sent.addReaction("Healer:705672828522987540").queue();
                    Sent.addReaction("StaminaDPS:705672828292169790").queue();
                    Sent.addReaction("MagicaDPS:705672828254552104").queue();
                    if (runners) {
                        Sent.addReaction("Runner:705672828258877450").queue();
                    }
                    Sent.addReaction("\uD83D\uDEAB").queue();
                }
        );
    }

    public String NumOfRoles(String[] args){

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
        return toRoleList(roles, numOfRoles);
    }

    public String toRoleList(String[][] roles, int numOfRoles){
        // ORGANIZE ALL THE ROLES INTO A SINGLE STRING SO THAT THEY CAN BE ADDED TO THE EMBED
        for(int p = 0; p < numOfRoles; p++){
            roleList += roles[p][0] + " " + roles[p][1] + "\n";
        }

        return roleList;
    }

}
