package stepDefinitions.jdbc;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseTesting {
    @Given("Establish new connection")
    public void establish_new_connection() {
        //List<String> namesFromDB = new ArrayList<>();
        String url = "jdbc:mariadb://3.141.20.110/my_jdbc_db";
        String user = "admin";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection to the database!");

            Statement statement =connection.createStatement();//Obj is used execute SQL query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");//fetch all raws,columns

            System.out.println("Employee Data:");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                String salary = resultSet.getString("salary");

                System.out.println("ID:" + id);
                System.out.println("name:" + name);
                System.out.println("department:" + department);
                System.out.println("salary:" + salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
