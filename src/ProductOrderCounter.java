import java.sql.*;

public class ProductOrderCounter {
    static String username = "root";

    //Normalde burda gerçek şifrem var
    static String password = "mySQL password";
    static String dbURL = "jdbc:mysql://localhost:3306/mulakat";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            int i = 0;
            for(i=2000;i<2007;i++) {
                String prodcutNum =String.valueOf(i);
                resultSet = statement.executeQuery("SELECT SiparisNo,Miktar,MalNumarası FROM siparisler WHERE MalNumarası = " + prodcutNum);

                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("MalNumarası") + " mal numaralı üründen " + resultSet.getInt("SiparisNo")
                    + " numaralı siparişte " + resultSet.getInt("Miktar") + " adet bulunmaktadır.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
