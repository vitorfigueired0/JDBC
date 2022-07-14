package application;
import db.DB;
import db.DbException;
import java.sql.*;

public class Main {

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DB.getConnection();
            preparedStatement = connection.prepareStatement(
                "UPDATE Seller"
                + "SET BaseSalary = 1900 "
                + "WHERE"
                + "(ID = 8)");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}