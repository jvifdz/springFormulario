package com.javierfernandez.springboot.form.app.controllers;

import com.javierfernandez.springboot.form.app.models.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form(Model model){

        model.addAttribute("titulo", "Formulario usuarios");
        return "form";
    }

    @PostMapping("/form")
    public String procesar(Model model,
                           @RequestParam(name = "username") String username,
                           @RequestParam String password,
                           @RequestParam String email){
        model.addAttribute("titulo", "Resultado form");

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setPasssword(password);
        model.addAttribute("usuario", usuario);

    //ya no hace falta lo traemos desde la clase uusuario
        /*model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("email", email);*/


        return "resultado";
    }



}
