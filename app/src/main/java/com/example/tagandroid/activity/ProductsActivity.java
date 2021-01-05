package com.example.tagandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tagandroid.R;
import com.example.tagandroid.model.Cart;
import com.example.tagandroid.model.CartProduct;
import com.example.tagandroid.model.Product;
import com.example.tagandroid.utils.ProductsGenerator;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {
    private Cart cart = Cart.getInstance();
    private ArrayAdapter<Product> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        setTitle("Products");
        productListConfig();
        goToCartButtonConfig();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    private void goToCartButtonConfig() {
        Button goToCart = findViewById(R.id.activity_products_goToCart_button);

        goToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCartActivity();
            }
        });
    }

    private void goToCartActivity() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);

    }

    private void productListConfig() {
        ListView listView = findViewById(R.id.activity_products_lv);
        setProductListAdapterConfig(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product clickedProduct = (Product) parent.getItemAtPosition(position);
                cart.addToCart(clickedProduct);
                Toast.makeText(ProductsActivity.this, clickedProduct.getName() + " added to cart!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setProductListAdapterConfig(ListView listView) {
        List<Product> products = ProductsGenerator.getInstance().getProductsList();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        listView.setAdapter(adapter);

    }
}
