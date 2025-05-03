package com.ferreteria.app_web_ferreteria.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "tipo_pintura_id", nullable = false)
    private TipoPintura tipoPintura;

    private String nombre;
    private String color;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
    private byte[] foto;


    private Double precioCompra;
    private Double precioVentaGalon;
    private Boolean permiteGranel;
    private Double precioMedioGalon;
    private Double precioCuartoGalon;
    private Double precioOctavoGalon;
    private Double precioDieciseisavoGalon;
    private Double precioTreintaidosavoGalon;
    private Integer stockTotal;
    private Integer stockMinimo;
    private Integer cantidadCerrados;
    private Integer cantidadAbiertos;
    private String estante;
    private String fila;
    private String area;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean estaActivo;
} 