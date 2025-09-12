package com.finalLaboIII.demo.dtos;

public class CarreraResponseDto {
    private Integer id;
    private String nombre;
    private int cantidadMaterias;
    private int duracionAnios;

    public CarreraResponseDto(Integer id, String nombre, int cantidadMaterias, int duracionAnios) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadMaterias = cantidadMaterias;
        this.duracionAnios = duracionAnios;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadMaterias() {
        return cantidadMaterias;
    }

    public void setCantidadMaterias(int cantidadMaterias) {
        this.cantidadMaterias = cantidadMaterias;
    }

    public int getDuracionAnios() {
        return duracionAnios;
    }

    public void setDuracionAnios(int duracionAnios) {
        this.duracionAnios = duracionAnios;
    }
}

