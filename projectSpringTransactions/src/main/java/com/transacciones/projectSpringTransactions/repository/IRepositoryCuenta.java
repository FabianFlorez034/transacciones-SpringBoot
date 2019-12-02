/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transacciones.projectSpringTransactions.repository;

import com.transacciones.projectSpringTransactions.model.entities.Cuenta;
import com.transacciones.projectSpringTransactions.model.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author nflorez
 */
@Repository
public interface IRepositoryCuenta extends JpaRepository<Cuenta, Integer>{

    List<Cuenta> findCuentasByIdPersona(Persona idPersona);


    
}
