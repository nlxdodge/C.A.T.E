package aq3d.bot.models;

public class Server {
  public Integer ID;
  public Integer SortIndex;
  public String Name;
  public String HostName;
  public String Port;
  public Integer UserCount;
  public Integer MaxUsers;
  public Boolean State;
  public Boolean Chat;
  public String Language;
  public String IP;
  public String LocalIP;
  public String LastUpdated;
  public Integer AccessLevel;
  public Integer BuildVersion;
  public Integer Connections;
  public Integer AlertLevel;
  public Integer InputQueueLength;
  public String GEMQueueLength;

  public Boolean exclude() {
    Boolean exclude = false;
    if (Name.contains("Localhost") || Name.contains("Hidden") || Name.contains("Gold")) {
      exclude = true;
    }
    return exclude;
  }

  @Override
  public String toString() {
    return String.format("%s - %s | %s/%s", getIcon(), Name, UserCount, MaxUsers);
  }

  public String getIcon() {
    if (Name.contains("White"))
      return "🔘";
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
    if (Name.contains("Grey"))
      return "🔘";
    return "🔘";
  }
}
