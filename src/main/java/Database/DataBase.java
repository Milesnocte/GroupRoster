package Database;

import java.sql.*;

public class DataBase{

    private static Connection connect = null;
    private static boolean hasData = false;

    public ResultSet displayRosters() throws ClassNotFoundException, SQLException {
        if(connect == null) getConnection();

        Statement statement = connect.createStatement();
        ResultSet result = statement.executeQuery("SELECT messageId, roles, numOfRoles, event, time, timezone, commander FROM roster");
        System.out.println("Connected!");
        return result;
    }

    public void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\GroupRoster.db");
        System.out.println("Connecting..");
        initialise();
    }

    public void initialise() throws SQLException {
        System.out.println("Initializing..");
        if(!hasData){
            hasData = true;

            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='roster'");

            if( !result.next() ){
                System.out.println("Table not found. Building the Roster table.");
                // Build the table
                Statement statement2 = connect.createStatement();
                statement2.execute("CREATE TABLE roster(messageId INTEGER," + "roles TEXT,"
                        + "numOfRoles TEXT," + "event TEXT," + "time TEXT," + "timezone TEXT," + "commander TEXT);");

                PreparedStatement prepared = connect.prepareStatement("INSERT INTO roster values(?,?,?,?,?,?,?);");
                prepared.setInt(1,2556523);
                prepared.setString(2,"Example, put your array into a toString");
                prepared.setString(3,"Tanks-healers-magica-stamina-runners");
                prepared.setString(4,"dungeon");
                prepared.setString(5,"2:00");
                prepared.setString(6,"EST");
                prepared.setString(7,"as mention toString");
                prepared.execute();

            }
        }
    }
    public void addUser(int m_id, String[] rosterVars) throws SQLException, ClassNotFoundException {

        if(connect == null) getConnection();

        PreparedStatement prepared = connect.prepareStatement("INSERT INTO roster values(?,?,?,?,?,?,?);");
        prepared.setInt(1,m_id);
        prepared.setString(2,rosterVars[0]);
        prepared.setString(3,rosterVars[1]);
        prepared.setString(4,rosterVars[2]);
        prepared.setString(5,rosterVars[3]);
        prepared.setString(6,rosterVars[4]);
        prepared.setString(7,rosterVars[5]);
        prepared.execute();
    }

}
