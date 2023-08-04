package com.example.cardioexample;

import android.content.Intent;
import android.net.wifi.ScanResult;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class MainActivity extends AppCompatActivity {
    private static final int SCAN_RESULT = 100;
    private TextView textViewNumCartao;
    private TextView textViewDataExp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Inicia componentes*/
        init();
    }

    private void init() {
        textViewNumCartao = (TextView) findViewById(R.id.textViewCartao);
            textViewDataExp = (TextView) findViewById(R.id.textViewDataExp);
        }

    public void scanCartao(View view) {
        Intent intent = new Intent(this, CardIOActivity.class)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false);
        startActivityForResult(intent, SCAN_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SCAN_RESULT) {
            if(data!= null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
                textViewNumCartao.setText(scanResult.getRedactedCardNumber());

                if(scanResult.isExpiryValid()) {
                    String mesExp = String.valueOf(scanResult.expiryMonth);
                    String anoExp = String.valueOf(scanResult.expiryYear);
                    String dataExp = mesExp + "/" + anoExp;
                    textViewDataExp.setText(dataExp);
                }
            }
        }
    }
}