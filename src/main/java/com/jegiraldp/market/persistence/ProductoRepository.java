package com.jegiraldp.market.persistence;

import com.jegiraldp.market.domain.Product;
import com.jegiraldp.market.domain.repository.ProductRepository;
import com.jegiraldp.market.persistence.crud.ProductoCrudRepository;
import com.jegiraldp.market.persistence.entities.Producto;
import com.jegiraldp.market.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public class ProductoRepository implements ProductRepository {
    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);

    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos =    productoCrudRepository.findByIdCategoria(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto->mapper.toProduct(producto));

    }

    @Override
    public Product save(Product product) {
        Producto producto=mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }
}
