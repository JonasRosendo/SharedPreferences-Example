package com.jonasrosendo.sharedpreferences;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText edt_nome;
    private Button btn_salvar;
    private TextView txv_usuario;
    private static final String ARQUIVO_PREF = "arquivo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_nome = findViewById(R.id.edt_nome);
        btn_salvar = findViewById(R.id.btn_salvar);
        txv_usuario = findViewById(R.id.txv_usuario);

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREF, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                if(edt_nome.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Preencha o nome!", Toast.LENGTH_SHORT).show();
                }else{
                    String nome = edt_nome.getText().toString();
                    editor.putString("nome", nome);
                    editor.commit();

                    txv_usuario.setText("Olá, " + nome);
                }
            }
        });

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREF, MODE_PRIVATE);
        if (preferences.contains("nome")){
            String nome = preferences.getString("nome", "usuário não definido");
            txv_usuario.setText("Olá, " + nome);
        }else {
            txv_usuario.setText("Olá, usuário não definido");
        }
    }
}
