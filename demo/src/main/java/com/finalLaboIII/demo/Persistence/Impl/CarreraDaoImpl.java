package com.finalLaboIII.demo.Persistence.Impl;

import com.finalLaboIII.demo.Model.Carrera;
import com.finalLaboIII.demo.Model.Materia;
import com.finalLaboIII.demo.Persistence.exceptions.CarreraNoEncontradaException;
import com.finalLaboIII.demo.Persistence.exceptions.MateriaNoEncontradaException;
import com.finalLaboIII.demo.Persistence.interfaces.CarreraDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class CarreraDaoImpl implements CarreraDao {
    private static final Logger logger = LoggerFactory.getLogger(CarreraDaoImpl.class);

    public static Map<Integer, Carrera> listaCarreras = new HashMap<>();
    private static int idContador = 1; // empezamos en 1

    private int getIdContador() {
        return idContador++;
    }

    @Override
    public void eliminarCarrera(Integer idCarrera) {
        if (!listaCarreras.containsKey(idCarrera)) {
            throw new CarreraNoEncontradaException("Carrera no encontrada con id " + idCarrera);
        }
        listaCarreras.remove(idCarrera);
        logger.info("Carrera eliminada. Lista actual: " + listaCarreras);
    }
    @Override
    public int crearCarrera(Carrera carrera) {
        int id = getIdContador();

        Carrera c = new Carrera(
                carrera.getNombre(),
                carrera.getCantidadMaterias(),
                carrera.getIdDepartamento(),
                carrera.getDuracionAnios()
        );

        c.setId(id);
        c.setMateriaList(carrera.getMateriaList());

        listaCarreras.put(id, c);
        return id;
    }

    @Override
    public List<Carrera> listarCarreras() {
        return new ArrayList<>(listaCarreras.values());
    }
    @Override
    public Carrera obtenerCarrera(Integer idCarrera) {
        Carrera carrera = listaCarreras.get(idCarrera);
        if (carrera == null) {
            throw new CarreraNoEncontradaException("Carrera no encontrada con ID " + idCarrera);
        }
        return carrera;
    }



    @Override
    public List<Materia> obtenerMateria_Carrera(Integer idCarrera) {
        Carrera carrera = listaCarreras.get(idCarrera);
        if (carrera == null) {
            throw new CarreraNoEncontradaException("Carrera no encontrada con id " + idCarrera);
        }
        if (carrera.getMateriaList() == null || carrera.getMateriaList().isEmpty()) {
            throw new MateriaNoEncontradaException("No hay materias registradas en esta carrera");
        }
        return new ArrayList<>(carrera.getMateriaList().values());
    }

    @Override
    public List<Carrera> obtenerCarrera_Departamento(Integer idDepartamento) {
        List<Carrera> carrerasDepartamento = new ArrayList<>();
        for (Carrera carrera : listaCarreras.values()) {
            if (carrera.getIdDepartamento() == idDepartamento) {
                carrerasDepartamento.add(carrera);
            }
        }
        if (carrerasDepartamento.isEmpty()) {
            throw new CarreraNoEncontradaException("No se encontraron carreras en el departamento " + idDepartamento);
        }
        carrerasDepartamento.sort(Comparator.comparing(Carrera::getNombre));
        return carrerasDepartamento;
    }

    @Override
    public void actualizarCarrera(int idCarrera, Carrera carrera) {
        if (!listaCarreras.containsKey(idCarrera)) {
            throw new CarreraNoEncontradaException("Carrera no encontrada con id " + idCarrera);
        }
        listaCarreras.put(idCarrera, carrera);
    }
}
