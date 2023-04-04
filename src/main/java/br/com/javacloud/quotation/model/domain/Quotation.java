package br.com.javacloud.quotation.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Quotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String supplier;
    private Integer quantity;
    private Float totalValue;
    @ManyToOne
    private Product product;

    @Override
    public String toString() {
        return "Quotation{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", totalValue=" + totalValue +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Float totalValue) {
        this.totalValue = totalValue;
    }
}
