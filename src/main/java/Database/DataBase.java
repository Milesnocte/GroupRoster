package Database;

import java.sql.*;

public class DataBase{

    private static Connection connect = null;
    private static boolean hasData = false;

    public void databaseCycle() throws Exception {
        System.out.println("Connecting to database...");
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        initialise();
        System.out.println("Database cycled\nClosing Database");
        connect.close();
    }

    public void initialise() throws SQLException {
        System.out.println("Initializing DataBase..");
        if(!hasData){
            hasData = true;

            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='roster'");

            if( !result.next() ){
                System.out.println("Table not found. Building the Roster table.");
                // Build the table
                Statement statement2 = connect.createStatement();
                statement2.execute("CREATE TABLE roster(messageId TEXT," + "going TEXT,"
                        + "maybe TEXT," + "date TEXT," + "time TEXT," + "timezone TEXT," + "event TEXT,"
                        + "memLimit TEXT," + "desc TEXT," + "host TEXT);");

                PreparedStatement prepared = connect.prepareStatement("INSERT INTO roster values(?,?,?,?,?,?,?,?,?,?);");
                prepared.setInt(1,2556523);
                prepared.setString(2,"@me, @him, @her");
                prepared.setString(3,"@them");
                prepared.setString(4,"01/14");
                prepared.setString(5,"2:00PM");
                prepared.setString(6,"EST");
                prepared.setString(7,"AmongUs");
                prepared.setString(8,"10");
                prepared.setString(9,"lets play some among us");
                prepared.setString(10,"@milesnocte");
                prepared.execute();
            }
        }
    }

