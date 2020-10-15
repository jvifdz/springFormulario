package com.javierfernandez.springboot.form.app.controllers;

import com.javierfernandez.springboot.form.app.models.domain.Usuario;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("usuario")
public class FormController {

    @Autowired
    private UsuarioValidador validador;
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(validador);

        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class,"fechaNacimiento" ,new CustomDateEditor(dateFormat, true));

    }

    @GetMapping("/form")
    public String form(Model model) {

        Usuario usuario = new Usuario();
        usuario.setNombre("john");
        usuario.setApellido("doe");
        usuario.setIdentificador("123.456.789-K");
        model.addAttribute("titulo", "Formulario usuarios");
        /*seria usuario (quitado user vuelto usuario pero he puesto en el model attribute user para cambiar*/
        model.addAttribute("usuario" , usuario);
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
