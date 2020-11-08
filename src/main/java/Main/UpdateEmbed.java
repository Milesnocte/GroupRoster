package Main;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;

public class UpdateEmbed {
    public UpdateEmbed(GuildMessageReactionAddEvent event){
        event.getReaction().removeReaction().queue();
        switch(event.getReactionEmote().toString()){
            case "RE:Tank(705672828468330516)":
                break;
            case "RE:Healer(705672828522987540)":
                break;
            case "RE:StaminaDPS(705672828292169790)":
                break;
            case "RE:MagicaDPS(705672828254552104)":
                break;
            case "RE:Runner(705672828258877450)":
                break;
            case "U+1f6ab":
                break;
        }
    }
    public void UpdateTank(){

    }
    public void UpdateHealer(){

    }
    public void UpdateDPS(){

    }
    public void UpdateRunner(){

    }
    public void RemoveMember(){

    }
}
