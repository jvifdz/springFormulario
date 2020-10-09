package com.javierfernandez.springboot.form.app.controllers;

import com.javierfernandez.springboot.form.app.models.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form(Model model) {

        Usuario usuario = new Usuario();
        model.addAttribute("titulo", "Formulario usuarios");
        /*seria usuario pero he puesto en el model attribute user para cambiar*/
        model.addAttribute("user" , usuario);
        return "form";
    }

    @PostMapping("/form")
    /*para poner nombre distinto en la vista moddel attribute*/
    public String procesar(@Valid @ModelAttribute("user") Usuario usuario, BindingResult result, Model model

                           //no hace falta ya que mapeamos desde el objeto
                           /*@RequestParam(name = "username") String username,
                           @RequestParam String password,
                           @RequestParam String email*/) {

        model.addAttribute("titulo", "Resultado form");

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
            });

            model.addAttribute("error", errores);

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


        return "resultado";
    }


}
