package br.com.javacloud.quotation.model.interfaces;

import br.com.javacloud.quotation.model.domain.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotationRepository extends JpaRepository<Quotation, Integer> {}