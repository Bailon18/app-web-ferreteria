package com.ferreteria.app_web_ferreteria.services.impl;

import com.ferreteria.app_web_ferreteria.model.Pedido;
import com.ferreteria.app_web_ferreteria.model.Cliente;
import com.ferreteria.app_web_ferreteria.repository.PedidoRepository;
import com.ferreteria.app_web_ferreteria.security.entity.User;
import com.ferreteria.app_web_ferreteria.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public Page<Pedido> findAllPedidos(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    @Override
    public Optional<Pedido> findPedidoById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Pedido savePedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido updatePedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public Page<Pedido> findByCliente(Cliente cliente, Pageable pageable) {
        return pedidoRepository.findByCliente(cliente, pageable);
    }

    @Override
    public Page<Pedido> findByVendedor(User vendedor, Pageable pageable) {
        return pedidoRepository.findByVendedor(vendedor, pageable);
    }

    @Override
    public Page<Pedido> findByFechaPedidoBetween(Timestamp fechaInicio, Timestamp fechaFin, Pageable pageable) {
        return pedidoRepository.findByFechaPedidoBetween(fechaInicio, fechaFin, pageable);
    }

    @Override
    public Page<Pedido> findByClienteAndVendedor(Cliente cliente, User vendedor, Pageable pageable) {
        return pedidoRepository.findByClienteAndVendedor(cliente, vendedor, pageable);
    }

    @Override
    public Page<Pedido> findByClienteAndFechaPedidoBetween(Cliente cliente, Timestamp fechaInicio, Timestamp fechaFin, Pageable pageable) {
        return pedidoRepository.findByClienteAndFechaPedidoBetween(cliente, fechaInicio, fechaFin, pageable);
    }

    @Override
    public Page<Pedido> findByVendedorAndFechaPedidoBetween(User vendedor, Timestamp fechaInicio, Timestamp fechaFin, Pageable pageable) {
        return pedidoRepository.findByVendedorAndFechaPedidoBetween(vendedor, fechaInicio, fechaFin, pageable);
    }

    @Override
    public Page<Pedido> findByClienteAndVendedorAndFechaPedidoBetween(Cliente cliente, User vendedor, Timestamp fechaInicio, Timestamp fechaFin, Pageable pageable) {
        return pedidoRepository.findByClienteAndVendedorAndFechaPedidoBetween(cliente, vendedor, fechaInicio, fechaFin, pageable);
    }

    @Override
    public Page<Pedido> findByEstado(String estado, Pageable pageable) {
        return pedidoRepository.findByEstado(estado, pageable);
    }
} 