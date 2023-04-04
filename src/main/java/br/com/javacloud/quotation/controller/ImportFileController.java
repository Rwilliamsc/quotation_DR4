package br.com.javacloud.quotation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.javacloud.quotation.model.domain.Product;
import br.com.javacloud.quotation.model.domain.Quotation;
import br.com.javacloud.quotation.model.service.ProductService;
import br.com.javacloud.quotation.model.service.QuotationService;

import java.io.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/files")
public class ImportFileController {
  @Autowired
  ProductService productService;
  @Autowired
  QuotationService quotationService;

  @PostMapping(value = "/import", consumes = "multipart/form-data", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String importFile(@RequestParam("file") MultipartFile file) throws IOException {
    File convFile = convertMultiPartToFile(file);
    FileReader fileR = new FileReader(convFile);
    BufferedReader readFile = new BufferedReader(fileR);

    String line = readFile.readLine();
    String[] field = null;

    while (line != null) {
      field = line.split(";");
      if (field[0] == "product") {
        insertProduct(field);
      } else if (field[0] == "product") {
        insertQuotation(field);
      }
      line = readFile.readLine();
    }
    convFile.deleteOnExit();
    return "";
  }

  private void insertProduct(String[] fields) {
    Product prod = new Product();
    prod.setName(fields[1]);
    prod.setDescription(fields[2]);
    prod.setPrice(Float.parseFloat(fields[3]));
    productService.save(prod);
  }

  private void insertQuotation(String[] fields) {
    Quotation quot = new Quotation();
    quot.setQuantity(Integer.parseInt(fields[1]));
    quot.setSupplier(fields[2]);
    quot.setTotalValue(Float.parseFloat(fields[3]));
    Product prod = new Product();
    prod.setId(Integer.parseInt(fields[4]));
    quot.setProduct(prod);
    quotationService.save(quot);
  }

  private File convertMultiPartToFile(MultipartFile file) throws IOException {
    File convFile = new File(file.getOriginalFilename());
    file.transferTo(convFile);
    return convFile;
  }
}
