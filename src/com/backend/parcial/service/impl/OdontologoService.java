package com.backend.parcial.service.impl;

import com.backend.parcial.entity.Odontologo;
import com.backend.parcial.repository.IDao;
import com.backend.parcial.service.IOdontologoService;

import java.util.HashMap;

public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> odontologoIDao;
    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoIDao.guardar(odontologo);
    }

    @Override
    public HashMap<Long, Odontologo> listarOdontologos() {
        return odontologoIDao.listarTodos();
    }
}
