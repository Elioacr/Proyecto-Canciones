<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title> Login y Registro </title>
		<link rel="stylesheet" href="/CSS/style.css">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	</head>
	<body>
		<div class="container">
			<div class="row">
			<br>
				<h1 class="titulo text-center"> Laboratorio de Letras </h1>
				<div class="col-6">
					<h1 class="text-secondary mt-4"> Registrarte </h1>
					<form:form action="/registrar/usuario" method="POST" modelAttribute="usuario">
						<div class="mt-4">
							<form:label class="form-label" path="nombre">
								Nombre:
							</form:label>
							<form:input class="form-control mb-4" path="nombre" type="text" name="nombre"/>
							<div>
								<form:errors class="alert alert-danger" path="nombre"/>
							</div>
						</div>
						<div class="mt-4">
							<form:label class="form-label" path="correo">
								Correo:
							</form:label>
							<form:input class="form-control mb-4" path="correo" type="text" name="correo"/>
							<div>
								<form:errors class="alert alert-danger" path="correo"/>
							</div>
						</div>
						<div class="mt-4">
							<form:label class="form-label" path="contraseña">
								Contraseña:
							</form:label>
							<form:input class="form-control mb-4" path="contraseña" type="password" name="contraseña"/>
							<div>
								<form:errors class="alert alert-danger" path="contraseña"/>
							</div>
						</div>
						<div class="mt-4">
							<form:label class="form-label" path="confirmarContraseña">
								Confirmar contraseña:
							</form:label>
							<form:input class="form-control mb-4" path="confirmarContraseña" type="password" name="confirmarContraseña"/>
							<div>
								<form:errors class="alert alert-danger" path="confirmarContraseña"/>
							</div>
						</div>
						<button class="btn btn-outline-secondary mt-4 float-end">
							Registrarte
						</button>
					</form:form>
				</div>
				<div class="col-6">
					<h1 class="text-secondary mt-4"> Iniciar Sesión </h1>
					<form:form action="/procesa/login" method="POST" modelAttribute="loginUsuario">
						<div class="mt-4">
							<form:label class="form-label" path="usuarioCorreo">
								Correo:
							</form:label>
							<form:input class="form-control mb-4" path="usuarioCorreo" type="text" name="usuarioCorreo"/>
							<div>
								<form:errors class="alert alert-danger" path="usuarioCorreo"/>
							</div>
						</div>
						<div class="mt-4">
							<form:label class="form-label" path="usuarioContraseña">
								Contraseña:
							</form:label>
							<form:input class="form-control mb-4" path="usuarioContraseña" type="password" name="usuarioContraseña"/>
							<div>
								<form:errors class="alert alert-danger" path="usuarioContraseña"/>
							</div>
						</div>
						<button class="btn btn-outline-secondary mt-4 float-end">
							Iniciar Sesión
						</button>
					</form:form>
				</div>
			</div>
		</div>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	</body>
</html>