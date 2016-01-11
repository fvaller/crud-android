package com.prjimoveis;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import database.DBController;

public class ConsultarActivity extends Activity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        DBController crud = new DBController(getBaseContext());
        final Cursor cursor = crud.buscarTodos();

        String[] nomeCampos = new String[] {"_id", "nome", "descricao", "tipo", "endereco", "numero", "bairro", "cidade", "valor"};

        int[] idViews = new int[] { R.id.idImovel, R.id.nomeImovel, R.id.descricaoImovel };

        SimpleCursorAdapter adapatador = new SimpleCursorAdapter(getBaseContext(), R.layout.imoveis_layout, cursor, nomeCampos, idViews);

        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adapatador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;

                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));

                Intent intent = new Intent(ConsultarActivity.this, AlterarActivity.class);
                intent.putExtra("codigo", codigo); /* Passagem do ID para a AlterarActivity */
                startActivity(intent);
                finish();
            }
        });

        Button cadastrar = (Button)findViewById(R.id.btnCadImovel);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultarActivity.this, InserirActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button sair = (Button)findViewById(R.id.btnSair);
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultarActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

