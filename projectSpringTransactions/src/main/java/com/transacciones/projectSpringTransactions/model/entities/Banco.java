package com.transacciones.projectSpringTransactions.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "banco", schema = "public")
@XmlRootElement
public class Banco implements Serializable{


    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //para generar el autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_banco")
    private Integer idBanco;
    @Basic(optional = false)
    @Column(name = "nombre_banco")
    private String nombreBanco;
    @Basic(optional = false)
    @Column(name = "cobro_transaccion")
    private double cobroTransaccion;

}
