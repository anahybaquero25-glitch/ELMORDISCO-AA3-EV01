package servlet;

import dao.ProductoDAO;
import modelo.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet encargado de controlar las solicitudes relacionadas
 * con la gestión de productos del sistema El Mordisco.
 *
 * Permite registrar, consultar, actualizar y eliminar productos.
 *
 * @author El Mordisco
 */
@WebServlet("/ProductoServlet")
public class ProductoServlet extends HttpServlet {

    private ProductoDAO productoDAO;

    /**
     * Inicializa el objeto DAO utilizado para acceder
     * a la información de los productos.
     */
    @Override
    public void init() {
        productoDAO = new ProductoDAO();
    }

    /**
     * Atiende las solicitudes GET.
     *
     * Se utiliza principalmente para consultar y mostrar
     * la lista de productos.
     *
     * @param request solicitud recibida desde la página web
     * @param response respuesta enviada al navegador
     * @throws ServletException si ocurre un error del servlet
     * @throws IOException si ocurre un error de entrada o salida
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        // Consulta y muestra todos los productos registrados.
        if ("listar".equals(accion)) {

            List<Producto> productos = productoDAO.listar();

            // Envía la lista de productos a la página JSP.
            request.setAttribute("productos", productos);

            request.getRequestDispatcher("productos.jsp")
                    .forward(request, response);

        } else {

            // Si no se especifica una acción, muestra el formulario.
            response.sendRedirect("producto.html");
        }
    }

    /**
     * Atiende las solicitudes POST.
     *
     * Se utiliza para registrar, actualizar y eliminar productos.
     *
     * @param request solicitud recibida desde el formulario
     * @param response respuesta enviada al navegador
     * @throws ServletException si ocurre un error del servlet
     * @throws IOException si ocurre un error de entrada o salida
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        // ELIMINAR PRODUCTO
        if ("eliminar".equals(accion)) {

            int idProducto = Integer.parseInt(
                    request.getParameter("idProducto")
            );

            productoDAO.eliminar(idProducto);

        } else {

            // Crear un objeto para almacenar los datos del producto.
            Producto producto = new Producto();

            // Si la acción es actualizar, se obtiene el ID del producto.
            if ("actualizar".equals(accion)) {

                int idProducto = Integer.parseInt(
                        request.getParameter("idProducto")
                );

                producto.setIdProducto(idProducto);
            }

            // Obtener los datos enviados desde el formulario web.
            producto.setNombre(request.getParameter("nombre"));
            producto.setCategoria(request.getParameter("categoria"));
            producto.setPrecio(
                    Double.parseDouble(request.getParameter("precio"))
            );
            producto.setStock(
                    Integer.parseInt(request.getParameter("stock"))
            );

            // ACTUALIZAR PRODUCTO
            if ("actualizar".equals(accion)) {

                productoDAO.actualizar(producto);

            } else {

                // GUARDAR NUEVO PRODUCTO
                productoDAO.guardar(producto);
            }
        }

        // Después de cualquier operación, vuelve a mostrar la lista.
        response.sendRedirect("ProductoServlet?accion=listar");
    }
}