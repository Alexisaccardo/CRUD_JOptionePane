import javax.swing.*;
import java.sql.SQLException;

public class Exercise {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        String error_register = "error, campos vacios o nulos";
        String error_select = "El codigo ingresado no coincide con el codigo de las ciudades registradas";
        String error_edit = "El codigo ingresado no coincide con el codigo de las ciudades registradas";
        String error_search = "El documento ingresado no coincide con un habitante registrado";

        JOptionPane.showMessageDialog(null, "GESTION CRUD");

        String answer = JOptionPane.showInputDialog("Deseas registar habitante, editar habitante, buscar un habitante, buscar todos los habitantes o eliminar habitantes?: ");

        if (answer.equalsIgnoreCase(
                "registrar")) {

            JOptionPane.showMessageDialog(null, "Registrar habitante: ");

            String document = JOptionPane.showInputDialog("Ingrese su numero de documento: ");
            String cellphone = JOptionPane.showInputDialog("Ingrese su celular:");
            String code_residence = JOptionPane.showInputDialog("Ingrese el codigo de residencia: ");
            String code_city = JOptionPane.showInputDialog("Ingrese el codigo de la ciudad: ");
            String address = JOptionPane.showInputDialog("Ingrese su direccion: ");

            if (document == null || document.equals("") || document.length() < 0 || cellphone == null
                    || cellphone.equals("") || cellphone.length() < 0 || code_residence == null
                    || code_residence.equals("") || code_residence.length() < 0 || code_city == null
                    || code_city.equals("") || code_city.length() < 0 || address == null
                    || address.equals("") || address.length() < 0) {

                JOptionPane.showMessageDialog(null, error_register);
            } else {
                BD bd = new BD();
                String code_BD = BD.Select_code_bd(code_city);

                if (code_BD.equals("")) {
                    JOptionPane.showMessageDialog(null, error_select);

                } else {
                    bd = new BD();
                    bd.register(document, cellphone, code_residence, code_city, address);

                }
            }

        }
        if (answer.equalsIgnoreCase("editar")) {

            JOptionPane.showMessageDialog(null, "Editar habitante: ");

            String document = JOptionPane.showInputDialog("Ingrese el numero de documento: ");
            String cellphone = JOptionPane.showInputDialog("Actualice el numero de celular:");
            String code_residence = JOptionPane.showInputDialog("Actualice el codigo de residencia: ");
            String code_city = JOptionPane.showInputDialog("Actualice el codigo de la ciudad: ");
            String address = JOptionPane.showInputDialog("Actualice su direccion: ");


            if (document == null || document.equals("") || document.length() < 0 || cellphone == null
                    || cellphone.equals("") || cellphone.length() < 0 || code_residence == null
                    || code_residence.equals("") || code_residence.length() < 0 || code_city == null
                    || code_city.equals("") || code_city.length() < 0 || address == null
                    || address.equals("") || address.length() < 0) {

                JOptionPane.showMessageDialog(null, "error, campos vacios o nulos");
            } else {
                BD bd = new BD();
                String code_BD = BD.Select_code_bd(code_city);

                if (code_BD.equals("")) {
                    JOptionPane.showMessageDialog(null, error_edit);

                } else {
                    bd = new BD();
                    bd.editar(document, cellphone, code_residence, code_city, address);

                }

            }
        }
        if (answer.equalsIgnoreCase("buscar un habitante")){

            String document = JOptionPane.showInputDialog("Ingrese el numero de documento: ");

            if (document.equals("")) {
                JOptionPane.showMessageDialog(null, error_search);
            }else{

            }
            BD bd = new BD();
            document = BD.Search_one(document);
        }
        if (answer.equalsIgnoreCase("buscar todos")){

            BD bd = new BD();
            BD.search();
        }
        if (answer.equalsIgnoreCase("Eliminar")){

            String document = JOptionPane.showInputDialog("Ingrese el documento del habitante que deseas eliminar? ");

            BD bd = new BD();
            BD.Delete(document);

        }
    }
}