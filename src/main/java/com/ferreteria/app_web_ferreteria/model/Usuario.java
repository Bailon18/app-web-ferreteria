package com.ferreteria.app_web_ferreteria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import com.ferreteria.app_web_ferreteria.util.TipoUsuario;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentoIdentidad;
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String correoElectronico;
    private String contrasena;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private boolean activo;
}
