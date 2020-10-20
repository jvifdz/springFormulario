package com.javierfernandez.springboot.form.app.services;

import com.javierfernandez.springboot.form.app.models.domain.Pais;

import java.util.List;

public interface PaisService {

    public List<Pais> listar();

    public Pais obtenerPorId(Integer id);


}
