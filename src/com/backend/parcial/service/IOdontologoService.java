package com.backend.parcial.service;

import com.backend.parcial.entity.Odontologo;

import java.util.HashMap;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);
    HashMap<Long, Odontologo> listarOdontologos();
}
