package com.example.demo.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.modelos.Cancion;
import com.example.demo.modelos.Colaborador;
import com.example.demo.modelos.Usuario;
import com.example.demo.servicios.ServicioCancion;
import com.example.demo.servicios.ServicioColaborador;
import com.example.demo.servicios.ServicioUsuario;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorCancion {
	@Autowired
	private final ServicioCancion servicioCancion;
	private final ServicioUsuario servicioUsuario;
	private final ServicioColaborador servicioColaborador;

	public ControladorCancion(ServicioCancion servicioCancion, ServicioUsuario servicioUsuario, ServicioColaborador servicioColaborador) {
		this.servicioCancion = servicioCancion;
		this.servicioUsuario = servicioUsuario;
		this.servicioColaborador = servicioColaborador;
	}


	@GetMapping("/canciones")
	public String desplegarCancion(Model modelo,
								   HttpSession sesion) {
		if(sesion.getAttribute("id_usuario") == null) {
			return "redirect:/login";
		}

		List<Cancion> canciones = this.servicioCancion.obtenerTodos();
		modelo.addAttribute("canciones", canciones);

		return "canciones.jsp";
	}
	
	@GetMapping("/canciones/nuevo")
	public String desplegarFormularioCancion(@ModelAttribute("cancion") Cancion cancion,
											HttpSession sesion) {
		if(sesion.getAttribute("id_usuario") == null) {
			return "redirect:/login";
		}
		return "formularioCancion.jsp";
	}

	@PostMapping("/agregar/cancion")
	public String procesarAgregarCancion(@Valid @ModelAttribute("cancion") Cancion cancion,
	                                    BindingResult validaciones,
	                                    HttpSession sesion, Model modelo) {
	    if (validaciones.hasErrors()) {
	        return "formularioCancion.jsp";
	    }
	    
	    if (servicioCancion.existePorTitulo(cancion.getTitulo())) {
            modelo.addAttribute("errorTitulo", "El título de la canción ya existe.");
            return "formularioCancion.jsp";
        }

	    Long idUsuario = (Long) sesion.getAttribute("id_usuario");
	    Usuario usuario = this.servicioUsuario.obtenerPorId(idUsuario);
	    cancion.setUsuario(usuario);
	    servicioCancion.insertarCancion(cancion);
	    Colaborador colaborador = new Colaborador();
        colaborador.setUsuario(usuario);
        colaborador.setCancion(cancion);
        this.servicioColaborador.insertarColaborador(colaborador);

	    return "redirect:/canciones";
	} 
	

    @GetMapping("/canciones/{id}")
    public String verDetallesCancion(@PathVariable("id") Long id, Model model, HttpSession sesion) {
    	Cancion cancion = servicioCancion.obtenerPorId(id); 
        if(sesion.getAttribute("id_usuario") == null) {
			return "redirect:/login";
		}
        model.addAttribute("cancion", cancion);

        return "detalleCancion.jsp";
    }

    @GetMapping("/editar/cancion/{id}")
    public String desplegarEditarFormularioCancion(@ModelAttribute("programa") Cancion cancion,
                                                    @PathVariable("id") Long id,
                                                    Model modelo,
                                                    HttpSession sesion) {
        if(sesion.getAttribute("id_usuario") == null) {
            return "redirect:/login";
        }
        cancion = this.servicioCancion.obtenerPorId(id);
        cancion.setLetraOriginal(cancion.getLetra());
        cancion.setLetra("");
        modelo.addAttribute("cancion", cancion);
        return "editarCancion.jsp";
    }

    @PutMapping("/procesar/editar/cancion/{id}")
 // La anotación @PutMapping indica que este método manejará una solicitud HTTP PUT a la URL especificada, 
 // que incluye un parámetro de ruta {id}.

 public String procesaEditarCancion(@Valid @ModelAttribute("cancion") Cancion cancion,
 // La anotación @Valid indica que los datos del objeto cancion deben ser validados automáticamente.
 // @ModelAttribute("cancion") indica que el objeto Cancion será extraído del modelo y enlazado a los campos del formulario.

                                    BindingResult validaciones, @PathVariable("id") Long id, HttpSession sesion) {
 // BindingResult es un objeto que contiene los resultados de la validación, 
 // permitiendo verificar si ocurrieron errores. 
 // @PathVariable("id") obtiene el valor del parámetro de la ruta {id} y lo asigna a la variable id.
 // HttpSession permite acceder a los datos de la sesión del usuario.

     if(validaciones.hasErrors()) {
         return "editarPrograma.jsp";
     }
 // Si se encuentran errores de validación, se redirige al formulario de edición de la canción (editarPrograma.jsp).

     Cancion cancionNueva = servicioCancion.obtenerPorId(id);
 // Se obtiene la canción existente en la base de datos utilizando el ID proporcionado.

     Long idUsuario = (Long) sesion.getAttribute("id_usuario");
 // Se obtiene el ID del usuario actualmente en sesión.

     Usuario usuario = this.servicioUsuario.obtenerPorId(idUsuario);
 // Se obtiene el objeto Usuario correspondiente al ID de la sesión.

     if(!cancionNueva.getColaboradores().stream().anyMatch(colaborador -> usuario.getId().equals(colaborador.getUsuario().getId()))) {
 // Se verifica si el usuario actual ya está registrado como colaborador de la canción. 
 // Si no es así, se procede a añadirlo como colaborador.

         Colaborador colaborador = new Colaborador();
 // Se crea un nuevo objeto Colaborador.

         colaborador.setUsuario(usuario);
 // Se establece el usuario actual como el colaborador.

         colaborador.setCancion(cancionNueva);
 // Se asocia la canción existente con el nuevo colaborador.

         this.servicioColaborador.insertarColaborador(colaborador);
 // Se guarda el nuevo colaborador en la base de datos.
     }
     
     cancion.setId(id);
 // Se asegura que la canción a actualizar tiene el ID correcto y está asociada con el usuario actual.

     cancion.setLetra(cancion.getLetraOriginal() + cancion.getLetra());
 // Se concatena la letra original de la canción con la nueva letra antes de actualizarla.

     this.servicioCancion.actualizarCancion(cancion);
 // Se actualiza la información de la canción en la base de datos.

     return "redirect:/canciones";
 // Se redirige al usuario a la lista de canciones.
 }

    
    @DeleteMapping("/eliminar/cancion/{id}")
    public String eliminarCancion(@PathVariable("id") Long id, HttpSession sesion) {
    	Cancion cancion = servicioCancion.obtenerPorId(id);
    	if(cancion.getUsuario().getId() == sesion.getAttribute("id_usuario")) {
    		servicioCancion.eliminarPorId(id);    		
    	}
    	
        return "redirect:/canciones";
    }

	@GetMapping("/logout")
	public String procesaLogout(HttpSession sesion) {
		sesion.invalidate();
		return "redirect:/login";
	}

}