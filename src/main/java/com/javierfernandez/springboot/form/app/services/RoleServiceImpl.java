package com.javierfernandez.springboot.form.app.services;


import com.javierfernandez.springboot.form.app.models.domain.Role;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private List<Role> roles;

    public RoleServiceImpl() {
        this.roles = Arrays.asList(
                new Role(1, "Administrador", "ROLE_ADMIN"),
                new Role(2, "Usuario", "ROLE_USER"),
                new Role(3, "Moderador ServImpl", "ROLE_MODERATOR"));


    }

    @Override
    public List<Role> listar() {
        return roles;
    }

    @Override
    public Role obtenerPorId(Integer id) {

        Role resultado = null;
        for (Role role : this.roles) {
            if (id == role.getId()) {
                resultado = role;
                break;
            }
        }
        return resultado;
    }
}
