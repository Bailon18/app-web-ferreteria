package com.ferreteria.app_web_ferreteria.repository;

import com.ferreteria.app_web_ferreteria.model.Pedido;
import com.ferreteria.app_web_ferreteria.model.Cliente;
import com.ferreteria.app_web_ferreteria.security.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.sql.Timestamp;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Page<Pedido> findByCliente(Cliente cliente, Pageable pageable);
    Page<Pedido> findByVendedor(User vendedor, Pageable pageable);
    Page<Pedido> findByFechaPedidoBetween(Timestamp fechaInicio, Timestamp fechaFin, Pageable pageable);
    Page<Pedido> findByClienteAndVendedor(Cliente cliente, User vendedor, Pageable pageable);
    Page<Pedido> findByClienteAndFechaPedidoBetween(Cliente cliente, Timestamp fechaInicio, Timestamp fechaFin, Pageable pageable);
    Page<Pedido> findByVendedorAndFechaPedidoBetween(User vendedor, Timestamp fechaInicio, Timestamp fechaFin, Pageable pageable);
    Page<Pedido> findByClienteAndVendedorAndFechaPedidoBetween(Cliente cliente, User vendedor, Timestamp fechaInicio, Timestamp fechaFin, Pageable pageable);
    @Query("SELECT p FROM Pedido p WHERE (:estado IS NULL OR p.estado = :estado)")
    Page<Pedido> findByEstado(@Param("estado") String estado, Pageable pageable);
} 