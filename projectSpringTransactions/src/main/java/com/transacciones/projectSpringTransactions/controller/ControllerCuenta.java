package com.transacciones.projectSpringTransactions.controller;

import com.transacciones.projectSpringTransactions.model.bo.CuentaDTO;
import com.transacciones.projectSpringTransactions.model.bo.PersonaDTO;
import com.transacciones.projectSpringTransactions.model.bo.TranssaccionesDTO;
import com.transacciones.projectSpringTransactions.model.entities.Cuenta;
import com.transacciones.projectSpringTransactions.service.imp.ServiceCuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerCuenta {

    @Autowired
    ServiceCuenta serviceCuenta;

    @PostMapping("/crearCuenta")
    public ResponseEntity<?> crearCuenta(@RequestBody CuentaDTO cuentaDTO) {
        try {
            serviceCuenta.crearCuenta(cuentaDTO);
            return new ResponseEntity<>(cuentaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/cuenta/{id}")
    public ResponseEntity<?> getCuenta(@PathVariable Integer id) {

        try {
            Cuenta cuenta = serviceCuenta.findById(id);
            return new ResponseEntity<>(cuenta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }


    }

    @GetMapping("/cuentas/{cedula}")
    public ResponseEntity<?> getCuentas(@PathVariable int cedula) {

        try {
            List<Cuenta> cuentaList = serviceCuenta.listarCuentasByPerson(cedula);
            return new ResponseEntity<>(cuentaList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }


    }

    @PutMapping("/transacciones")
    public ResponseEntity<?> Transation(@RequestBody TranssaccionesDTO transsaccionesDTO) {
        try {
            serviceCuenta.Transferencia(transsaccionesDTO);
            return new ResponseEntity<>("Transacción exitosa!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/cuentaExpi/{id}")
    public ResponseEntity<?> getCuentaExpiration(@PathVariable Integer id) {

        try {

            String response= serviceCuenta.findByIdExpiration(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
