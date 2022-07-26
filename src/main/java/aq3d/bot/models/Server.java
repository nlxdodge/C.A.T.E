package aq3d.bot.models;

public class Server {
    public int ID;
    public int SortIndex;
    public String Name;
    public String HostName;
    public String Port;
    public int UserCount;
    public int MaxUsers;
    public boolean State;
    public boolean Chat;
    public String Language;
    public String IP;
    public String LocalIP;
    public String LastUpdated;
    public int Connections;
    public int AlertLevel;
    public int Status;
    public String Region;
    public String InputQueueLength;
    public String AccessLevel;
    public String GEMQueueLength;

    public boolean exclude() {
        return Name.contains("Localhost") || Name.contains("Hidden") || Name.contains("Gold");
    }

    @Override
    public String toString() {
        return String.format("%s - %s | %s/%s", getIcon(), Name, UserCount, MaxUsers);
    }

    public String getIcon() {
        if (Name.contains("White"))
            return "⚪";
        if (Name.contains("Red"))
            return "🔴";
        if (Name.contains("Orange"))
            return "🟠";
        if (Name.contains("Gold"))
            return "🟡";
        if (Name.contains("Green"))
            return "🟢";
        if (Name.contains("Blue"))
            return "🔵";
        if (Name.contains("Purple"))
            return "🟣";
        if (Name.contains("Brown"))
            return "🟤";
        if (Name.contains("Black"))
            return "⚫";
        return "🔘";
    }
}
