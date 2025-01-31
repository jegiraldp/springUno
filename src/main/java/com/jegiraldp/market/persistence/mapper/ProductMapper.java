package com.jegiraldp.market.persistence.mapper;

import com.jegiraldp.market.domain.Product;
import com.jegiraldp.market.persistence.entities.Producto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses=CategoryMapper.class)
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
          //  @Mapping(source = "categoria", target = "category"),
    })
    Product toProduct(Producto producto);
    List<Product> toProducts(List<Producto> productos);

    @InheritInverseConfiguration
    @Mapping(target="codigoBarra", ignore = true)
    Producto toProducto(Product product);
}
