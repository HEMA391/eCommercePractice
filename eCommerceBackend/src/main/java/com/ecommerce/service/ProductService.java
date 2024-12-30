package com.ecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Product;
import com.ecommerce.request.CreateProductRequest;

public interface ProductService {
	public Product createProduct(CreateProductRequest req);
	public String deleteProduct(Long productId) throws ProductException;
	public Product updateProduct(Long productId, Product reqProduct) throws ProductException;
	public Product findProductById(Long id) throws ProductException;
	public List<Product> findProductBycategory(String category);
	public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, 
			Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);
	public List<Product> findAllProducts();
}
