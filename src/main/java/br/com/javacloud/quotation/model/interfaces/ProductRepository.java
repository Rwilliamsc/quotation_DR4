package br.com.javacloud.quotation.model.interfaces;

import br.com.javacloud.quotation.model.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {}