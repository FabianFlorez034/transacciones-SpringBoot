/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transacciones.projectSpringTransactions.service.imp;

import com.transacciones.projectSpringTransactions.model.bo.PersonaDTO;
import com.transacciones.projectSpringTransactions.model.entities.Persona;
import com.transacciones.projectSpringTransactions.util.ValidarDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.transacciones.projectSpringTransactions.repository.IRepositoryPersona;
import com.transacciones.projectSpringTransactions.service.IServicePersona;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author nflorez
 */
@Service("servicePersona")
public class ServicePersona implements IServicePersona {

    @Autowired
    IRepositoryPersona repository;


    @Override
    public void crearPersona(PersonaDTO personaDTO) throws Exception {

        Persona searchPErsona = repository.findPersonaByCedula(personaDTO.getCedula());
        if (searchPErsona != null) {


            throw new ValidarDatos("El número de cédula ya esta registrado");
        }
        if (personaDTO.getCedula() == 0) {
            throw new Exception("Ingrese el campo cédula");
        }
        if (personaDTO.getNombre() == null) {
            throw new Exception("Ingrese el campo nombre");
        }

        Persona persona = Persona.builder()
                .nombre(personaDTO.getNombre())
                .cedula(personaDTO.getCedula())
                .build();
        repository.save(persona);
    }

    @Override
    public Persona getPersona(int cedula) throws Exception {
        Persona searchPersona = repository.findPersonaByCedula(cedula);
        if (searchPersona == null)

            throw new ValidarDatos("El número de cédula no está registrado");

        return searchPersona;
    }

    @Override
    public Persona getPersonaById(Integer id_persona) throws Exception {
        Optional<Persona> searchPersonaById = repository.findById(id_persona);
        if (!searchPersonaById.isPresent())

            throw new ValidarDatos("El id no está registrado");

        return searchPersonaById.get();


    }

    @Override
    public void updatePerson(Integer id_persona, PersonaDTO personaDTO) throws Exception {
        Optional<Persona> searchPersonaById = repository.findById(id_persona);
        //utilizo el método de buscar por cédula para validar la actualización del campo cédula
        Persona searchPersonaByCedula = repository.findPersonaByCedula(personaDTO.getCedula());

        if (!searchPersonaById.isPresent())

            throw new Exception("El id no está registrado");

        if (searchPersonaByCedula != null)
            throw new Exception("Número de cédula no valido, ya pertenece a otra persona.");

        searchPersonaById.get().setCedula(personaDTO.getCedula());
        searchPersonaById.get().setNombre(personaDTO.getNombre());
        repository.save(searchPersonaById.get());

        /* Otra Forma de actualizar es instanciar el objeto y construirlo

        Persona persona = Persona.builder()
                .IdPersona(id_persona)
                .Nombre(personaDTO.getNombre())
                .Cedula(personaDTO.getCedula())
                .build();
        repository.save(persona);
        */

    }

    @Override
    public List<PersonaDTO> findAll() throws Exception {
        List<Persona> allPeople = repository.findAll();

        List<PersonaDTO> listPersonaDto = new ArrayList<>();

        for (Persona persona : allPeople) {
            listPersonaDto.add(PersonaDTO.builder()
                    .cedula(persona.getCedula())
                    .nombre(persona.getNombre())
                    .build()

            );
        }

        return listPersonaDto;
    }

    @Override
    public void deletePerson(int cedula) throws Exception {

        Persona persona = repository.findPersonaByCedula(cedula);
        if (persona == null)
            throw new Exception("El número de cédula no exite");

        repository.delete(persona);

    }


}
