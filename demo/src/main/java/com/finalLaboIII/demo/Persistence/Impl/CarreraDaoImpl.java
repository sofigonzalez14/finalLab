package com.finalLaboIII.demo.Persistence.Impl;

import com.finalLaboIII.demo.Model.Carrera;
import com.finalLaboIII.demo.Persistence.interfaces.CarreraDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CarreraDaoImpl implements CarreraDao {
    private static final Logger logger = LoggerFactory.getLogger(CarreraDao.class);
    public static Map<Integer, Carrera> listaCarreras = new HashMap<>();
    public static int idContador = 0;
    public int getIdContador(){

        return idContador++;
    }
    public void eliminarCarrera(Integer idCarrera){
        logger.info(idCarrera.toString());
        if(!listaCarreras.containsKey(idCarrera)){
            throw new RuntimeException();
        }
        listaCarreras.remove(idCarrera);
        logger.info("Lista carrera: " + listaCarreras.toString());
    }

    public int crearCarrera(Carrera carrera){
        int id = getIdContador();
        Carrera c = new Carrera();
        c.setNombre(carrera.getNombre());
        c.setIdDepartamento(carrera.getIdDepartamento());
        c.setCantidadCuatrimestre(carrera.getCantidadCuatrimestre());
        c.setMateriaList(carrera.getMateriaList());
        listaCarreras.put(id, c);
        return id;
    }

    @Override
    public void obtenerMateria_Carrera(Integer idCarrera) {
        if(!listaCarreras.containsKey(idCarrera)){
            throw new RuntimeException();
        }
        Carrera carreraObtenida = listaCarreras.get(idCarrera );
        logger.info("Carrera obtenida: " + carreraObtenida.toString());
    }
    // obtener todas las carreras de un departamento dado
    @Override
    public void obtenerCarrera_Departamento(Integer idDepartamento) {
        List<Carrera> carrerasDepartamento = new ArrayList<>();
        for (Carrera carrera : listaCarreras.values()) {
            if (carrera.getIdDepartamento() == idDepartamento) {
                carrerasDepartamento.add(carrera);
            }
        }
        carrerasDepartamento.sort(Comparator.comparing(Carrera::getNombre));
    }

    @Override
    public void actualizarCarrera(int idCarrera, Carrera carrera) {
        if(!listaCarreras.containsKey(idCarrera)){
            throw new RuntimeException();
        }
        listaCarreras.replace(idCarrera, carrera);
    }


}
