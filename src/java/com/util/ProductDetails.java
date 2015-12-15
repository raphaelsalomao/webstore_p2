
package com.util;

import java.util.List;
import javax.annotation.ManagedBean;
@ManagedBean
public class ProductDetails {  
    private List<String> product_name;    
    private List<String> product_description;
    private List<String> product_price;
    private List<String> product_brand;    

    public void setNome_produto(List<String> product_name) {        
        this.product_name = product_name;        
    }
    
    public List<String> getProduct_name() {
        if(product_name!=null){
            this.product_name=product_name;
            System.out.println("In USER BEAN");
        }
        return product_name;
    }

    public List<String> getProduct_description() {
        
        return product_description;
    }

    public void setDescricao_produto(List<String> product_description) {
        this.product_description = product_description;
    }

    public List<String> getProduct_price() {
        return product_price;
    }

    public void setPreco_produto(List<String> product_price) {
        this.product_price = product_price;
    }

    public List<String> getProduct_brand() {
        return product_brand;
    }

    public void setMarca_produto(List<String> product_brand) {
        this.product_brand = product_brand;
    }
}
