package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRespository;
import vn.hoidanit.laptopshop.repository.RoleRepository;

@Service
public class ProductService {
    private final ProductRespository productRespository;
    private final RoleRepository roleRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(ProductRespository productRespository, RoleRepository roleRepository,
            CartRepository cartRepository, CartDetailRepository cartDetailRepository, UserService userService) {
        this.productRespository = productRespository;
        this.roleRepository = roleRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
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

    public void handleAddProductToCart(String email, long productId) {
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                // tao moi cart
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(1);
                cart = this.cartRepository.save(newCart);
            }

            // luu cart_detail
            // tim product by id
            Product product = this.productRespository.findById(productId);
            if (product != null) {
                Product realProduct = product;
                CartDetail cartDetail = new CartDetail();
                cartDetail.setCart(cart);
                cartDetail.setProduct(realProduct);
                cartDetail.setPrice(realProduct.getPrice());
                cartDetail.setQuantity(1);
                this.cartDetailRepository.save(cartDetail);
            }

        }
    }
}
