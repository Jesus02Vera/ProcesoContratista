/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package procesocontratista;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class ProcesoContratista {

    public static void main(String[] args) throws SQLException {

        String usuario = "root";
        String password = "031208";
        String url = "jdbc:mysql://localhost:3306/condominio";

        while (true) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conexion = DriverManager.getConnection(url, usuario, password);
                Statement st = conexion.createStatement();

                System.out.println("\nPERSONAS");
                ResultSet resultadoPersonas = st.executeQuery("select * from personas");
                System.out.println("\nID\tNOMBRE\tAPELLIDO\tEMAIL");
                while (resultadoPersonas.next()) {
                    System.out.println(resultadoPersonas.getInt("personas_id")
                            + "\t" + resultadoPersonas.getString("personas_nombre")
                            + "\t" + resultadoPersonas.getString("personas_apellido")
                            + "\t" + resultadoPersonas.getString("personas_email"));
                }

                System.out.println("\nCONTRATISTAS");
                ResultSet resultadoContratistas = st.executeQuery("select * from contratistas");
                System.out.println("\nID\tCARGO\tID PERSONA");
                while (resultadoContratistas.next()) {
                    System.out.println(resultadoContratistas.getInt("contratistas_id")
                            + "\t" + resultadoContratistas.getString("contratistas_cargo")
                            + "\t" + resultadoContratistas.getInt("personas_personas_id"));
                }

                System.out.println("\n1. Insertar contratista");
                System.out.println("2. Editar contratista");
                System.out.println("3. Eliminar contratista");
                System.out.println("4. Consultar personas");
                System.out.println("5. Consultar contratistas");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opción:"));

                switch (opcion) {
                    case 1 -> {
                        String idPersona = JOptionPane.showInputDialog("Introduzca el ID de la persona: ");
                        String cargo = JOptionPane.showInputDialog("Introduzca el cargo del contratista: ");
                        st.executeUpdate("Insert into contratistas (contratistas_cargo, personas_personas_id) values('" + cargo + "'," + idPersona + ")");
                        JOptionPane.showMessageDialog(null, "Contratista registrado correctamente");
                    }
                    case 2 -> {
                        String idContratista = JOptionPane.showInputDialog("Introduzca el ID del contratista a editar: ");
                        String nuevoCargo = JOptionPane.showInputDialog("Introduzca el nuevo cargo del contratista: ");
                        st.executeUpdate("Update contratistas set contratistas_cargo = '" + nuevoCargo + "' where contratistas_id = " + idContratista);
                        JOptionPane.showMessageDialog(null, "Contratista editado correctamente");
                    }
                    case 3 -> {
                        String idContratistaEliminar = JOptionPane.showInputDialog("Introduzca el ID del contratista a eliminar: ");
                        st.executeUpdate("Delete from contratistas where contratistas_id = " + idContratistaEliminar);
                        JOptionPane.showMessageDialog(null, "Contratista eliminado correctamente");
                    }
                    case 4 -> {
                        String idPersonaConsultar = JOptionPane.showInputDialog("Introduzca el ID de la persona a consultar: ");
                        ResultSet resultadoPersona = st.executeQuery("select * from personas where personas_id = " + idPersonaConsultar);
                        while (resultadoPersona.next()) {
                            System.out.println("\nID\tNOMBRE\tAPELLIDO\tEMAIL");
                            System.out.println(resultadoPersona.getInt("personas_id")
                                    + "\t" + resultadoPersona.getString("personas_nombre")
                                    + "\t" + resultadoPersona.getString("personas_apellido")
                                    + "\t" + resultadoPersona.getString("personas_email"));
                        }
                    }

                    case 5 -> {
                        String idContratistaConsultar = JOptionPane.showInputDialog("Introduzca el ID del contratista a consultar: ");
                        ResultSet resultadoContratista = st.executeQuery("select * from contratistas where contratistas_id = " + idContratistaConsultar);
                        while (resultadoContratista.next()) {
                            System.out.println("\nID\tCARGO\tID PERSONA");
                            System.out.println(resultadoContratista.getInt("contratistas_id")
                                    + "\t" + resultadoContratista.getString("contratistas_cargo")
                                    + "\t" + resultadoContratista.getInt("personas_personas_id"));
                        }
                    }

                    case 6 -> System.exit(0);

                    default -> JOptionPane.showMessageDialog(null, "Opción no válida");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProcesoContratista.class.getName());
            }
        }
    }
}
