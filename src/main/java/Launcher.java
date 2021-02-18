import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Launcher {

    public static void main(String[] args) throws LoginException, InterruptedException {
      methA();
    }

    private static void methA() throws LoginException, InterruptedException {
        String token = "NzgzNTg1ODA5ODUyMzk5NjY2.X8c5Jw.N-Yv628dviETOD-LSXb71xduD0A";
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        JDA jda = builder.build();
        jda.getPresence().setActivity(Activity.watching("Джон Уик"));
        jda.addEventListener(new EventListener());
        Thread.sleep(4000L);
        //jda.getTextChannelById(569839230038048785L).sendMessage("Запустилось").queue();
    }

}
