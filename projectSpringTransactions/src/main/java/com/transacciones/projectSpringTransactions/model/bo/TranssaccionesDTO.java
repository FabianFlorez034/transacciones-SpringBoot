package com.transacciones.projectSpringTransactions.model.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TranssaccionesDTO implements Serializable{


        private static final long serialVersionUID = 1L;

        private int idOrigen;
        private int idDestino;
        private double cantidad;


}
