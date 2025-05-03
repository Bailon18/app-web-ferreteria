package com.ferreteria.app_web_ferreteria.controllers;

import com.ferreteria.app_web_ferreteria.model.Producto;
import com.ferreteria.app_web_ferreteria.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/list")
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.findAllProductos();
        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productos, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Producto>> getAllProductosPaginados(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Producto> productos = productoService.findAllProductos(pageable);
        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productos, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Producto>> getProductoById(@PathVariable Long id) {
        Optional<Producto> producto = productoService.findProductoById(id);
        return producto.map(value -> new ResponseEntity<>(producto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, "multipart/form-data;charset=UTF-8" })
    public ResponseEntity<Producto> createOrUpdateProducto(
            @RequestPart("producto") Producto producto,
            @RequestPart(name = "foto", required = false) MultipartFile foto
    ) throws IOException {
        if (foto != null && !foto.isEmpty()) {
            producto.setFoto(foto.getBytes());
        }
        Producto productoRetorno;
        if (producto.getId() != null) {
            productoRetorno = productoService.updateProducto(producto);
        } else {
            productoRetorno = productoService.saveProducto(producto);
        }
        return new ResponseEntity<>(productoRetorno, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filtro")
    public ResponseEntity<Page<Producto>> getProductoFiltro(@RequestParam String textoBuscar,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Producto> productos = productoService.filtrarProducto(textoBuscar, pageable);
        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productos, HttpStatus.OK);
        }
    }

    @GetMapping("/estado")
    public ResponseEntity<Page<Producto>> getProductosEstado(@RequestParam Boolean estado,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Producto> productos = productoService.listarProductosEstado(estado, pageable);
        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productos, HttpStatus.OK);
        }
    }
} 