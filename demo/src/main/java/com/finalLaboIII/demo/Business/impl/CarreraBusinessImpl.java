package com.finalLaboIII.demo.Business.impl;

import com.finalLaboIII.demo.Business.interfaces.CarreraBusiness;
import com.finalLaboIII.demo.Model.Carrera;
import com.finalLaboIII.demo.Model.Materia;
import com.finalLaboIII.demo.Persistence.Impl.CarreraDaoImpl;
import com.finalLaboIII.demo.Persistence.interfaces.CarreraDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraBusinessImpl implements CarreraBusiness {

    private final CarreraDaoImpl carreraPersistence = new CarreraDaoImpl();

    @Override
    public void eliminarCarrera(Integer idCarrera) {
        carreraPersistence.eliminarCarrera(idCarrera);
    }

    @Override
    public int crearCarrera(Carrera carrera) {
        return carreraPersistence.crearCarrera(carrera);
    }

    @Override
    public List<Carrera> listarTodas() {
        return carreraPersistence.listarCarreras();  // ✅ usando la instancia
    }

    @Override
    public List<Materia> obtenerMateria_Carrera(Integer idCarrera) {
        return carreraPersistence.obtenerMateria_Carrera(idCarrera);
    }

    @Override
    public Carrera obtenerCarrera(Integer idCarrera) {
        return carreraPersistence.obtenerCarrera(idCarrera);
    }

    @Override
    public List<Carrera> obtenerCarrera_Departamento(Integer idDepartamento) {
        return carreraPersistence.obtenerCarrera_Departamento(idDepartamento);
    }

    @Override
    public void actualizarCarrera(int idCarrera, Carrera carrera) {
        carreraPersistence.actualizarCarrera(idCarrera, carrera);
    }


}

