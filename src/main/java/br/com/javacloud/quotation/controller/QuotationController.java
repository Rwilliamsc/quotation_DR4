package br.com.javacloud.quotation.controller;

import br.com.javacloud.quotation.model.domain.Quotation;
import br.com.javacloud.quotation.model.domain.Quotation;
import br.com.javacloud.quotation.model.service.ProductService;
import br.com.javacloud.quotation.model.service.QuotationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quotations")
public class QuotationController {

    @Autowired
    private QuotationService quotationService;

    @ApiOperation(value = "List all products", response = List.class)
    @GetMapping
    public List<Quotation> findAll() {
        return quotationService.findAll();
    }

    @ApiOperation(value = "Find quotation by ID", response = Quotation.class)
    @GetMapping("/{id}")
    public ResponseEntity<Quotation> findById(@PathVariable Integer id) {
        Optional<Quotation> quotation = quotationService.findById(id);
        if (quotation.isPresent()) {
            return new ResponseEntity<>(quotation.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Create a new quotation", response = Quotation.class)
    @PostMapping
    public ResponseEntity<Quotation> create(@RequestBody Quotation quotation) {
        try {
            Quotation savedQuotation = quotationService.save(quotation);
            return new ResponseEntity<>(savedQuotation, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Update a quotation", response = Quotation.class)
    @PutMapping("/{id}")
    public ResponseEntity<Quotation> update(@PathVariable Integer id, @RequestBody Quotation quotation) {
        Optional<Quotation> optionalProduct = quotationService.findById(id);
        if (optionalProduct.isPresent()) {
            quotation.setId(id);
            Quotation savedProduct = quotationService.update(quotation);
            return new ResponseEntity<>(savedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Delete a quotation")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        Optional<Quotation> optionalProduct = quotationService.findById(id);
        if (optionalProduct.isPresent()) {
            quotationService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
