package Main;
import Credentials.Credentials;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class BuildEmbed {

    long id;
    boolean runners;
    String roles[][];
    EmbedBuilder embed;
    String roleList, rosterEvent, time, timeZone, commander;

    public BuildEmbed(MessageReceivedEvent event, String[] args) {

        rosterEvent= args[3];
        time= args[1];
        timeZone = args[2];
        commander = event.getMember().getAsMention();
        embed = new EmbedBuilder();

        roleList = NumOfRoles(args);

        embed.setDescription("React the role you want to join to join the roster\nReact :no_entry_sign: to remove your role");
        embed.setFooter("Group Roster created by MilesNocte", event.getJDA().getUserById(Credentials.OWNER_TOKEN).getAvatarUrl());
        embed.addField("Commander", (":crown: " + commander), false);
        embed.addField("Time", time + " " + timeZone, false);
        embed.addField("Event", rosterEvent, false);
        embed.addField("Roles", roleList, false);

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

        roleList = "";

        int numTanks = Integer.parseInt(args[4]);
        int numHealers = Integer.parseInt(args[5]);
        int numDps = Integer.parseInt(args[6]);
        int numRunners = Integer.parseInt(args[7]);
        int numOfRoles = numTanks + numHealers + numDps + numRunners;

        roles = new String[numOfRoles][2];

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

        for(int p = 0; p < numOfRoles; p++){
            roleList += roles[p][0] + " " + roles[p][1] + "\n";
        }
        return roleList;
    }
}
