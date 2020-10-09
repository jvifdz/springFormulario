package com.javierfernandez.springboot.form.app.controllers;

import com.javierfernandez.springboot.form.app.models.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form(Model model){

        model.addAttribute("titulo", "Formulario usuarios");
        return "form";
    }

    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model

                           //no hace falta ya que mapeamos desde el objeto
                           /*@RequestParam(name = "username") String username,
                           @RequestParam String password,
                           @RequestParam String email*/){

        model.addAttribute("titulo", "Resultado form");

        if (result.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err ->{
                errores.put(err.getField(),"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
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
