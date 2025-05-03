package com.ferreteria.app_web_ferreteria.services.impl;

import com.ferreteria.app_web_ferreteria.model.Producto;
import com.ferreteria.app_web_ferreteria.repository.ProductoRepository;
import com.ferreteria.app_web_ferreteria.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAllProductos() {
        return productoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Producto> findAllProductos(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    public Optional<Producto> findProductoById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Producto producto) {
        Producto existente = productoRepository.findById(producto.getId()).orElse(null);
        if (existente != null) {
            existente.setNombre(producto.getNombre());
            existente.setColor(producto.getColor());
            existente.setDescripcion(producto.getDescripcion());
            existente.setPrecioCompra(producto.getPrecioCompra());
            existente.setPrecioVentaGalon(producto.getPrecioVentaGalon());
            existente.setPermiteGranel(producto.getPermiteGranel());
            existente.setPrecioMedioGalon(producto.getPrecioMedioGalon());
            existente.setPrecioCuartoGalon(producto.getPrecioCuartoGalon());
            existente.setPrecioOctavoGalon(producto.getPrecioOctavoGalon());
            existente.setPrecioDieciseisavoGalon(producto.getPrecioDieciseisavoGalon());
            existente.setPrecioTreintaidosavoGalon(producto.getPrecioTreintaidosavoGalon());
            existente.setStockTotal(producto.getStockTotal());
            existente.setStockMinimo(producto.getStockMinimo());
            existente.setCantidadCerrados(producto.getCantidadCerrados());
            existente.setCantidadAbiertos(producto.getCantidadAbiertos());
            existente.setEstante(producto.getEstante());
            existente.setFila(producto.getFila());
            existente.setArea(producto.getArea());
            existente.setEstaActivo(producto.getEstaActivo());
            if (producto.getFoto() != null && producto.getFoto().length > 0) {
                existente.setFoto(producto.getFoto());
            }
            return productoRepository.save(existente);
        }
        return null;
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> buscarProductoPorFiltro(String filtro) {
        return productoRepository.buscarProductoPorFiltro(filtro);
    }

    @Override
    public Page<Producto> listarProductosEstado(Boolean estado, Pageable pageable) {
        List<Producto> productos = productoRepository.findByEstaActivoOrderByIdDesc(estado);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Producto> sublist;

        if (productos.size() < startItem) {
            sublist = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, productos.size());
            sublist = productos.subList(startItem, toIndex);
        }

        return new PageImpl<>(sublist, pageable, productos.size());
    }

    @Override
    public Page<Producto> filtrarProducto(String textoBuscar, Pageable pageable) {
        List<Producto> productos = productoRepository.buscarProductoPorFiltro(textoBuscar);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Producto> sublist;

        if (productos.size() < startItem) {
            sublist = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, productos.size());
            sublist = productos.subList(startItem, toIndex);
        }

        return new PageImpl<>(sublist, pageable, productos.size());
    }
} 