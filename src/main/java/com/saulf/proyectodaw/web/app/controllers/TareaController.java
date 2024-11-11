package com.saulf.proyectodaw.web.app.controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.saulf.proyectodaw.web.app.models.entity.Comentario;
import com.saulf.proyectodaw.web.app.models.entity.Tarea;
import com.saulf.proyectodaw.web.app.models.entity.Usuario;
import com.saulf.proyectodaw.web.app.models.service.ICargarArchivoService;
import com.saulf.proyectodaw.web.app.models.service.IUsuarioService;
import com.saulf.proyectodaw.web.app.utils.Paginador;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tarea")
@SessionAttributes("tarea")
public class TareaController {
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private ICargarArchivoService cargarArchivoService;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		int size = 10;// cantidad a mostrar por pagina
		Pageable pageRequest = PageRequest.of(page, size);
		Page<Tarea> tareas = usuarioService.findAllTareas(pageRequest);
		// url,Page<Tarea>
		Paginador<Tarea> pageRender = new Paginador<>("/home", tareas);
		model.addAttribute("titulo", "Listado de Tareas");
		model.addAttribute("page", pageRender);
		model.addAttribute("tareas", tareas);
		
		return "tarea/listar";
	}

	
	@GetMapping("/form/{usuarioId}")
	public String crear(@PathVariable(value = "usuarioId") Long usuarioId, Map<String, Object> model,
			RedirectAttributes flash) {
		Usuario usuario = usuarioService.findOne(usuarioId);
		if (usuario == null) {
			flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
			return "redirect:/listar";
		}
		Tarea tarea = new Tarea();
		tarea.setUsuario(usuario);
		model.put("tarea", tarea);
		model.put("titulo", "Crear Tarea");
		return "tarea/form";
	}
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Tarea tarea, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status, @RequestParam("file") MultipartFile file) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear Tarea");
			return "tarea/form";
		}
		if (!file.isEmpty()) {
			if (tarea.getId() != null && tarea.getId() > 0 && tarea.getImagen() != null
					&& tarea.getImagen().length() > 0) {
				cargarArchivoService.delete(tarea.getImagen());
			}
			String nombreUnicoFile = null;
			try {
				nombreUnicoFile = cargarArchivoService.copy(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			flash.addFlashAttribute("info", "Se ha subido con éxito '" + nombreUnicoFile + "'");
			tarea.setImagen(nombreUnicoFile);
		}
		String mensajeFlash = (tarea.getId() != null) ? "Tarea editada con éxito!"
				: "Tarea creada con éxito!";
		usuarioService.saveTarea(tarea);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/usuario/listar";
	}
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Tarea tarea = usuarioService.findTareaById(id);
		if (tarea == null) {
			flash.addFlashAttribute("error", "Tarea inexistente en la base de datos!");
			return "redirect:/listar";
		}
		model.addAttribute("tarea", tarea);
		model.addAttribute("titulo", tarea.getTitulo());
		return "tarea/ver";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		Tarea tarea = usuarioService.findTareaById(id);
		
		if(tarea != null) {
			usuarioService.deleteTarea(id);
			flash.addFlashAttribute("success", "Tarea eliminada con éxito!");
			return "redirect:/usuario/ver/" + tarea.getUsuario().getId();
		}
		flash.addFlashAttribute("error", "No se pudo eliminar, no existe en la base de datos!");
		
		return "redirect:/listar";
	}
	
	@PostMapping("/comentar/{tareaId}")
	public String comentar(Comentario comentario,@PathVariable(value = "tareaId") Long tareaId, Map<String, Object> model,
			RedirectAttributes flash) {
		Tarea tarea = usuarioService.findTareaById(tareaId);
		if (tarea == null) {
			flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
			return "redirect:/listar";
		}
	
		comentario.setUsuario(usuarioService.findOne((long) 1));
		tarea.addComentario(comentario);
		usuarioService.saveComentario(comentario);
		model.put("tarea", tarea);
		model.put("titulo", tarea.getTitulo());
		return "redirect:/tarea/ver/{tareaId}";
	}
	
}