/**
 * Created by Djidjelly Siclait on 11/1/2016.
 */
package com.clinichelper.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "items")
public class Product implements Serializable {
    //Attributes
    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private Float productPrice;
    private Integer productInStock;
    @ManyToOne
    private Clinic clinic;

    // Constructors
    public Product(){

    }

    public Product(Clinic clinic, String productName, String productDescription, Float productPrice, Integer productInStock){
        this.setProductId(clinic.getClinicPrefix() + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setProductName(productName);
        this.setProductDescription(productDescription);
        this.setProductPrice(productPrice);
        this.setProductInStock(productInStock);
        this.setClinic(clinic);
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductInStock() {
        return productInStock;
    }

    public void setProductInStock(Integer productInStock) {
        this.productInStock = productInStock;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
