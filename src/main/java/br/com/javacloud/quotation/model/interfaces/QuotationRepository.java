package br.com.javacloud.quotation.model.interfaces;

import br.com.javacloud.quotation.model.domain.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuotationRepository extends JpaRepository<Quotation, Integer> {
    @Query("FROM Quotation as q WHERE q.product.id = :id")
    List<Quotation> findByIdProduct(Integer id);
}