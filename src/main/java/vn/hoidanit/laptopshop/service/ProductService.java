package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.repository.ProductRespository;
import vn.hoidanit.laptopshop.repository.RoleRepository;

@Service
public class ProductService {
    private final ProductRespository productRespository;
    private final RoleRepository roleRepository;

    public ProductService(ProductRespository productRespository, RoleRepository roleRepository) {
        this.productRespository = productRespository;
        this.roleRepository = roleRepository;
    }

    public Product hanleSaveProduct(Product product) {
        Product tuananh = this.productRespository.save(product);
        return tuananh;
    }

    public List<Product> getAllProduct() {
        return this.productRespository.findAll();
    }

    public Product getProductById(long id) {
        return this.productRespository.findById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

    public void deleteProductById(long id) {
        this.productRespository.deleteById(id);
    }
}
