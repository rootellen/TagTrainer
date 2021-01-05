package com.example.tagandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tagandroid.R;
import com.example.tagandroid.model.Cart;
import com.example.tagandroid.model.CartProduct;
import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {
    private Cart cart = Cart.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Checkout");
        setTotalTextViewConfig();
        setPaymentConfirmButonConfg();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    private void setPaymentConfirmButonConfg() {
        Button btn = findViewById(R.id.activity_checkout_payment_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!paymentMethodChoosed().equals("")){
                    goToPurchaseActivity();
                }
            }
        });
    }


    
    private void goToPurchaseActivity() {
        Intent intent = new Intent(this, PurchaseActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private String paymentMethodChoosed(){
        RadioButton boletoCheck = findViewById(R.id.activity_checkout_rb_boleto);
        RadioButton cartaoCheck = findViewById(R.id.activity_checkout_rb_cartao);

        if(boletoCheck.isChecked()) return boletoCheck.getText().toString();
        if(cartaoCheck.isChecked()) return cartaoCheck.getText().toString();

        return "";
    }

    private void setTotalTextViewConfig() {
        TextView totalTextView = findViewById(R.id.activity_checkout_payment_tv);
        double totalPrice = 0;

        for (CartProduct cp : cart.getCartProductsList())
            totalPrice += cp.getTotalPrice();

        totalTextView.setText("Total: R$" + String.format("%.2f", totalPrice));

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
