package com.transacciones.projectSpringTransactions.model.bo;

import lombok.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private int cedula;

}
