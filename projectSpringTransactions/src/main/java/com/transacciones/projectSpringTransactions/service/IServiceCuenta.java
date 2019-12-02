package com.transacciones.projectSpringTransactions.service;

import com.transacciones.projectSpringTransactions.model.bo.CuentaDTO;
import com.transacciones.projectSpringTransactions.model.bo.TranssaccionesDTO;
import com.transacciones.projectSpringTransactions.model.entities.Cuenta;

import java.util.List;

public interface IServiceCuenta {

    void crearCuenta(CuentaDTO cuentaDTO)throws Exception;

    Cuenta findById(Integer id)throws Exception;

    String findByIdExpiration(Integer id)throws Exception;

    List<Cuenta> listarCuentasByPerson(int cedula)throws Exception;

    void Transferencia(TranssaccionesDTO transsaccionesDTO)throws Exception;

}
