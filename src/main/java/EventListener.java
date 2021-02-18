import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import pattern.GuildV;

import javax.annotation.Nonnull;

public class EventListener extends ListenerAdapter {

    private GuildV[] guildVs = {new GuildV()};

    public EventListener() {

    }

    private GuildV getGuild(long id) {
        for (int i = 0; i < this.guildVs.length; i++) {
            if (id == this.guildVs[i].getGuildId()) {
                return this.guildVs[i];
            }
        }
        return null;
    }

    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        boolean root = false;
        boolean botAdmin=false;
        String message = event.getMessage().getContentRaw();
        GuildV guildV = getGuild(event.getGuild().getIdLong());
        String[] messageContent = message.split(" ");
        if (guildV == null) {
            return;
            //создает новую гильдию и сохраняет ее сделать
        }
        if (event.getMessage().isWebhookMessage()) {
            return;
        }
        if (messageContent.length < 1 || messageContent[0].equals("")) {
            return;
        }
        if (event.getMember().getUser().isBot()) {
            return;
        }
        if (guildV.isAdmin(event.getMember().getIdLong()) || event.getMember().isOwner()) {
            root = true;
        }
        if (message.charAt(0) == guildV.getPrefix()) {

            if (root) { //Команда для админов сервера
                if (messageContent[0].equals(guildV.getPrefix() + "help")) {
                    event.getMessage().getChannel().sendMessage("Кто-то звал на помощь? Без паники, Инквизитор V всегда на страже порядка!").queue();
                }
                if (messageContent.length < 2) {
                    return;
                }
                if (messageContent[0].equals(guildV.getPrefix() + "кто") && messageContent[1].equals( "я")) {
                    event.getMessage().getChannel().sendMessage("Я не анкаповец, кто скажет что я из Анкапа - выжгу не задумываясь").queue();
                }
                if (messageContent[0].equals(guildV.getPrefix() + "префикс")) {
                    guildV.setPrefix(messageContent[1].charAt(0));
                    event.getMessage().getChannel().sendMessage("Префикс сменён на " + guildV.getPrefix()).queue();
                }
                String test = String.valueOf(guildV.getPrefix()) + String.valueOf(guildV.getPrefix());
                if (messageContent[0].equals(test)) {
                    String messageForBot = "";
                    ;
                    for (int i = 1; i < messageContent.length; i++) {
                        messageForBot = messageForBot + " " + messageContent[i];
                    }
                    event.getMessage().delete().queue();
                    event.getMessage().getChannel().sendMessage(messageForBot).queue();
                }
            }

        }
    }

    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        String name = event.getMember().getEffectiveName();
        String id = event.getMember().getId();
        GuildV guildV = getGuild(event.getGuild().getIdLong());
        // event.getMember().
        long time = event.getMember().getTimeJoined().toEpochSecond();
        long time1 = event.getMember().getTimeCreated().toEpochSecond();

        String f = "**БЫЛ ЗАБАНЕН ПОЛЬЗОВАТЕЛЬ**\n"+"Имя: " + name + " id: " + id + "\nВремя присоединения: " + time + "\nВремя создания аккаунта: " + time1;
        if (time1 + 86400L * 1L >= time) {
            if (guildV.isBlackNick(event.getMember().getEffectiveName())) {
                event.getJDA().getTextChannelById(guildV.getLogChannel()).sendMessage(f).queue();
                new KickBan(event).start();
            }
        }
    }
}
