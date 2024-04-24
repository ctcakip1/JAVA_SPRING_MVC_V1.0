package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.repository.ProductRespository;

@Service
public class ProductService {
    private final ProductRespository productRespository;

    public ProductService(ProductRespository productRespository) {
        this.productRespository = productRespository;
    }

    public Product createProduct(Product product) {
        return this.productRespository.save(product);
    }

    public List<Product> getAllProduct() {
        return this.productRespository.findAll();
    }
}
