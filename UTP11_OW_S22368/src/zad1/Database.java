package zad1;

import java.lang.reflect.Method;
import java.sql.*;

public class Database {
    boolean exitOnException;
    DatabaseMetaData md;

    public Database(String url, TravelData travelData) {
        try (Connection con = DriverManager.getConnection(url)) {
//            md = con.getMetaData();
//            reportInfo();
            PreparedStatement insert = con.prepareStatement("INSERT INTO Offer(country, departureDate, returnDate)");

            Statement stmt = con.createStatement();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create() {
    }

    public void showGui() {
    }

    void reportInfo() throws SQLException {
        exitOnException = false;
        info("getDatabaseProductName");
        info("getDatabaseProductVersion");
        info("getDriverName");
        info("getURL");
        info("getUserName");

        info("supportsAlterTableWithAddColumn");
        info("supportsAlterTableWithDropColumn");
        info("supportsANSI92FullSQL");
        info("supportsBatchUpdates");
        info("supportsMixedCaseIdentifiers");
        info("supportsMultipleTransactions");
        info("supportsPositionedDelete");
        info("supportsPositionedUpdate");
        info("supportsSchemasInDataManipulation");
        info("supportsTransactions");

        System.out.println("ResultSet  TYPE_SCROLL_INSENSITIVE :" +
                md.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
        System.out.println("ResultSet  TYPE_SCROLL_SENSITIVE :" +
                md.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
        System.out.println("insertsAreDetected :" +
                md.insertsAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE));
        System.out.println("updatesAreDetected :" +
                md.updatesAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE));
    }

    @SuppressWarnings("unchecked")
    void info(String metName)  {
        Class mdc  = DatabaseMetaData.class;
        Class[] paramTypes =  { };
        Object[] params =  { };
        String infoTyp;
        if (metName.startsWith("get"))
            infoTyp = metName.substring(3,metName.length());
        else infoTyp = metName;
        try  {
            Method m = mdc.getDeclaredMethod(metName, paramTypes);
            System.out.println(infoTyp + ": " + m.invoke(md, params));
        } catch(Exception exc)  {
            System.out.println(exc);
        }
    }
}
