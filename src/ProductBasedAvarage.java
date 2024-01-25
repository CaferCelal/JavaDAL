import java.sql.*;

public class ProductBasedAvarage {
    static String username = "root";

    //Normalde burda gerçek şifrem var
    static String password = "mySQL password";
    static String dbURL = "jdbc:mysql://localhost:3306/mulakat";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        float toplamTutar = 0.0F;
        int ürünSayar =0;

        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            int i=0;
            for (i=2000;i<2007;i++) {

                String productNum = String.valueOf(i);
                resultSet = statement.executeQuery("SELECT BirimFiyat,Miktar FROM siparisler WHERE MalNumarası = " + productNum );

                while (resultSet.next()) {
                    toplamTutar = toplamTutar + (resultSet.getFloat("BirimFiyat") * resultSet.getInt("Miktar"));
                    ürünSayar = ürünSayar + resultSet.getInt("Miktar");
                }
                System.out.println(productNum + " mal numaralı ürünün ortalama fiyatı : " +toplamTutar/ürünSayar);
                toplamTutar =0;
                ürünSayar =0;
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
