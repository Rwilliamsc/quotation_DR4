package br.com.javacloud.quotation.controller;

import br.com.javacloud.quotation.model.domain.Quotation;
import br.com.javacloud.quotation.model.service.QuotationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

    @GetMapping
    @Operation(summary = "get all quotations")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "get all quotations") })
    public List<Quotation> findAll() {
        return quotationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get quotation By Id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "get quotation By Id"),
            @ApiResponse(responseCode = "404", description = "quotation not found") })
    public ResponseEntity<Quotation> findById(@PathVariable Integer id) {
        Optional<Quotation> quotation = quotationService.findById(id);
        if (quotation.isPresent()) {
            return new ResponseEntity<>(quotation.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "create quotation")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "create quotation") })
    public ResponseEntity<Quotation> create(@RequestBody Quotation quotation) {
        try {
            Quotation savedQuotation = quotationService.save(quotation);
            return new ResponseEntity<>(savedQuotation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "update quotation")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "update quotation"),
            @ApiResponse(responseCode = "404", description = "quotation not found") })
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

    @DeleteMapping("/{id}")
    @Operation(summary = "delete quotation")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "delete quotation"),
            @ApiResponse(responseCode = "404", description = "quotation not found") })
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
