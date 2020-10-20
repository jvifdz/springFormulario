package com.javierfernandez.springboot.form.app.controllers;

import com.javierfernandez.springboot.form.app.editors.NombreMayusculaEditor;
import com.javierfernandez.springboot.form.app.editors.PaisPropertyEditor;
import com.javierfernandez.springboot.form.app.models.domain.Pais;
import com.javierfernandez.springboot.form.app.models.domain.Usuario;
import com.javierfernandez.springboot.form.app.services.PaisService;
import com.javierfernandez.springboot.form.app.validation.UsuarioValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SessionAttributes("usuario")
public class FormController {

    @Autowired
    private UsuarioValidador validador;


    @Autowired
    private PaisService paisService;

    @Autowired
    private PaisPropertyEditor paisEditor;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validador);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
        binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());
        binder.registerCustomEditor(Pais.class, "pais", paisEditor);

    }

    @ModelAttribute("listaPaises")
    public List<Pais> listaPaises() {
        //comento ya que ahora lo hago desde el servicio PAisService
        /*return Arrays.asList(
                new Pais(1, "ES","España"),
                new Pais(2, "MX","Mexico"),
                new Pais(3, "CL","Chile"),
                new Pais(4, "AR","Argentina"),
                new Pais(5, "PE", "Perú"),
                new Pais(6, "CO", "Colombia"),
                new Pais(7, "VE", "Venezuela"));*/
        return paisService.listar();
    }

    @ModelAttribute("listaRolesString")
    public List<String> listaRolesString(){
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");
        roles.add("ROLE_MODERATOR");
        return roles;

    }

    @ModelAttribute("listaRolesMap")
    public Map<String, String> listaRolesMap() {
        Map<String, String> roles = new HashMap<String, String>();
        //en el put primero key luego value
        roles.put("ROLE_ADMIN", "Administrador");
        roles.put("ROLE_USER", "Usuario");
        roles.put("ROLE_MODERATOR", "Moderador");
        return roles;
    }


    //ya no estoy usando este model attribute ya que use despues el hasmap y despues la clase pais
    @ModelAttribute("paises")
    public List<String> paises() {
        return Arrays.asList("España", "Mexico", "Chile", "Perú", "Colombia", "Venezuela");
    }

    @ModelAttribute("paisesMap")
    public Map<String, String> paisesMap() {
        Map<String, String> paises = new HashMap<String, String>();
        //en el put primero key luego value
        paises.put("ES", "España");
        paises.put("MX", "Mexico");
        paises.put("CL", "Chile");
        paises.put("AR", "Argentina");
        paises.put("PE", "Perú");
        paises.put("CO", "Colombia");
        paises.put("VE", "Venezuela");
        return paises;
    }

    @GetMapping("/form")
    public String form(Model model) {

        Usuario usuario = new Usuario();
        usuario.setNombre("john");
        usuario.setApellido("doe");
        usuario.setIdentificador("123.456.789-K");
        model.addAttribute("titulo", "Formulario usuarios");
        /*seria usuario (quitado user vuelto usuario pero he puesto en el model attribute user para cambiar*/
        model.addAttribute("usuario", usuario);


        return "form";
    }

    @PostMapping("/form")
    /*para poner nombre distinto en la vista moddel attribute*/
    public String procesar(@Valid /*@ModelAttribute("user")*/ Usuario usuario, BindingResult result, Model model, SessionStatus status

                           //no hace falta ya que mapeamos desde el objeto
                           /*@RequestParam(name = "username") String username,
                           @RequestParam String password,
                           @RequestParam String email*/) {

        // Voy a validar automaticamente con la anotacion valid el metodo initBinder
        // validador.validate(usuario,result);

        model.addAttribute("titulo", "Resultado form");

        if (result.hasErrors()) {
            //Error de forma manual
           /* Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
            });

            model.addAttribute("error", errores);*/


            return "form";
        }


        //mejor manera de pasar por argumento el usuario
       /* Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setPasssword(password);*/
        model.addAttribute("usuario", usuario);

        //ya no hace falta lo traemos desde la clase usuario
        /*model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("email", email);*/

        status.setComplete();
        return "resultado";
    }


}
