package com.transacciones.projectSpringTransactions.service;

import com.transacciones.projectSpringTransactions.model.bo.PersonaDTO;
import com.transacciones.projectSpringTransactions.model.entities.Persona;
import com.transacciones.projectSpringTransactions.util.ValidarDatos;


import java.util.List;

public interface IServicePersona {

    void crearPersona(PersonaDTO personaDTO) throws Exception;

    Persona getPersona(int cedula)throws Exception;

    Persona getPersonaById(Integer id_persona)throws Exception;

    void updatePerson(Integer id_persona, PersonaDTO personaDTO)throws Exception;

    List<PersonaDTO> findAll()throws Exception;

    void deletePerson(int cedula)throws Exception;
}
