package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Fernando Valler on 11/01/2016.
 */
public class DBController {

    private SQLiteDatabase db;
    private DB banco;
    private String campos[] = {"_id", "nome", "descricao", "tipo", "endereco", "numero", "bairro", "cidade", "valor"};
    private String campos_usuario[] = {"_id", "login", "senha"};
    private Cursor cursor;

    public DBController(Context contexto) {
        banco = new DB(contexto);
    }

    /* Cadastra um novo imovel */
    public String inserir (String nome, String descricao, String tipo, String endereco, String numero, String bairro, String cidade, String valor){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("descricao", descricao);
        valores.put("tipo", tipo);
        valores.put("endereco", endereco);
        valores.put("numero", numero);
        valores.put("bairro", bairro);
        valores.put("cidade", cidade);
        valores.put("valor", valor);

        resultado = db.insertOrThrow("imoveis", null, valores);
        db.close();

        if(resultado == -1)
            return "Erro ao Inserir o Registro!";
        else
          return "Registro inserido com sucesso!!";
    }

    /* Busca todos os imoveis para exibir na ListView */
    public Cursor buscarTodos(){
        db = banco.getReadableDatabase();
        cursor = db.query("imoveis", campos, null, null, null, null, null);

        if( cursor != null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    /* Pesquisa um imovel pelo _id do registro */
    public Cursor buscaPorId(int id){
        db = banco.getReadableDatabase();
        cursor = db.query("imoveis", campos, "_id = "+id, null, null, null, null, null);

        if ( cursor != null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    /* Atualiza um registro da tabela */
    public void alterar (int id, String nome, String descricao, String tipo, String endereco, String numero, String bairro, String cidade, String valor){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();

        valores.put("nome", nome);
        valores.put("descricao", descricao);
        valores.put("tipo", tipo);
        valores.put("endereco", endereco);
        valores.put("numero", numero);
        valores.put("bairro", bairro);
        valores.put("cidade", cidade);
        valores.put("valor", valor);

        db.update("imoveis", valores, "_id = " +id, null);
    }

    /* Exclui um resgitro do banco pelo seu ID */
    public void excluir(int id){
        db = banco.getReadableDatabase();
        db.delete("imoveis", "_id = "+ id, null);
    }

    /* Verifica se existe um usuario */
    public boolean logar(String login, String senha){
        db = banco.getReadableDatabase();
        cursor = db.query("usuarios", campos_usuario, " login = ? AND senha = ?", new String[]{login, senha}, null, null, null);
        if(cursor.moveToFirst()){ return true; }
        return false;
    }
}
