package com.jegiraldp.market.persistence;

import com.jegiraldp.market.domain.Purchase;
import com.jegiraldp.market.domain.repository.PurchaseRepository;
import com.jegiraldp.market.persistence.crud.CompraCrudRepository;
import com.jegiraldp.market.persistence.entities.Compra;
import com.jegiraldp.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;


    @Override
    public List<Purchase> getAll() {

        return purchaseMapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(Integer.parseInt(clientId))
                .map(compras -> purchaseMapper.toPurchases((List<Compra>) compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }
}
