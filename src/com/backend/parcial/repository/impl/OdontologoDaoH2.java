package com.backend.parcial.repository.impl;

import com.backend.parcial.dbconnection.H2Connection;
import com.backend.parcial.entity.Odontologo;
import com.backend.parcial.repository.IDao;
import org.apache.log4j.Logger;

import java.sql.*;

import java.util.HashMap;
import java.util.Map;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoGuardado = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS(MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.execute();

            odontologoGuardado = new Odontologo(odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                odontologoGuardado.setId(resultSet.getLong("id"));
            }
            connection.commit();
            LOGGER.info("Odontologo guardado: " + odontologoGuardado);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Error de conexion");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException ex) {
                    LOGGER.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                LOGGER.error("La conexion no se pudo cerrar" + exception.getMessage());
            }
        }

        return odontologoGuardado;
    }

    @Override
    public HashMap<Long, Odontologo> listarTodos() {
        Connection connection = null;
        HashMap<Long, Odontologo> odontologos = new HashMap<>();

        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Odontologo odontologo = new Odontologo(resultSet.getLong("id"), resultSet.getString("matricula"), resultSet.getString("nombre"), resultSet.getString("apellido"));

                odontologos.put(odontologo.getId(), odontologo);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                LOGGER.error("La conexion no se pudo cerrar" + exception.getMessage());
            }
        }
        LOGGER.info("Odontologos: " + odontologos);
        return odontologos;
    }
}
