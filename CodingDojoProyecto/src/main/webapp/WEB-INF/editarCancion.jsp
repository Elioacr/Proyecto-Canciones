<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Cancion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-0">${cancion.titulo}</h1>
        <form:form action="/procesar/editar/cancion/${cancion.id}" method="POST" modelAttribute="cancion">
            <input type="hidden" name="_method" value="PUT"/>
            
            <div class="mb-3 row">
                <form:label path="titulo" class="col-sm-2 col-form-label">Título:</form:label>
                <div class="col-sm-10">
                    <form:input path="titulo" class="form-control" type="text" name="titulo" />
                    <form:errors path="titulo" class="alert alert-danger mt-1" />
                </div>
            </div>
            
            <div class="mb-3 row">
                <form:label path="genero" class="col-sm-2 col-form-label">Genero:</form:label>
                <div class="col-sm-10">
                    <form:input path="genero" class="form-control" type="text" name="genero"/>
                    <form:errors path="genero" class="alert alert-danger mt-1" />
                </div>
            </div>
            
            <div class="mb-3 row">
                <form:label path="letra" class="col-sm-2 col-form-label" >Letra:</form:label>
                <div class="col-sm-10">
            	<p>${cancion.letraOriginal}</p>
                    <form:input path="letra" class="form-control" type="text" name="letra"/>
                    <form:input path="letraOriginal" class="form-control" type="hidden" name="letraOriginal"/>
                    <form:errors path="letra" class="alert alert-danger mt-1" />
                </div>
            </div>

            <div class="d-flex justify-content-center mb-3">
                <a href="/canciones/${cancion.id}" class="btn btn-secondary me-2">Cancelar</a>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </div>
        </form:form>

        <form action="/eliminar/cancion/${cancion.id}" method="POST" class="mt-3">
            <input type="hidden" name="_method" value="DELETE"/>
            <c:if test="${cancion.usuario.id == id_usuario}">
				<button type="submit" class="btn btn-danger">Eliminar</button>
			</c:if>
            
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>