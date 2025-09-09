package com.finalLaboIII.demo.dtos;

public class CarreraDto {
    private String nombre;
    private int cantidadCuatrimestre;
    private int idDepartamento;

    public CarreraDto() {}

    public CarreraDto(String nombre, int cantidadCuatrimestre, int idDepartamento) {
        this.nombre = nombre;
        this.cantidadCuatrimestre = cantidadCuatrimestre;
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadCuatrimestre() {
        return cantidadCuatrimestre;
    }

    public void setCantidadCuatrimestre(int cantidadCuatrimestre) {
        this.cantidadCuatrimestre = cantidadCuatrimestre;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
}
