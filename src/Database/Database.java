package Database;

import java.sql.*;

import customer.Customer;

public class Database {
    Customer customer;

    String url = "jdbc:mysql://localhost:3306/hotel";
    String username = "root";
    String password = "";

    public int register(Customer cus) {
        String[] person = checkUser(cus.getEmail());
        int rowInserted = 0;

        if (person[0] == null) {
            try {
                Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

                String sql = "INSERT INTO `user` (name, password, email, details) VALUES (?, ?, ?, ?)";

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, cus.getName());
                statement.setString(2, cus.getPassword());
                statement.setString(3, cus.getEmail());
                statement.setString(4, "customer");

                rowInserted = statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return 0;
        }

        return rowInserted;
    }

    public String[] checkUser(String email) {
        String[] person = new String[4];

        try {
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

            String sql = "SELECT * FROM `user` WHERE email = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                person[0] = result.getString("name");
                person[1] = result.getString("password");
                person[2] = result.getString("email");
                person[3] = result.getString("details");
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

}
