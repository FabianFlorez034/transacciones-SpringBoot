/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transacciones.projectSpringTransactions.service.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import com.transacciones.projectSpringTransactions.model.bo.CuentaDTO;
import com.transacciones.projectSpringTransactions.model.bo.TranssaccionesDTO;
import com.transacciones.projectSpringTransactions.model.entities.Cuenta;
import com.transacciones.projectSpringTransactions.model.entities.Persona;
import com.transacciones.projectSpringTransactions.repository.IRepositoryPersona;
import com.transacciones.projectSpringTransactions.service.IServiceCuenta;
import com.transacciones.projectSpringTransactions.service.IServicePersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.transacciones.projectSpringTransactions.repository.IRepositoryCuenta;

/**
 * @author nflorez
 */
@Service
public class ServiceCuenta implements IServiceCuenta {

    @Autowired
    IRepositoryCuenta repository;

    @Autowired
    IRepositoryPersona repositoryPersona;


    @Override
    public void crearCuenta(CuentaDTO cuentaDTO) throws Exception {

        Optional<Persona> searchPerson = repositoryPersona.findById(cuentaDTO.getIdPersona());
        if (!searchPerson.isPresent()) {
            throw new Exception("El id de la persona NO existe");
        }
        if (cuentaDTO.getSaldo() == 0) {
            throw new Exception("El saldo no es valido");
        }
        Cuenta cuenta = Cuenta.builder().saldo(cuentaDTO.getSaldo()).
                fechaExpiracion(cuentaDTO.getFechaExpiracion()).
                idPersona(searchPerson.get()).build();

        repository.save(cuenta);


    }

    @Override
    public Cuenta findById(Integer id) throws Exception {

        //guardo el id de la cuenta a buscar, este si es un Optional y puedo usar el .isPresent
        //Es optional por que el findById es propio del repository
        Optional<Cuenta> searchCuenta = repository.findById(id);
        if (!searchCuenta.isPresent())
            throw new Exception("El id de la cuenta NO existe");



        return searchCuenta.get();
    }

    @Override
    public List<Cuenta> listarCuentasByPerson(int cedula) throws Exception {
        Persona searchPerson = repositoryPersona.findPersonaByCedula(cedula);

        if (searchPerson == null) {
            throw new Exception("El id de la persona NO existe");
        }

        List<Cuenta> cuentaList = repository.findCuentasByIdPersona(searchPerson);

        return cuentaList;
    }

    @Override
    public void Transferencia(TranssaccionesDTO transsaccionesDTO) throws Exception {
        Optional<Cuenta> searchCuentaOri = repository.findById(transsaccionesDTO.getIdOrigen());
        Optional<Cuenta> searchCuentaDest = repository.findById(transsaccionesDTO.getIdDestino());
        Date fechaActual = new Date();


        if (!searchCuentaOri.isPresent())
            throw new Exception("La cuenta de origen no existe!");

        if (searchCuentaOri.get().getFechaExpiracion().getTime()<=fechaActual.getTime())
            throw  new  Exception("La cuenta de origen expiró el "+ searchCuentaOri.get().getFechaExpiracion());

        if (!searchCuentaDest.isPresent())
            throw new Exception("La cuenta de destino no existe!");


        if (searchCuentaDest.get().getFechaExpiracion().getTime()<=fechaActual.getTime()){
            throw  new  Exception("La cuenta de destino expiró el "+ searchCuentaDest.get().getFechaExpiracion());
        }

        if (searchCuentaOri.get().getSaldo() <= transsaccionesDTO.getCantidad())
            throw new Exception("No cuenta con saldo suficiente, la cuenta no puede quedar en ceros.");

        double saldoFinalOri = searchCuentaOri.get().getSaldo() - transsaccionesDTO.getCantidad();
        searchCuentaOri.get().setSaldo(saldoFinalOri);
        repository.save(searchCuentaOri.get());
        double saldoFinalDest = searchCuentaDest.get().getSaldo() + transsaccionesDTO.getCantidad();
        searchCuentaDest.get().setSaldo(saldoFinalDest);
        repository.save(searchCuentaDest.get());

    }

    @Override
    public String findByIdExpiration(Integer id) throws Exception {

        //guardo el id de la cuenta a buscar, este si es un Optional y puedo usar el .isPresent
        Optional<Cuenta> searchCuenta = repository.findById(id);
        if (!searchCuenta.isPresent())
            throw new Exception("El id de la cuenta NO existe");


        Date fechaActual = new Date();
        String response = null;

        if (searchCuenta.get().getFechaExpiracion().getTime()<=fechaActual.getTime()){
            response= "La cuenta expiró el "+ searchCuenta.get().getFechaExpiracion();
        }
        else {
            response="La cuenta esta Activa hasta el "+ searchCuenta.get().getFechaExpiracion();
        }
            return response;

    }
}
