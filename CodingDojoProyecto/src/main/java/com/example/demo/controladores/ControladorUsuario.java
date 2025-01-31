package com.example.demo.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.modelos.LoginUsuario;
import com.example.demo.modelos.Usuario;
import com.example.demo.servicios.ServicioUsuario;


@Controller
public class ControladorUsuario {

	@Autowired
	private final ServicioUsuario servicioUsuario;

	public ControladorUsuario(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}

	@GetMapping({"/", "/login", "/registro"})
	public String despliegaLoginRegistro(@ModelAttribute("usuario") Usuario usuario,
										 @ModelAttribute("loginUsuario") LoginUsuario loginUsuario) {
		return "index.jsp";
	}

	@PostMapping("/registrar/usuario")
	public String procesaRegistrarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
										  BindingResult validaciones,
										  @ModelAttribute("loginUsuario") LoginUsuario loginUsuario,
										  HttpSession sesion) {
		validaciones = this.servicioUsuario.validarRegistro(validaciones, usuario);
		if(validaciones.hasErrors()) {
			return "index.jsp";
		}

		Usuario usuarioCreado = this.servicioUsuario.insertarUsuario(usuario);
		sesion.setAttribute("id_usuario", usuarioCreado.getId());
		sesion.setAttribute("nombre_usuario", usuarioCreado.getNombre());

		return "redirect:/canciones";
	}

	@PostMapping("/procesa/login")
	public String procesaLogin(@Valid @ModelAttribute("loginUsuario") LoginUsuario loginUsuario,
							   BindingResult validaciones,
							   @ModelAttribute("usuario") Usuario usuario,
							   HttpSession sesion) {
		validaciones = this.servicioUsuario.validarLogin(validaciones, loginUsuario);
		if(validaciones.hasErrors()) {
			return "index.jsp";
		}
		Usuario usuarioActual = this.servicioUsuario.obtenerPorCorreo(loginUsuario.getUsuarioCorreo());
		sesion.setAttribute("id_usuario", usuarioActual.getId());
		sesion.setAttribute("nombre_usuario", usuarioActual.getNombre());

		return "redirect:/canciones";
	}

}