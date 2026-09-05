package dao;

import conexion.Conexion;
import modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO encargada de realizar las operaciones de productos
 * en la base de datos MySQL.
 *
 * @author El Mordisco
 */
public class ProductoDAO {

    /**
     * Guarda un nuevo producto en la base de datos.
     *
     * @param producto producto que se desea registrar
     * @return true si el registro fue exitoso, false si ocurre un error
     */
    public boolean guardar(Producto producto) {

        String sql = "INSERT INTO producto "
                + "(nombre, categoria, precio, stock) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            // Se asignan los datos del producto a la consulta.
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCategoria());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());

            // Ejecuta el registro del producto.
            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return false;
        }
    }

    /**
     * Consulta todos los productos almacenados.
     *
     * @return lista de productos registrados
     */
    public List<Producto> listar() {

        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT * FROM producto";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Recorre los registros obtenidos de la base de datos.
            while (rs.next()) {

                Producto producto = new Producto();

                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCategoria(rs.getString("categoria"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));

                productos.add(producto);
            }

        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        return productos;
    }

    /**
     * Actualiza la información de un producto existente.
     *
     * @param producto producto con los datos actualizados
     * @return true si la actualización fue exitosa, false si ocurre un error
     */
    public boolean actualizar(Producto producto) {

        String sql = "UPDATE producto SET nombre=?, categoria=?, precio=?, stock=? "
                + "WHERE id_producto=?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            // Se asignan los nuevos datos del producto.
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCategoria());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getIdProducto());

            // Ejecuta la actualización.
            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un producto de la base de datos mediante su identificador.
     *
     * @param idProducto identificador del producto que se desea eliminar
     * @return true si la eliminación fue exitosa, false si ocurre un error
     */
    public boolean eliminar(int idProducto) {

        String sql = "DELETE FROM producto WHERE id_producto=?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            // Se establece el ID del producto que será eliminado.
            ps.setInt(1, idProducto);

            // Ejecuta la eliminación.
            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}