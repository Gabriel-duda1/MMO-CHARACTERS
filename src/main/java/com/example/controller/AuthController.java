package com.example.controller;

import com.example.model.Usuario;
import com.example.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/login")
    public String login() { return "Login"; }

    @GetMapping("/usuario/cadastrar")
    public String formCadastro() { return "Cadastro"; }

    @PostMapping("/usuario/cadastrar")
    public String cadastrar(Usuario usuario) {
        service.salvar(usuario);
        return "redirect:/login";
    }
}