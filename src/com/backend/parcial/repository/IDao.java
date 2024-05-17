package com.backend.parcial.repository;

import java.util.HashMap;

public interface IDao<T> {
    T guardar(T t);
    HashMap <Long, T> listarTodos();
}
