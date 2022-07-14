package application;
import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = DB.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Department");

            while (resultSet.next()){
                System.out.println(resultSet.getInt("ID")
                        + ", "
                        + resultSet.getString("Name"));
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
            DB.closeResulSet(resultSet);
            DB.closeConnection();
        }

    }
}