    public static void newRoster(String messageId, String date, String time, String timezone
            , String event, String memLimit, String desc, String host) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        PreparedStatement prepared = null;
        prepared = connect.prepareStatement("INSERT INTO roster values(?,?,?,?,?,?,?,?,?,?);");
        prepared.setString(1,messageId);
        prepared.setString(2,null);
        prepared.setString(3,null);
        prepared.setString(4,date);
        prepared.setString(5,time);
        prepared.setString(6,timezone);
        prepared.setString(7,event);
        prepared.setString(8,memLimit);
        prepared.setString(9,desc);
        prepared.setString(10,host);
        prepared.execute();
        connect.close();
    }

    // GUILD EVENTS MESSAGE ID
    public void setGuildeventsId(String gID, String eventsId) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        PreparedStatement prepared = null;
        prepared = connect.prepareStatement("UPDATE roster SET messageId = '" + eventsId + "' WHERE GuildID = '" + gID + "';");
        prepared.execute();
        connect.close();
    }
    public String getGuildeventsId(String gID) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        Statement prepared = connect.createStatement();
        ResultSet guildPrefix = prepared.executeQuery("SELECT messageId FROM roster WHERE GuildID = " + gID +";");
        String returnString = guildPrefix.getString("messageId");
        connect.close();
        return returnString;
    }

    // GOING
    public void setGoing(String gID, String eventsId) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        PreparedStatement prepared = null;
        prepared = connect.prepareStatement("UPDATE roster SET going = '" + eventsId + "' WHERE GuildID = '" + gID + "';");
        prepared.execute();
        connect.close();
    }
    public String getGoing(String gID) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        Statement prepared = connect.createStatement();
        ResultSet guildPrefix = prepared.executeQuery("SELECT going FROM roster WHERE GuildID = " + gID +";");
        String returnString = guildPrefix.getString("going");
        connect.close();
        return returnString;
    }

    // MAYBE
    public void setMaybe(String gID, String eventsId) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        PreparedStatement prepared = null;
        prepared = connect.prepareStatement("UPDATE roster SET maybe = '" + eventsId + "' WHERE GuildID = '" + gID + "';");
        prepared.execute();
        connect.close();
    }
    public String getMaybe(String gID) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        Statement prepared = connect.createStatement();
        ResultSet guildPrefix = prepared.executeQuery("SELECT maybe FROM roster WHERE GuildID = " + gID +";");
        String returnString = guildPrefix.getString("maybe");
        connect.close();
        return returnString;
    }

    // DATE
    public void setDate(String gID, String eventsId) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        PreparedStatement prepared = null;
        prepared = connect.prepareStatement("UPDATE roster SET date = '" + eventsId + "' WHERE GuildID = '" + gID + "';");
        prepared.execute();
        connect.close();
    }
    public String getDate(String gID) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        Statement prepared = connect.createStatement();
        ResultSet guildPrefix = prepared.executeQuery("SELECT date FROM roster WHERE GuildID = " + gID +";");
        String returnString = guildPrefix.getString("date");
        connect.close();
        return returnString;
    }

    // TIME
    public void setTime(String gID, String eventsId) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        PreparedStatement prepared = null;
        prepared = connect.prepareStatement("UPDATE roster SET time = '" + eventsId + "' WHERE GuildID = '" + gID + "';");
        prepared.execute();
        connect.close();
    }
    public String getTime(String gID) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        Statement prepared = connect.createStatement();
        ResultSet guildPrefix = prepared.executeQuery("SELECT time FROM roster WHERE GuildID = " + gID +";");
        String returnString = guildPrefix.getString("time");
        connect.close();
        return returnString;
    }

    // TIMEZONE
    public void setTimeZ(String gID, String eventsId) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        PreparedStatement prepared = null;
        prepared = connect.prepareStatement("UPDATE roster SET timezone = '" + eventsId + "' WHERE GuildID = '" + gID + "';");
        prepared.execute();
        connect.close();
    }
    public String getTimeZ(String gID) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        Statement prepared = connect.createStatement();
        ResultSet guildPrefix = prepared.executeQuery("SELECT timezone FROM roster WHERE GuildID = " + gID +";");
        String returnString = guildPrefix.getString("timezone");
        connect.close();
        return returnString;
    }

    // EVENT
    public void setEvent(String gID, String eventsId) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        PreparedStatement prepared = null;
        prepared = connect.prepareStatement("UPDATE roster SET event = '" + eventsId + "' WHERE GuildID = '" + gID + "';");
        prepared.execute();
        connect.close();
    }
    public String getEvent(String gID) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        Statement prepared = connect.createStatement();
        ResultSet guildPrefix = prepared.executeQuery("SELECT event FROM roster WHERE GuildID = " + gID +";");
        String returnString = guildPrefix.getString("event");
        connect.close();
        return returnString;
    }

    // MEMBER LIMIT
    public void setMemLimit(String gID, String eventsId) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        PreparedStatement prepared = null;
        prepared = connect.prepareStatement("UPDATE roster SET memLimit = '" + eventsId + "' WHERE GuildID = '" + gID + "';");
        prepared.execute();
        connect.close();
    }
    public String getMemLimit(String gID) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        Statement prepared = connect.createStatement();
        ResultSet guildPrefix = prepared.executeQuery("SELECT memLimit FROM roster WHERE GuildID = " + gID +";");
        String returnString = guildPrefix.getString("memLimit");
        connect.close();
        return returnString;
    }

    // DESCRIPTION
    public void setDesc(String gID, String eventsId) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        PreparedStatement prepared = null;
        prepared = connect.prepareStatement("UPDATE roster SET desc = '" + eventsId + "' WHERE GuildID = '" + gID + "';");
        prepared.execute();
        connect.close();
    }
    public String getDesc(String gID) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        Statement prepared = connect.createStatement();
        ResultSet guildPrefix = prepared.executeQuery("SELECT desc FROM roster WHERE GuildID = " + gID +";");
        String returnString = guildPrefix.getString("desc");
        connect.close();
        return returnString;
    }

    // HOST
    public void setHost(String gID, String eventsId) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        PreparedStatement prepared = null;
        prepared = connect.prepareStatement("UPDATE roster SET host = '" + eventsId + "' WHERE GuildID = '" + gID + "';");
        prepared.execute();
        connect.close();
    }
    public String getHost(String gID) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        Statement prepared = connect.createStatement();
        ResultSet guildPrefix = prepared.executeQuery("SELECT host FROM roster WHERE GuildID = " + gID +";");
        String returnString = guildPrefix.getString("host");
        connect.close();
        return returnString;
    }

}
