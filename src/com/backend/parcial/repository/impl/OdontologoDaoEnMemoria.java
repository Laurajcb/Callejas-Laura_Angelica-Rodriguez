package com.backend.parcial.repository.impl;

import com.backend.parcial.entity.Odontologo;
import com.backend.parcial.repository.IDao;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class OdontologoDaoEnMemoria implements IDao<Odontologo> {
    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    private HashMap<Long, Odontologo> odontologosEnMemoria;

    public OdontologoDaoEnMemoria(HashMap<Long, Odontologo> odontologoEnMemoria) {
        this.odontologosEnMemoria = odontologoEnMemoria;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        odontologosEnMemoria.put(odontologo.getId(), odontologo);
        LOGGER.info("Odontologo guardado en memoria: " + odontologo);
        return odontologo;
    }

    @Override
    public HashMap<Long, Odontologo> listarTodos() {
        LOGGER.info("Odontologos guardados en memoria: " + odontologosEnMemoria);
        return odontologosEnMemoria;
    }
}
