package com.prjimoveis;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.DBController;

public class AlterarActivity extends Activity {

    private Cursor cursor;
    private DBController crud;
    private String codigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        /* Recebe o codigo envido pelo Activity */
        codigo = this.getIntent().getStringExtra("codigo");

        crud = new DBController(getBaseContext());

        final EditText edtNome = (EditText)findViewById(R.id.edtNome);
        final EditText edtDescricao = (EditText)findViewById(R.id.edtDescricao);
        final EditText edtTipo = (EditText)findViewById(R.id.edtTipo);
        final EditText edtEndereco = (EditText)findViewById(R.id.edtEndereco);
        final EditText edtNumero = (EditText)findViewById(R.id.edtNumero);
        final EditText edtBairro = (EditText)findViewById(R.id.edtBairro);
        final EditText edtCidade = (EditText)findViewById(R.id.edtCidade);
        final EditText edtValor = (EditText)findViewById(R.id.edtValor);

        cursor = crud.buscaPorId(Integer.parseInt(codigo));

        edtNome.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        edtDescricao.setText(cursor.getString(cursor.getColumnIndexOrThrow("descricao")));
        edtTipo.setText(cursor.getString(cursor.getColumnIndexOrThrow("tipo")));
        edtEndereco.setText(cursor.getString(cursor.getColumnIndexOrThrow("endereco")));
        edtNumero.setText(cursor.getString(cursor.getColumnIndexOrThrow("numero")));
        edtBairro.setText(cursor.getString(cursor.getColumnIndexOrThrow("bairro")));
        edtCidade.setText(cursor.getString(cursor.getColumnIndexOrThrow("cidade")));
        edtValor.setText(cursor.getString(cursor.getColumnIndexOrThrow("valor")));

        Button editar = (Button)findViewById(R.id.btnSalvar2);
        editar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                crud.alterar(Integer.parseInt(codigo), edtNome.getText().toString(), edtDescricao.getText().toString(), edtTipo.getText().toString(), edtEndereco.getText().toString(), edtNumero.getText().toString(), edtBairro.getText().toString(), edtCidade.getText().toString(), edtValor.getText().toString());
                Toast.makeText(getApplicationContext(), R.string.msg_alterado, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AlterarActivity.this, ConsultarActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button excluir = (Button)findViewById(R.id.btnExcluir);
        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.excluir(Integer.parseInt(codigo));
                Toast.makeText(getApplicationContext(), R.string.msg_excluido , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AlterarActivity.this,ConsultarActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

