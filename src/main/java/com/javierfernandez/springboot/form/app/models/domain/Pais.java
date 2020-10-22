package com.javierfernandez.springboot.form.app.models.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Pais {

    private Integer id;
    //No es necesario ya que valido el objeto completo
    //@NotEmpty
    private String codigo;

    private String nombre;

    public Pais() {
    }

    public Pais(Integer id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Este to string es necesario para poder poner por defecto un pais. en el formulario esta la id en el
    //select y al ser un int debemos pasarlo a un string

    @Override
    public String toString() {
        return this.id.toString();
    }
}
