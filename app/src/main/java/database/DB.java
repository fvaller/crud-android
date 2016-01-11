package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fernando Valler on 11/01/2016.
 */
public class DB extends SQLiteOpenHelper {

    public DB(Context context) {
        super(context, "imovel", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /* Cria a tabela de imoveis */
        String sql = "CREATE TABLE imoveis (_id integer primary key autoincrement, nome VARCHAR(100) NOT NULL, descricao VARCHAR(200)," +
                " tipo VARCHAR(50), endereco VARCHAR(200), numero VARCHAR(10), bairro VARCHAR(100), cidade VARCHAR(100), valor VARCHAR(20))";
        db.execSQL(sql);

        /* Cria a tabela de usuarios */
        db.execSQL("CREATE TABLE usuarios(_id integer primary key autoincrement, login VARCHAR(100), senha VARCHAR(100))");

        /* Adiciona um usuario */
        db.execSQL("INSERT INTO usuarios (login, senha) VALUES ('admin', 'admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS imoveis");
        onCreate(db);
    }
}
