package com.sit.InventoryManagementSystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sit.InventoryManagementSystem.dto.ProductDTO;
import com.sit.InventoryManagementSystem.dto.Response;
import com.sit.InventoryManagementSystem.entity.Category;
import com.sit.InventoryManagementSystem.entity.Product;
import com.sit.InventoryManagementSystem.entity.User;
import com.sit.InventoryManagementSystem.exceptions.NotFoundException;
import com.sit.InventoryManagementSystem.mongo.service.AuditLogService;
import com.sit.InventoryManagementSystem.repository.CategoryRepository;
import com.sit.InventoryManagementSystem.repository.ProductRepository;
import com.sit.InventoryManagementSystem.repository.UserRepository;
import com.sit.InventoryManagementSystem.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	@Autowired
	private AuditLogService auditLogService;
	@Autowired
    private UserRepository userRepository;

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

//    @Override
//    public Response saveProduct(ProductDTO productDTO) {
//
//        Category category = categoryRepository.findById(productDTO.getCategoryId())
//                .orElseThrow(() -> new NotFoundException("Category Not Found"));
//
//        
//        Product productToSave = Product.builder()
//                .name(productDTO.getName())
//                .sku(productDTO.getSku())
//                .price(productDTO.getPrice())
//                .stockQuantity(productDTO.getStockQuantity())
//                .description(productDTO.getDescription())
//                .mgfDate(productDTO.getMgfDate())          
//                .expiryDate(productDTO.getExpiryDate()) 
//                .category(category)
//                .build();
//
//        
//        productRepository.save(productToSave);
//        return Response.builder()
//                .status(200)
//                .message("Product successfully saved")
//                .build();
//    }
    @Override
    public Response saveProduct(ProductDTO productDTO) {

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category Not Found"));

        Product productToSave = Product.builder()
                .name(productDTO.getName())
                .sku(productDTO.getSku())
                .price(productDTO.getPrice())
                .stockQuantity(productDTO.getStockQuantity())
                .description(productDTO.getDescription())
                .mgfDate(productDTO.getMgfDate())
                .expiryDate(productDTO.getExpiryDate())
                .category(category)
                .build();

        productRepository.save(productToSave);

        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("User not found"));

       
        auditLogService.logAction(
                user.getEmail(),
                user.getName(),
                "ADD_PRODUCT",
                "Added product: " + productDTO.getName() + ", SKU: " + productDTO.getSku()
        );

        return Response.builder()
                .status(200)
                .message("Product successfully saved")
                .build();
    }



    @Override
    public Response updateProduct(ProductDTO productDTO) {

        Product existingProduct = productRepository.findById(productDTO.getProductId())
                .orElseThrow(() -> new NotFoundException("Product Not Found"));

        //  category change keli teva check karel 
        if (productDTO.getCategoryId() != null && productDTO.getCategoryId() > 0) {
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category Not Found"));
            existingProduct.setCategory(category);
        }

        if (productDTO.getName() != null && !productDTO.getName().isBlank()) {
            existingProduct.setName(productDTO.getName());
        }

        if (productDTO.getSku() != null && !productDTO.getSku().isBlank()) {
            existingProduct.setSku(productDTO.getSku());
        }

        if (productDTO.getDescription() != null && !productDTO.getDescription().isBlank()) {
            existingProduct.setDescription(productDTO.getDescription());
        }

        if (productDTO.getPrice() != null && productDTO.getPrice().compareTo(BigDecimal.ZERO) >= 0) {
            existingProduct.setPrice(productDTO.getPrice());
        }

        if (productDTO.getStockQuantity() != null && productDTO.getStockQuantity() >= 0) {
            existingProduct.setStockQuantity(productDTO.getStockQuantity());
        }

       
        if (productDTO.getMgfDate() != null) {
            existingProduct.setMgfDate(productDTO.getMgfDate());
        }

        if (productDTO.getExpiryDate() != null) {
            existingProduct.setExpiryDate(productDTO.getExpiryDate());
        }
        
//         auditLogService.logAction(adminname, , null, null);
        
        productRepository.save(existingProduct);
        return Response.builder()
                .status(200)
                .message("Product successfully updated")
                .build();
    }


    @Override
    public Response getAllProducts() {

        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<ProductDTO> productDTOS = modelMapper.map(products, new TypeToken<List<ProductDTO>>() {}.getType());

        return Response.builder()
                .status(200)
                .message("success")
                .products(productDTOS)
                .build();
    }

    @Override
    public Response getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Not Found"));

        return Response.builder()
                .status(200)
                .message("success")
                .product(modelMapper.map(product, ProductDTO.class))
                .build();
    }

    @Override
    public Response deleteProduct(Long id) {

        productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Not Found"));

        productRepository.deleteById(id);

        return Response.builder()
                .status(200)
                .message("Product successfully deleted")
                .build();
    }

}
