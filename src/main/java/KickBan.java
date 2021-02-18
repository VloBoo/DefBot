import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;

public class KickBan extends Thread {
    private GuildMemberJoinEvent event;

    public KickBan(GuildMemberJoinEvent event) {
        this.event = event;
    }

    public void run() {
        try {
            Thread.sleep(1000L * 1L);
            event.getMember().getUser().openPrivateChannel().complete().sendMessage("Вы были забанены модератором `VloBo#4158` по причине: `фейк`").queue();
            event.getJDA().getTextChannelById(811629627734622208L).sendMessage("Test");
            event.getMember().ban(1, "фейк").queue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
