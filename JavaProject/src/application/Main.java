package application;
import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DB.getConnection();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Seller"
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentID)"
                    + "Values"
                    + "(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "anna");
            preparedStatement.setString(2, "anna@gmail.com");
            preparedStatement.setDate(3, new java.sql.Date(sdf.parse("02/01/2002").getTime()));
            preparedStatement.setDouble(4, 1750);
            preparedStatement.setInt(5, 3);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0){
                ResultSet set = preparedStatement.getGeneratedKeys();
                while (set.next()){
                    int id = set.getInt(1);
                    System.out.println("DONE, ID = " + id);
                }
            }
            else {
                System.out.println("NO ROWS AFFECTED");
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        catch (ParseException e){
            throw new DbException(e.getMessage());
        }
    }
}