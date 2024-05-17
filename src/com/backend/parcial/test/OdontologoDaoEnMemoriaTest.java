package com.backend.parcial.test;


import static org.junit.jupiter.api.Assertions.*;

import com.backend.parcial.entity.Odontologo;
import com.backend.parcial.repository.IDao;
import com.backend.parcial.repository.impl.OdontologoDaoEnMemoria;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class OdontologoDaoEnMemoriaTest {
   private IDao<Odontologo> odontologoIDao = new OdontologoDaoEnMemoria(new HashMap<>());

   @Test
    public void listartodosLosOdontlogosEnMemoria(){

       odontologoIDao.guardar(new Odontologo(1L, "M-123455", "Maria", "Perez"));
       odontologoIDao.guardar(new Odontologo(2L, "M-789997", "Martina", "Marin"));
       odontologoIDao.guardar(new Odontologo(3L, "M-152663", "Pedro", "Perez"));

       assertFalse(odontologoIDao.listarTodos().isEmpty());
   }


}