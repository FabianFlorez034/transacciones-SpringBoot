/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transacciones.projectSpringTransactions.repository;

import com.transacciones.projectSpringTransactions.model.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nflorez
 */

@Repository
public interface IRepositoryPersona extends JpaRepository<Persona, Integer>{

    Persona findPersonaByCedula(int cedula);
}
