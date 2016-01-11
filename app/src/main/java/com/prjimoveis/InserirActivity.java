package com.prjimoveis;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.DBController;

public class InserirActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        Button salvar = (Button)findViewById(R.id.btnSalvar);
        Button cancelar = (Button)findViewById(R.id.btnCancelar);

        salvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DBController crud = new DBController(getBaseContext());

                EditText edtNome = (EditText)findViewById(R.id.edtNome);
                EditText edtDescricao = (EditText)findViewById(R.id.edtDescricao);
                EditText edtTipo = (EditText)findViewById(R.id.edtTipo);
                EditText edtEndereco = (EditText)findViewById(R.id.edtEndereco);
                EditText edtNumero = (EditText)findViewById(R.id.edtNumero);
                EditText edtBairro = (EditText)findViewById(R.id.edtBairro);
                EditText edtCidade = (EditText)findViewById(R.id.edtCidade);
                EditText edtValor = (EditText)findViewById(R.id.edtValor);

                String nome = edtNome.getText().toString();
                String descricao = edtDescricao.getText().toString();
                String tipo = edtTipo.getText().toString();
                String endereco = edtEndereco.getText().toString();
                String numero = edtNumero.getText().toString();
                String bairro = edtBairro.getText().toString();
                String cidade = edtCidade.getText().toString();
                String valor = edtValor.getText().toString();

                String msg;

                if( !nome.equals("") ){
                    msg = crud.inserir(nome, descricao, tipo, endereco, numero, bairro, cidade, valor);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(InserirActivity.this, ConsultarActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), R.string.msg_camposobrigatorios, Toast.LENGTH_LONG).show();
                }

                /*Intent intent = new Intent(InserirActivity.this, ConsultarActivity.class);
                startActivity(intent);
                finish();*/
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}



