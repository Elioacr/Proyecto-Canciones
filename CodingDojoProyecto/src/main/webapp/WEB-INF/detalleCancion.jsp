<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalles de la cancion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-8">
                <h1 class="mb-0">${cancion.titulo}</h1>
                <p><strong>Creado por: ${cancion.usuario.nombre}</strong></p>
            </div>
            
            <div class="col-md-4 d-flex justify-content-end">
                <a href="/canciones" class="btn btn-link">Volver al panel</a>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col-md-4">
                <p>Genero: ${cancion.genero}</p>
                <br>
                <p>Letra:</p>
                <p>${cancion.letra}</p>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col-md-12">
                <a href="/editar/cancion/${cancion.id}" class="btn btn-primary">Contribuir</a>
            </div>
        </div>
        <div>
        <h1>Colaboradores</h1>
        	<c:forEach var="colaboracion" items="${cancion.colaboradores}">
        		<ul>
        			<li>${colaboracion.usuario.nombre}</li>
        		</ul>
        	</c:forEach>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>