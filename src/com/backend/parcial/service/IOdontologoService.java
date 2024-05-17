package com.backend.parcial.service.impl;

import com.backend.parcial.entity.Odontologo;

import java.util.HashMap;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);

    HashMap<Long, Odontologo> listarOdontologos();
}
