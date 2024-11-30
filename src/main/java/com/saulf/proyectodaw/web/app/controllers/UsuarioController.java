package com.saulf.proyectodaw.web.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.saulf.proyectodaw.web.app.editor.RolesEditor;
import com.saulf.proyectodaw.web.app.models.entity.Role;
import com.saulf.proyectodaw.web.app.models.entity.Usuario;
import com.saulf.proyectodaw.web.app.models.service.ICargarArchivoService;
import com.saulf.proyectodaw.web.app.models.service.IRoleService;
import com.saulf.proyectodaw.web.app.models.service.IUsuarioService;
import com.saulf.proyectodaw.web.app.utils.Paginador;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("usuario")
@SessionAttributes("usuario")
public class UsuarioController {
	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private ICargarArchivoService cargarArchivoService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private RolesEditor roleEditor;

	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * método para registrar los PropertyEditor
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Role.class, "roles", roleEditor);
	}

	/**
	 * método que devuelve una lista páginada con todos los usuarios.
	 * 
	 * @param page
	 * @param model
	 * @return vista lista
	 */
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		int size = 7;// cantidad a mostrar por pagina
		Pageable pageRequest = PageRequest.of(page, size);

		Page<Usuario> usuarios = usuarioService.findAll(pageRequest);
		// url,Page<Usuario>
		Paginador<Usuario> pageRender = new Paginador<>("/usuario/listar", usuarios);

		model.addAttribute("titulo", "Usuarios");
		model.addAttribute("page", pageRender);
		model.addAttribute("usuarios", usuarios);
		return "usuario/listar";
	}

	/**
	 * método que devuelve el formulario
	 * 
	 * @param model
	 * @return vista form
	 */

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("titulo", "Formulario de Usuario");
		return "usuario/form";
	}

	/**
	 * método que devuelve el formulario con los datos del usuario
	 * 
	 * @param id
	 * @param model
	 * @param flash
	 * @return vista form
	 */

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Usuario usuario = null;

		if (id > 0) {
			usuario = usuarioService.findOne(id);

			if (usuario == null) {
				flash.addFlashAttribute("error", "Usuario inexistente en la base de datos!");
				return "redirect:/usuario/listar";
			}

		} else {
			flash.addFlashAttribute("error", "El identificador del usuario no puede ser cero!");
			return "redirect:/usuario/listar";
		}
		model.put("usuario", usuario);
		model.put("titulo", "Editar Usuario");
		return "usuario/form";
	}

	/**
	 * método que crear un usuario, validando previamente sus campos
	 * 
	 * @param usuario
	 * @param result
	 * @param model
	 * @param flash
	 * @param status
	 * @param foto
	 * @return vista form
	 */

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status, @RequestParam("file") MultipartFile foto) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Usuario");
			return "usuario/form";
		}

		if (!foto.isEmpty()) {

			if (usuario.getId() != null && usuario.getId() > 0 && usuario.getFoto() != null
					&& usuario.getFoto().length() > 0) {

				cargarArchivoService.delete(usuario.getFoto());
			}

			String nombreUnicoFile = null;
			try {
				nombreUnicoFile = cargarArchivoService.copy(foto);
			} catch (IOException e) {

				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Se ha subido con éxito '" + nombreUnicoFile + "'");

			usuario.setFoto(nombreUnicoFile);
		}

		String mensajeFlash = (usuario.getId() != null) ? "Usuario editado con éxito!" : "Usuario creado con éxito!";
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuarioService.save(usuario);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);

		return "redirect:/usuario/ver/" + usuario.getId();
	}

	/**
	 * método que elimina un usuario
	 * 
	 * @param id
	 * @param flash
	 * @return vista listar
	 */

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Usuario usuario = usuarioService.findOne(id);
			if (usuario == null) {
				flash.addFlashAttribute("error", "Usuario inexistente en la base de datos!");
				return "redirect:/usuario/listar";
			}
			if (usuarioService.isAdmin(id)) {

				flash.addFlashAttribute("success", "No se puede eliminar un usuario con rol Administrador");
				return "redirect:/usuario/listar";

			} else {
				usuarioService.delete(id);
				flash.addFlashAttribute("success", "Usuario eliminado con éxito!");
			}

			if (usuario.getFoto() != null) {
				if (cargarArchivoService.delete(usuario.getFoto())) {
					flash.addFlashAttribute("info", "Foto " + usuario.getFoto() + " eliminada con éxito!");
				}
			}

		}

		return "redirect:/usuario/listar";
	}

	/**
	 * método para mostrar una foto
	 * 
	 * @param filename
	 * @return devuelve la respuesta con el recurso
	 */

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = cargarArchivoService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	/**
	 * método para ver los detalles del usuario.
	 * 
	 * @param id
	 * @param model
	 * @param flash
	 * @return
	 */

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Usuario usuario = usuarioService.findOne(id);
		if (usuario == null) {
			flash.addFlashAttribute("error", "Usuario inexistente en la base de datos!");
			return "redirect:/usuario/listar";
		}

		model.put("usuario", usuario);
		model.put("titulo", "Perfil de " + usuario.getUsername());
		return "usuario/ver";
	}

	@ModelAttribute("listaRoles")
	public List<Role> listaRoles() {
		return this.roleService.findAll();
	}
}
