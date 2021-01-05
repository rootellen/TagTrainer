package com.example.tagandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tagandroid.R;
import com.example.tagandroid.model.Cart;
import com.example.tagandroid.model.CartProduct;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private ArrayAdapter<CartProduct> adapter;
    private Cart cart = Cart.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setTitle("Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setProductsListViewConfig();
        setCheckoutButtonConfig();
    }


    @Override
    protected void onResume() {
        super.onResume();
        refreshProductsList();
    }


    private void setCheckoutButtonConfig() {
        Button bnt = findViewById(R.id.activity_cart_goToCheckout_button);
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cart.getCartProductsList().size() > 0)
                    startCheckoutActivity();
            }
        });
    }
    public void startCheckoutActivity (){
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }
    public void setProductsListViewConfig(){

        ListView productsLv = findViewById(R.id.activity_cart_products_lv);
        setAdapterConfig(productsLv);
        registerForContextMenu(productsLv);

    }

    public void refreshProductsList(){
        adapter.clear();
        adapter.addAll(cart.getCartProductsList());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_cart_products_lv_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        CartProduct clickedProduct = adapter.getItem(menuInfo.position);

        if(itemId == R.id.activity_cart_products_lv_menu_add){
            cart.addToCart(clickedProduct.getProduct());

            refreshProductsList();
        }
        else {
            cart.removeFromCart(clickedProduct);

            refreshProductsList();
        }

        return super.onContextItemSelected(item);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setAdapterConfig(ListView lv) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
            lv.setAdapter(adapter);
    }

}
