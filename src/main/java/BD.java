import javax.swing.*;
import java.sql.*;

public class BD {
    public BD() {
    }
    String error_register = "No se pudo registrar el habitante";
    String error_edit = "No se pudo editar el habitante";

    public static String Select_code_bd(String code_city) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/crud_joption";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM citys WHERE code_city = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1,  code_city); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet.next()) {
            code_city = resultSet.getString("code_city");
            return code_city;

        }
        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();
        return "";
    }

    public static String Search_one(String document) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/crud_joption";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM population WHERE document = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1,  document); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet.next()) {
            document = resultSet.getString("document");
            String cellphone = resultSet.getString("cellphone");
            String code_residence = resultSet.getString("code_residence");
            String code_city = resultSet.getString("code_city");
            String address = resultSet.getString("address");

            JOptionPane.showMessageDialog(null, "El documento es: " + document + " Celular: "
                    + cellphone + " Codigo de residencia: " + code_residence + " Codigo de ciudad: " + code_city +
                    " Direccion: " + address);

            return document;

        }
        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();
        return "";

    }

    public static void search() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/crud_joption";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM population");

        while(resultSet2.next()){
            String document = resultSet2.getString("document");
            String cellphone = resultSet2.getString("cellphone");
            String code_residence = resultSet2.getString("code_residence");
            String code_city = resultSet2.getString("code_city");
            String address = resultSet2.getString("address");

            JOptionPane.showMessageDialog(null, "El documento es: " + document + " Celular: "
                    + cellphone + " Codigo de residencia: " + code_residence + " Codigo de ciudad: " + code_city +
                    " Direccion: " + address);
        }
    }

    public static void Delete(String document) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/crud_joption";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        String sentenciaSQL = "DELETE FROM population WHERE document = ?";
        PreparedStatement prepareStatement = connection2.prepareStatement(sentenciaSQL);
        prepareStatement.setString(1, document);
        prepareStatement.executeUpdate();

        JOptionPane.showMessageDialog(null, "Habitante eliminado exitosamente.");
    }

    public void register(String document, String cellphone, String code_residence, String code_city, String address) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/crud_joption";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM population");

            // Sentencia INSERT
            String sql = "INSERT INTO population (document , cellphone, code_residence, code_city, address) VALUES (?, ?, ?, ?, ?)";

            // Preparar la sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, document);
            preparedStatement.setString(2, cellphone);
            preparedStatement.setString(3, code_residence);
            preparedStatement.setString(4, code_city);
            preparedStatement.setString(5, address);

            // Ejecutar la sentencia
            int files = preparedStatement.executeUpdate();

            if (files > 0) {
                JOptionPane.showMessageDialog(null, "Habitante agregado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, error_register);
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void editar(String document, String cellphone, String code_residence, String code_city, String address) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/crud_joption";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();
        String consulta = "UPDATE population SET cellphone = ?, code_residence = ?, code_city = ?, address = ? WHERE document = ?";

        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, cellphone);
        preparedStatement.setString(2, code_residence);
        preparedStatement.setString(3, code_city);
        preparedStatement.setString(4, address);
        preparedStatement.setString(5, document);

        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            JOptionPane.showMessageDialog(null, "Habitante editado exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, error_edit);
        }

        preparedStatement.close();
        connection2.close();
    }
}


