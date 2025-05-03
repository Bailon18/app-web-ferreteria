package com.ferreteria.app_web_ferreteria.services;

import com.ferreteria.app_web_ferreteria.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> findAllProductos();
    Page<Producto> findAllProductos(Pageable pageable);
    Optional<Producto> findProductoById(Long id);
    Producto saveProducto(Producto producto);
    Producto updateProducto(Producto producto);
    void deleteProducto(Long id);
    List<Producto> buscarProductoPorFiltro(String filtro);
    Page<Producto> listarProductosEstado(Boolean estado, Pageable pageable);
    Page<Producto> filtrarProducto(String textoBuscar, Pageable pageable);
} 