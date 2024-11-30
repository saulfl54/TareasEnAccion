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

/**
 * Controlador de la entidad Usuario, encargado de manejar las operaciones CRUD 
 * y la gestión de archivos asociados a los usuarios.
 * <p>Autor: Saulf</p>
 */
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
    private PasswordEncoder passwordEncoder;

    /**
     * Registra un PropertyEditor para convertir cadenas de texto en objetos de tipo Role.
     * 
     * @param binder El WebDataBinder utilizado para registrar el editor de roles.
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, "roles", roleEditor);
    }

    /**
     * Muestra una lista de usuarios paginada.
     * 
     * @param page Número de página para la paginación.
     * @param model Modelo para agregar atributos a la vista.
     * @return Vista con la lista de usuarios.
     */
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        int size = 7; // Cantidad a mostrar por página
        Pageable pageRequest = PageRequest.of(page, size);

        Page<Usuario> usuarios = usuarioService.findAll(pageRequest);
        // Generar la paginación con la URL "/usuario/listar"
        Paginador<Usuario> pageRender = new Paginador<>("/usuario/listar", usuarios);

        model.addAttribute("titulo", "Usuarios");
        model.addAttribute("page", pageRender);
        model.addAttribute("usuarios", usuarios);
        return "usuario/listar";
    }

    /**
     * Muestra el formulario para crear un nuevo usuario.
     * 
     * @param model Modelo que se pasa a la vista.
     * @return Vista del formulario para crear un usuario.
     */
    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {
        Usuario usuario = new Usuario();
        model.put("usuario", usuario);
        model.put("titulo", "Formulario de Usuario");
        return "usuario/form";
    }

    /**
     * Muestra el formulario de edición de un usuario con los datos cargados.
     * 
     * @param id Identificador del usuario a editar.
     * @param model Modelo que se pasa a la vista.
     * @param flash Atributos de redirección para mensajes flash.
     * @return Vista del formulario de edición.
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
     * Crea o edita un usuario validando los campos y subiendo la foto asociada si es necesario.
     * 
     * @param usuario El objeto usuario con los datos del formulario.
     * @param result Resultados de la validación del formulario.
     * @param model Modelo que se pasa a la vista.
     * @param flash Atributos de redirección para mensajes flash.
     * @param status Estado de la sesión.
     * @param foto Archivo de la foto de perfil del usuario.
     * @return Redirige a la vista con el perfil del usuario creado o editado.
     */
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash,
            SessionStatus status, @RequestParam("file") MultipartFile foto) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Usuario");
            return "usuario/form";
        }

        if (!foto.isEmpty()) {
            // Si es una edición de usuario, eliminar la foto anterior
            if (usuario.getId() != null && usuario.getId() > 0 && usuario.getFoto() != null
                    && usuario.getFoto().length() > 0) {
                cargarArchivoService.delete(usuario.getFoto());
            }

            // Subir nueva foto
            String nombreUnicoFile = null;
            try {
                nombreUnicoFile = cargarArchivoService.copy(foto);
            } catch (IOException e) {
                e.printStackTrace();
            }

            flash.addFlashAttribute("info", "Se ha subido con éxito '" + nombreUnicoFile + "'");
            usuario.setFoto(nombreUnicoFile);
        }

        // Codificar la contraseña y guardar el usuario
        String mensajeFlash = (usuario.getId() != null) ? "Usuario editado con éxito!" : "Usuario creado con éxito!";
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioService.save(usuario);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);

        return "redirect:/usuario/ver/" + usuario.getId();
    }

    /**
     * Elimina un usuario de la base de datos y la foto asociada si existe.
     * 
     * @param id Identificador del usuario a eliminar.
     * @param flash Atributos de redirección para mensajes flash.
     * @return Redirige a la lista de usuarios.
     */
    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

        if (id > 0) {
            Usuario usuario = usuarioService.findOne(id);
            if (usuario == null) {
                flash.addFlashAttribute("error", "Usuario inexistente en la base de datos!");
                return "redirect:/usuario/listar";
            }

            // Verificar si el usuario es administrador y no permitir su eliminación
            if (usuarioService.isAdmin(id)) {
                flash.addFlashAttribute("success", "No se puede eliminar un usuario con rol Administrador");
                return "redirect:/usuario/listar";
            } else {
                usuarioService.delete(id);
                flash.addFlashAttribute("success", "Usuario eliminado con éxito!");
            }

            // Eliminar la foto si existe
            if (usuario.getFoto() != null) {
                if (cargarArchivoService.delete(usuario.getFoto())) {
                    flash.addFlashAttribute("info", "Foto " + usuario.getFoto() + " eliminada con éxito!");
                }
            }
        }

        return "redirect:/usuario/listar";
    }

    /**
     * Muestra la foto de perfil de un usuario.
     * 
     * @param filename Nombre del archivo de la foto.
     * @return Responde con el archivo solicitado.
     */
    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
        Resource recurso = null;

        try {
            recurso = cargarArchivoService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }

    /**
     * Muestra los detalles de un usuario en su perfil.
     * 
     * @param id Identificador del usuario.
     * @param model Modelo que se pasa a la vista.
     * @param flash Atributos de redirección para mensajes flash.
     * @return Vista con los detalles del usuario.
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

    /**
     * Carga la lista de roles disponibles.
     * 
     * @return Lista de roles.
     */
    @ModelAttribute("listaRoles")
    public List<Role> listaRoles() {
        return this.roleService.findAll();
    }
}
