package com.ferreteria.app_web_ferreteria.controllers;

import com.ferreteria.app_web_ferreteria.model.Pedido;
import com.ferreteria.app_web_ferreteria.model.Cliente;
import com.ferreteria.app_web_ferreteria.security.entity.User;
import com.ferreteria.app_web_ferreteria.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<Page<Pedido>> getAllPedidos(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Pedido> pedidos = pedidoService.findAllPedidos(pageable);
        if (pedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pedido>> getPedidoById(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.findPedidoById(id);
        return pedido.map(value -> new ResponseEntity<>(pedido, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Pedido> createOrUpdatePedido(@RequestBody Pedido pedido) {
        Pedido pedidoRetorno;
        if (pedido.getId() != null) {
            pedidoRetorno = pedidoService.updatePedido(pedido);
        } else {
            pedidoRetorno = pedidoService.savePedido(pedido);
        }
        return new ResponseEntity<>(pedidoRetorno, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<Page<Pedido>> getPedidosByCliente(@PathVariable Long clienteId,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        Page<Pedido> pedidos = pedidoService.findByCliente(cliente, pageable);
        if (pedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        }
    }

    @GetMapping("/vendedor/{vendedorId}")
    public ResponseEntity<Page<Pedido>> getPedidosByVendedor(@PathVariable Long vendedorId,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        User vendedor = new User();
        vendedor.setId(vendedorId);
        Page<Pedido> pedidos = pedidoService.findByVendedor(vendedor, pageable);
        if (pedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        }
    }

    @GetMapping("/fecha")
    public ResponseEntity<Page<Pedido>> getPedidosByFecha(@RequestParam String inicio,
                                                         @RequestParam String fin,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Timestamp fechaInicio = Timestamp.valueOf(inicio);
        Timestamp fechaFin = Timestamp.valueOf(fin);
        Page<Pedido> pedidos = pedidoService.findByFechaPedidoBetween(fechaInicio, fechaFin, pageable);
        if (pedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        }
    }

    @GetMapping("/cliente-vendedor")
    public ResponseEntity<Page<Pedido>> getPedidosByClienteAndVendedor(@RequestParam Long clienteId,
                                                                      @RequestParam Long vendedorId,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        User vendedor = new User();
        vendedor.setId(vendedorId);
        Page<Pedido> pedidos = pedidoService.findByClienteAndVendedor(cliente, vendedor, pageable);
        if (pedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        }
    }

    @GetMapping("/cliente-fecha")
    public ResponseEntity<Page<Pedido>> getPedidosByClienteAndFecha(@RequestParam Long clienteId,
                                                                   @RequestParam String inicio,
                                                                   @RequestParam String fin,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        Timestamp fechaInicio = Timestamp.valueOf(inicio);
        Timestamp fechaFin = Timestamp.valueOf(fin);
        Page<Pedido> pedidos = pedidoService.findByClienteAndFechaPedidoBetween(cliente, fechaInicio, fechaFin, pageable);
        if (pedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        }
    }

    @GetMapping("/vendedor-fecha")
    public ResponseEntity<Page<Pedido>> getPedidosByVendedorAndFecha(@RequestParam Long vendedorId,
                                                                    @RequestParam String inicio,
                                                                    @RequestParam String fin,
                                                                    @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        User vendedor = new User();
        vendedor.setId(vendedorId);
        Timestamp fechaInicio = Timestamp.valueOf(inicio);
        Timestamp fechaFin = Timestamp.valueOf(fin);
        Page<Pedido> pedidos = pedidoService.findByVendedorAndFechaPedidoBetween(vendedor, fechaInicio, fechaFin, pageable);
        if (pedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        }
    }

    @GetMapping("/cliente-vendedor-fecha")
    public ResponseEntity<Page<Pedido>> getPedidosByClienteVendedorFecha(@RequestParam Long clienteId,
                                                                        @RequestParam Long vendedorId,
                                                                        @RequestParam String inicio,
                                                                        @RequestParam String fin,
                                                                        @RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        User vendedor = new User();
        vendedor.setId(vendedorId);
        Timestamp fechaInicio = Timestamp.valueOf(inicio);
        Timestamp fechaFin = Timestamp.valueOf(fin);
        Page<Pedido> pedidos = pedidoService.findByClienteAndVendedorAndFechaPedidoBetween(cliente, vendedor, fechaInicio, fechaFin, pageable);
        if (pedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        }
    }

    @GetMapping("/estado")
    public ResponseEntity<Page<Pedido>> getPedidosByEstado(@RequestParam(required = false) String estado,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Pedido> pedidos = pedidoService.findByEstado(estado, pageable);
        if (pedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        }
    }
} 