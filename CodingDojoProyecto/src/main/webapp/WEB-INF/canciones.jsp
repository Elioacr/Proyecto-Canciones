<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de Canciones</title>
    <link rel="stylesheet" href="/CSS/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5">
    <div class="row"><br>
				<h1 class="col-6"> Hola, ${nombre_usuario}</h1>
				<a class="col-2" href="/logout"> cerrar sesión </a>
			</div>
        <h1 class="mb-4 titulo">Todas las canciones de laboratorio</h1>
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Título</th>
                    <th scope="col">Número de Colaboradores</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cancion" items="${canciones}">
                    <tr>
                        <td><a href="/canciones/${cancion.id}">${cancion.titulo}</a>
                        <p>Genero: ${cancion.genero}</p></td>
                        <td>${cancion.colaboradores.size()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="/canciones/nuevo" class="btn btn-primary">
			Agregar una Cancion
		</a>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gybVuVV2Qek8UOkmnO9WjHG5to/i1zY7ScMIe6uHc9eOks+jF2" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-oBqDVmMz4fnFO9gybVuVV2Qek8UOkmnO9WjHG5to/i1zY7ScMIe6uHc9eOks+jF2" crossorigin="anonymous"></script>
</body>
</html>