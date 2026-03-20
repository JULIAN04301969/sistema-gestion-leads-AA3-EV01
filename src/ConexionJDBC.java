import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionJDBC {

    static final String URL = "jdbc:mysql://localhost:3306/sistema_gestion_leads?useSSL=false&serverTimezone=UTC";
    static final String USUARIO = "root";
    static final String CONTRASENA = "JeOl0430";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexion exitosa a sistema_gestion_leads");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de conexion: " + e.getMessage());
        }
        return conexion;
    }

    // CREATE
    public static void insertarLead(String nombre, String email, String estado) {
        String sql = "INSERT INTO `lead` (nombre, email, fecha_registro, estado, id_fuente, id_prioridad, id_usuario_creador) VALUES (?, ?, CURDATE(), ?, 1, 2, 1)";
        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, estado);
            ps.executeUpdate();
            System.out.println("Lead insertado: " + nombre);
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    // READ
    public static void listarLeads() {
        String sql = "SELECT id_lead, nombre, email, estado FROM `lead`";
        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            System.out.println("\n--- LISTA DE LEADS ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_lead") +
                    " | Nombre: " + rs.getString("nombre") +
                    " | Email: " + rs.getString("email") +
                    " | Estado: " + rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
    }

    // UPDATE
    public static void actualizarEstadoLead(int idLead, String nuevoEstado) {
        String sql = "UPDATE `lead` SET estado = ? WHERE id_lead = ?";
        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idLead);
            ps.executeUpdate();
            System.out.println("Lead " + idLead + " actualizado a estado: " + nuevoEstado);
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    // DELETE
    public static void eliminarLead(int idLead) {
        String sql = "DELETE FROM `lead` WHERE id_lead = ?";
        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idLead);
            ps.executeUpdate();
            System.out.println("Lead " + idLead + " eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTION DE LEADS ===\n");

        // READ inicial
        listarLeads();

        // CREATE
        insertarLead("Prueba JDBC", "prueba@test.com", "Nuevo");

        // READ tras insertar
        listarLeads();

        // UPDATE
        actualizarEstadoLead(1, "En proceso");

        // READ tras actualizar
        listarLeads();

        // DELETE
        eliminarLead(6);

        // READ final
        listarLeads();
    }
}