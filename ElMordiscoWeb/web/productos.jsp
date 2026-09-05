<%@page import="java.util.List"%>
<%@page import="modelo.Producto"%>

<!DOCTYPE html>
<html>
<head>
    <title>El Mordisco - Lista de Productos</title>
    <meta charset="UTF-8">
</head>

<body>

    <h1>EL MORDISCO</h1>
    <h2>Lista de Productos</h2>

    <table border="1">

        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Categoría</th>
            <th>Precio</th>
            <th>Stock</th>
        </tr>

        <%
            List<Producto> productos =
                    (List<Producto>) request.getAttribute("productos");

            if (productos != null) {
                for (Producto producto : productos) {
        %>

        <tr>
            <td><%= producto.getIdProducto() %></td>
            <td><%= producto.getNombre() %></td>
            <td><%= producto.getCategoria() %></td>
            <td><%= producto.getPrecio() %></td>
            <td><%= producto.getStock() %></td>
        </tr>

        <%
                }
            }
        %>

    </table>

    <br>

    <a href="producto.html">Volver a Gestión de Productos</a>

</body>
</html>