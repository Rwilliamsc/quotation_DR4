package br.com.javacloud.quotation.model.service;

import br.com.javacloud.quotation.model.domain.Quotation;
import br.com.javacloud.quotation.model.interfaces.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuotationService {

    @Autowired
    private QuotationRepository quotationRepository;

    public List<Quotation> findAll() {
        return quotationRepository.findAll();
    }

    public Optional<Quotation> findById(Integer id) {
        return quotationRepository.findById(id);
    }

    public Quotation save(Quotation product) {
        return quotationRepository.save(product);
    }

    public void deleteById(Integer id) {
        quotationRepository.deleteById(id);
    }

    public Quotation update(Quotation product) {
        return quotationRepository.save(product);
    }
}