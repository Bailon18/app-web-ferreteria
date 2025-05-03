package com.ferreteria.app_web_ferreteria.repository;

import com.ferreteria.app_web_ferreteria.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query(value = "SELECT * FROM producto WHERE nombre LIKE CONCAT('%', :filtro, '%') OR color LIKE CONCAT('%', :filtro, '%')", nativeQuery = true)
    List<Producto> buscarProductoPorFiltro(@Param("filtro") String filtro);
    List<Producto> findByEstaActivoOrderByIdDesc(Boolean estaActivo);
} 