package pattern;

public class GuildV {
    private long[] adminId={516575984158375938L,331853322522722304L,389821356918702080L};
    private String[] blackNick={"grumm9690"};
    private long logChannel=811629627734622208L;
    private char prefix = '%';
    private long guildId=533633242167771172L;

    public boolean isAdmin(long id) {
        for (int i = 0; i < this.adminId.length; i++) {
            if (id == this.adminId[i]) {
                return true;
            }
        }
        return false;
    }
    public long getGuildId(){
        return this.guildId;
    }
    public boolean isBlackNick(String nick) {
        for (int i = 0; i < this.blackNick.length; i++) {
            if (nick.equalsIgnoreCase(this.blackNick[i])) {
                return true;
            }
        }
        return false;
    }

    public void setLogChannel(long channel) {
        this.logChannel = channel;
    }

    public long getLogChannel() {
        return this.logChannel;
    }

    public void setPrefix(char c) {
        this.prefix = c;
    }

    public char getPrefix() {
        return this.prefix;
    }
}
