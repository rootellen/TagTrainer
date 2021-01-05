package com.example.tagandroid.utils;

import com.example.tagandroid.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductsGenerator {
    private static ProductsGenerator productsGenerator;
    private List<Product> productsList;

    private ProductsGenerator() {
        this.productsList = generateProducts();
    }

    public static ProductsGenerator getInstance() {
        if (productsGenerator == null) {
            productsGenerator = new ProductsGenerator();
        }

        return productsGenerator;
    }

    private List<Product> generateProducts() {

        return new ArrayList<>(Arrays.asList(
                new Product("Tenis DC", "sku_1234", "Shoes", "42", 250.00),
                new Product("Camiseta Nike", "sku_3234", "T-SHIRT", "M", 90.00),
                new Product("Camiseta A", "sku_4321", "T-SHIRT", "Black", 55.00),
                new Product("Camiseta B", "sku_5321", "T-SHIRT", "White", 60.00),
                new Product("Tenis A", "sku_6321", "Shoes", "40", 199.99),
                new Product("Tenis B", "sku_7321", "Shoes", "41", 150.00),
                new Product("Calca B", "sku_9321", "Pants", "Black", 130.00)));
    }

    public List<Product> getProductsList() {
        // Para nao modificar a lista original de produtos da classe.
        return new ArrayList<>(productsList);
    }
}
