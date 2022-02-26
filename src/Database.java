import java.sql.*;
import customer.Customer;

public class Database {
        Customer customer;

        String url = "jdbc:mysql://localhost:3306/hotel";
        String username = "root";
        String password = "";

        public boolean register(Customer cus) {
                boolean result = false;
                String[] person = checkCustomer(cus.getEmail());

                if(person[0] == null) {
                        try {
                                Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

                                String sql = "INSERT INTO `user` (name, password, email) VALUES (?, ?, ?)";

                                PreparedStatement statement = connection.prepareStatement(sql);
                                statement.setString(1, cus.getName());
                                statement.setString(2, cus.getPassword());
                                statement.setString(3, cus.getEmail());

                                int rowInserted = statement.executeUpdate();

                                if(rowInserted > 0) {
                                        result = true;
                                }else {
                                        result = false;
                                }
                        }catch(SQLException e) {
                                e.printStackTrace();
                        }
                }else {
                        result = false;
                }

                return result;
        }

        public String[] checkCustomer(String email) {
                String[] person = new String[3];

                try {
                        Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

                        String sql = "SELECT * FROM `user` WHERE email = ?";

                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, email);

                        ResultSet result = statement.executeQuery();

                        if(result.next()) {
                                person[0]  = result.getString("name");
                                person[1] = result.getString("password");
                                person[2] = result.getString("email");
                        }

                        connection.close();

                }catch(SQLException e) {
                        e.printStackTrace();
                }

                return person;
        }

}
