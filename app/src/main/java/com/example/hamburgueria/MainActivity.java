package com.example.hamburgueria;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            Button mais = findViewById(R.id.btnMais);
            Button menos = findViewById(R.id.btnMenos);
            Button submit = findViewById(R.id.btnSubmit);
            TextView qtdItens = findViewById(R.id.qtd_Itens);
            CheckBox bacon = findViewById(R.id.checkBacon);
            CheckBox queijo = findViewById(R.id.checkQueijo);
            CheckBox onion = findViewById(R.id.checkOnion);

            configurarbotoes(mais, menos, submit, qtdItens, bacon, queijo, onion);
            return insets;
        });

    }

    private void configurarbotoes(Button mais, Button menos, Button submit, TextView qtdItens, CheckBox bacon, CheckBox queijo, CheckBox onion) {
        mais.setOnClickListener(view -> {
            int quantidade = Integer.parseInt(qtdItens.getText().toString());
            quantidade++;
            qtdItens.setText(String.valueOf(quantidade));
        });
        menos.setOnClickListener(view -> {
            int quantidade = Integer.parseInt(qtdItens.getText().toString());
            if (quantidade > 0) {
                quantidade--;
                qtdItens.setText(String.valueOf(quantidade));
            }
        });
        submit.setOnClickListener(view -> {
            int preço_final = getPrecoFinal(qtdItens, bacon, queijo, onion);

            TextView total = findViewById(R.id.total);
            total.setText("Total: R$ " + preço_final + ",00");

        });

    }

    private int getPrecoFinal(TextView qtdItens, CheckBox bacon, CheckBox queijo, CheckBox onion) {
        int preço_final = 0;
        int qtd_itens = Integer.parseInt(qtdItens.getText().toString());
        int adicionais = checkAdicionais(bacon, queijo, onion);
        preço_final = qtd_itens * 20 + adicionais;
        return preço_final;
    }

    private int checkAdicionais(CheckBox bacon, CheckBox queijo, CheckBox onion) {
        int res = 0;
        if (bacon.isChecked()) {
            res += 2;
        }
        if (queijo.isChecked()) {
            res += 2;
        }
        if (onion.isChecked()) {
            res += 3;
        }
        return res;
    }
}