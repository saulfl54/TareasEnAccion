package com.saulf.proyectodaw.web.app.controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tarea")
@SessionAttributes("tarea")
public class TareaController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ICargarArchivoService cargarArchivoService;

    /**
     * Muestra el formulario para crear una nueva tarea.
     * 
     * @param usuarioId El ID del usuario al cual se asociará la tarea.
     * @param model El modelo para pasar los atributos a la vista.
     * @param flash Atributos para mensajes flash.
     * @return La vista para crear una tarea.
     */
    @GetMapping("/form/{usuarioId}")
    public String crear(@PathVariable Long usuarioId, Model model, RedirectAttributes flash) {
        Usuario usuario = usuarioService.findOne(usuarioId);
        if (usuario == null) {
            flash.addFlashAttribute("error", "El usuario no existe.");
            return "redirect:/usuario/listar";
        }

        Tarea tarea = new Tarea();
        tarea.setUsuario(usuario); // Asociamos el usuario a la tarea
        model.addAttribute("tarea", tarea);
        model.addAttribute("titulo", "Crear Tarea");

        // Construir la URL para el formulario para la creación
        String formUrl = "/tarea/form/" + usuarioId; // Sin tareaId
        model.addAttribute("formUrl", formUrl); // Pasamos la URL como atributo al modelo

        return "tarea/form"; // Retorna la vista del formulario
    }

    /**
     * Muestra el formulario para editar una tarea existente.
     * 
     * @param usuarioId El ID del usuario propietario de la tarea.
     * @param tareaId El ID de la tarea a editar.
     * @param model El modelo para pasar los atributos a la vista.
     * @param flash Atributos para mensajes flash.
     * @return La vista para editar una tarea.
     */
    @GetMapping("/form/{usuarioId}/{tareaId}")
    public String editar(@PathVariable Long usuarioId, @PathVariable Long tareaId, Model model,
            RedirectAttributes flash) {
        Usuario usuario = usuarioService.findOne(usuarioId);
        if (usuario == null) {
            flash.addFlashAttribute("error", "El usuario no existe.");
            return "redirect:/usuario/listar";
        }

        Tarea tarea = usuarioService.findTareaById(tareaId);
        if (tarea == null || !tarea.getUsuario().getId().equals(usuarioId)) {
            flash.addFlashAttribute("error", "Tarea no encontrada o no pertenece a este usuario.");
            return "redirect:/usuario/listar";
        }

        model.addAttribute("tarea", tarea);
        model.addAttribute("titulo", "Editar Tarea");

        // Construir la URL para el formulario para la edición
        String formUrl = "/tarea/form/" + usuarioId + "/" + tareaId; // Incluimos tareaId
        model.addAttribute("formUrl", formUrl); // Pasamos la URL como atributo al modelo

        return "tarea/form";
    }

    /**
     * Guarda una tarea, ya sea creada o editada.
     * 
     * @param tarea La tarea a guardar.
     * @param result Los resultados de la validación de la tarea.
     * @param model El modelo para pasar los atributos a la vista.
     * @param flash Atributos para mensajes flash.
     * @param status Estado de la sesión.
     * @param file El archivo a asociar con la tarea (si existe).
     * @param usuarioId El ID del usuario propietario de la tarea.
     * @param tareaId El ID de la tarea (si es edición).
     * @return Redirige a la vista de la tarea o de error.
     */
    @PostMapping("/form/{usuarioId}/{tareaId}")
    public String guardar(@Valid Tarea tarea, BindingResult result, Model model, RedirectAttributes flash,
            SessionStatus status, @RequestParam("file") MultipartFile file, @PathVariable Long usuarioId,
            @PathVariable Long tareaId) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar Tarea");
            return "tarea/form"; // Si hay errores, volvemos al formulario
        }

        Usuario usuario = usuarioService.findOne(usuarioId);
        if (usuario == null) {
            flash.addFlashAttribute("error", "El usuario no existe.");
            return "redirect:/usuario/listar";
        }

        tarea.setUsuario(usuario);

        // Manejo del archivo de imagen (si existe)
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

        // Determinamos el mensaje según si es creación o edición
        String mensajeFlash = (tarea.getId() != null) ? "Tarea editada con éxito!" : "Tarea creada con éxito!";
        usuarioService.saveTarea(tarea);
        status.setComplete(); // Limpiar la sesión

        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/tarea/ver/{tareaId}";
    }

    /**
     * Crea una nueva tarea asociada a un usuario.
     * 
     * @param tarea La tarea a crear.
     * @param result Los resultados de la validación de la tarea.
     * @param model El modelo para pasar los atributos a la vista.
     * @param flash Atributos para mensajes flash.
     * @param status Estado de la sesión.
     * @param file El archivo a asociar con la tarea.
     * @param usuarioId El ID del usuario propietario de la tarea.
     * @return Redirige a la vista de listado de tareas del usuario.
     */
    @RequestMapping(value = "/form/{usuarioId}", method = RequestMethod.POST)
    public String guardar(@Valid Tarea tarea, BindingResult result, Model model, RedirectAttributes flash,
            SessionStatus status, @RequestParam("file") MultipartFile file, @PathVariable Long usuarioId) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Crear Tarea");
            return "tarea/form";
        }

        Usuario usuario = usuarioService.findOne(usuarioId);
        if (usuario == null) {
            flash.addFlashAttribute("error", "El usuario no existe.");
            return "redirect:/usuario/listar";
        }

        tarea.setUsuario(usuario); // Asociar la tarea al usuario

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

        String mensajeFlash = (tarea.getId() != null) ? "Tarea editada con éxito!" : "Tarea creada con éxito!";
        usuarioService.saveTarea(tarea);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);

        return "redirect:/usuario/ver/{usuarioId}";
    }

    /**
     * Devuelve los detalles de una tarea.
     * 
     * @param id El ID de la tarea a mostrar.
     * @param model El modelo para pasar los atributos a la vista.
     * @param flash Atributos para mensajes flash.
     * @return La vista de detalles de la tarea.
     */
    @GetMapping("/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
        Tarea tarea = usuarioService.findTareaById(id);

        if (tarea == null) {
            flash.addFlashAttribute("error", "Tarea inexistente en la base de datos!");
            return "redirect:/listar";
        }

        model.addAttribute("tarea", tarea);
        model.addAttribute("nombrePerfil", tarea.getUsuario().getUsername());
        model.addAttribute("titulo", tarea.getTitulo());

        return "tarea/ver";
    }

    /**
     * Elimina una tarea.
     * 
     * @param id El ID de la tarea a eliminar.
     * @param flash Atributos para mensajes flash.
     * @return Redirige a la vista de listado de tareas.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

        Tarea tarea = usuarioService.findTareaById(id);

        if (tarea != null) {
            usuarioService.deleteTarea(id);
            flash.addFlashAttribute("success", "Tarea eliminada con éxito!");
            return "redirect:/usuario/ver/" + tarea.getUsuario().getId();
        }
        flash.addFlashAttribute("error", "No se pudo eliminar, no existe en la base de datos!");

        return "redirect:/usuario/listar";
    }

    /**
     * Crea un comentario en una tarea.
     * 
     * @param comentario El comentario a crear.
     * @param tareaId El ID de la tarea a la cual se agrega el comentario.
     * @param model El modelo para pasar los atributos a la vista.
     * @param flash Atributos para mensajes flash.
     * @return Redirige a la vista de detalles de la tarea.
     */
    @PostMapping("/comentar/{tareaId}")
    public String comentar(Comentario comentario, @PathVariable(value = "tareaId") Long tareaId,
            Map<String, Object> model, RedirectAttributes flash) {

        Tarea tarea = usuarioService.findTareaById(tareaId);

        if (tarea == null) {
            flash.addFlashAttribute("error", "La tarea no existe en la base de datos");
            return "redirect:/listar";
        }

        // Obtener el nombre de usuario del usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Usuario usuario = usuarioService.findByUsername(username);

        // Reemplazar saltos de línea en el contenido del comentario
        String comentarioConSaltos = comentario.getContenido().replace("\n", "<br />");
        comentario.setContenido(comentarioConSaltos);

        usuarioService.saveComentario(comentario, usuario, tarea);
        flash.addFlashAttribute("success", "Comentario realizado con éxito!");

        model.put("tarea", tarea);
        model.put("titulo", tarea.getTitulo());

        return "redirect:/tarea/ver/{tareaId}";
    }

    /**
     * Elimina un comentario de una tarea.
     * 
     * @param idComentario El ID del comentario a eliminar.
     * @param idTarea El ID de la tarea de la cual se elimina el comentario.
     * @param flash Atributos para mensajes flash.
     * @return Redirige a la vista de detalles de la tarea.
     */
    @GetMapping("/eliminarComentario/{idComentario}/{idTarea}")
    public String eliminarComentario(@PathVariable(value = "idComentario") Long idComentario,
            @PathVariable(value = "idTarea") Long idTarea, RedirectAttributes flash) {

        Comentario comentario = usuarioService.findComentarioById(idComentario);
        Tarea tarea = usuarioService.findTareaById(idTarea);

        if ((tarea != null && comentario != null) || tarea != null || comentario != null) {
            usuarioService.deleteComentario(idComentario);
            flash.addFlashAttribute("success", "Comentario eliminado con éxito!");
            return "redirect:/tarea/ver/" + tarea.getId();
        }
        flash.addFlashAttribute("error", "No se pudo eliminar, no existe en la base de datos!");

        return "redirect:/usuario/listar";
    }
}
