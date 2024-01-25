import java.sql.*;

public class MulakatAvarage {
    static String username = "root";

    //Normalde burda gerçek şifrem var
    static String password = "mySQL password";
    static String dbURL = "jdbc:mysql://localhost:3306/mulakat";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Float toplamTutar = 0.0F;
        int ürünSayar =0;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT BirimFiyat,Miktar FROM siparisler WHERE SiparisNo IN (1000, 1001, 1002)");

            while (resultSet.next()) {
                ürünSayar = ürünSayar +resultSet.getInt("Miktar");
                toplamTutar = toplamTutar + (resultSet.getFloat("BirimFiyat")*resultSet.getInt("Miktar"));
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
            float ortalamaFiyat=toplamTutar/ürünSayar;
            System.out.println(ortalamaFiyat);
        }
    }
}