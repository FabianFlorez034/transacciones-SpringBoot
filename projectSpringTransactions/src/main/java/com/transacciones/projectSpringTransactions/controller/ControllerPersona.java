package com.transacciones.projectSpringTransactions.controller;

import com.transacciones.projectSpringTransactions.model.bo.PersonaDTO;
import com.transacciones.projectSpringTransactions.model.entities.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.transacciones.projectSpringTransactions.service.imp.ServicePersona;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerPersona {
    @Autowired
    ServicePersona servicePersona;

    @PostMapping("/crearPersona")
    public ResponseEntity<?> crearPersona(@RequestBody PersonaDTO personaDTO) {
        try {
            servicePersona.crearPersona(personaDTO);
            return new ResponseEntity<>(personaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/persona/{cedula}")
    public ResponseEntity<?> findPersonaByCedula(@PathVariable int cedula){
        try {
            Persona persona = servicePersona.getPersona(cedula);
            return new ResponseEntity<>(persona, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/personaById/{id_persona}")
    public ResponseEntity<?> findPersonaByCedula(@PathVariable Integer id_persona){
        try {
            Persona persona = servicePersona.getPersonaById(id_persona);
            return new ResponseEntity<>(persona, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/actualizar/{id_persona}")
    public ResponseEntity<?> updatePerson(@PathVariable Integer id_persona, @RequestBody PersonaDTO personaDTO){
        try {
            servicePersona.updatePerson(id_persona, personaDTO);
            return new ResponseEntity<>(personaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/personas")
    public ResponseEntity<?> findAll(){
        try {
            List<Persona> listaPersonas = servicePersona.findAll();
            return new ResponseEntity<>(listaPersonas,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        }
    }



