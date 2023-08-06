package com.finalLaboIII.demo.Business.impl;

import com.finalLaboIII.demo.Business.interfaces.CarreraBusiness;
import com.finalLaboIII.demo.Model.Carrera;
import com.finalLaboIII.demo.Persistence.Impl.CarreraDaoImpl;
import org.springframework.stereotype.Component;


@Component
public class CarreraBusinessImpl implements CarreraBusiness {
    CarreraDaoImpl carreraPersistence = new CarreraDaoImpl();

    @Override
    public void eliminarCarrera(Integer idCarrera) {

        carreraPersistence.eliminarCarrera(idCarrera);
    }

    @Override
    public int crearCarrera(Carrera carrera) {
        return carreraPersistence.crearCarrera(carrera);

    }

    @Override
    public void obtenerMateria_Carrera(Integer idCarrera) {

        carreraPersistence.obtenerMateria_Carrera(idCarrera);
    }

    @Override
    public void obtenerCarrera_Departamento(Integer idDepartamento) {
        carreraPersistence.obtenerCarrera_Departamento(idDepartamento);
    }

    @Override
    public void actualizarCarrera(int idCarrera, Carrera carrera) {
        carreraPersistence.actualizarCarrera(idCarrera, carrera);
    }


}
