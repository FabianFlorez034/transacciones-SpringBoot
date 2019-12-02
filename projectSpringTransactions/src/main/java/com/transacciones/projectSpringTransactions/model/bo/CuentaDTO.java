package com.transacciones.projectSpringTransactions.model.bo;

import com.transacciones.projectSpringTransactions.model.entities.Persona;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private double saldo;
    private Date fechaExpiracion;
    private Integer idPersona;
}
