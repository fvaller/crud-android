package com.prjimoveis;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import database.DBController;

public class MainActivity extends Activity {

    private DBController crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crud = new DBController(getBaseContext());
    }

    public void onClickEntrar(View v){

        EditText usuario = (EditText)findViewById(R.id.edtUsuario);
        EditText senha = (EditText)findViewById(R.id.edtSenha);

        String usuarioStr = usuario.getText().toString();
        String senhaStr = senha.getText().toString();

        if(usuario != null && !usuarioStr.equals("") && senha != null && !senhaStr.equals("")){

            boolean logado = crud.logar(usuarioStr, senhaStr);

            //if(usuarioStr.equals("admin") && senhaStr.equals("admin")){
            if(logado){
                Intent intent = new Intent(MainActivity.this, ConsultarActivity.class);
                startActivity(intent);
                finish();

            }else{
                senha.setText("");
                senha.clearFocus();
                Toast.makeText(getApplicationContext(), R.string.msg_senha_invalida, Toast.LENGTH_SHORT).show();
            }
        }else{
            senha.clearFocus();
            Toast.makeText(getApplicationContext(), R.string.msg_camposobrigatorios, Toast.LENGTH_SHORT).show();
        }

    }

}

