package com.example.tagandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tagandroid.R;
import com.example.tagandroid.model.Cart;
import com.example.tagandroid.model.CartProduct;

import java.util.ArrayList;

public class PurchaseActivity extends AppCompatActivity {
    private Cart cart = Cart.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        setTitle("Purchase");
        setProductsListViewConfig();
        setTotalPriceTextViewConfgi();
        setHomeButtonConfig();
        sendPurchaseEventToFirebase();
    }

    private void setHomeButtonConfig() {
        Button btn = findViewById(R.id.activity_purchase_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseActivity.this, ProductsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void sendPurchaseEventToFirebase() {
        ArrayList<CartProduct> items = cart.getCartProductsList();
    }

    private void setProductsListViewConfig() {
        ListView lv = findViewById(R.id.activity_purchase_products_lv);
        setProductsListAdapterConfig(lv);
    }

    private void setProductsListAdapterConfig(ListView lv) {
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cart.getCartProductsList()));
    }

    private void setTotalPriceTextViewConfgi() {
        TextView totalTextView = findViewById(R.id.activity_purchase_total_tv);

        totalTextView.setText("Total Purchase: R$" + String.format("%.2f", getTotalPriceFromItems()));
    }

    private double getTotalPriceFromItems() {
        double total = 0;
        for (CartProduct cp : cart.getCartProductsList())
            total += cp.getProduct().getPrice() * cp.getQuantity();
        return total;
    }

    @Override
    protected void onStop() {
        super.onStop();
        cart.clear();
    }
}
