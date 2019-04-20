package br.com.caicosoft.bancodedadossqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase bancoDados = openOrCreateDatabase("app",MODE_PRIVATE, null); // abrir ja existente ou criar novo bd MODE_PRIVATE: apenas o app tem acesso

            //criar tabela se ela nao existir
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoa (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INTEGER)");
            //bancoDados.execSQL("DROP TABLE pessoa");

            //inerir dados
            //bancoDados.execSQL("INSERT INTO pessoa(nome, idade) VALUES('Gerson Ferreira', 20)");
            //bancoDados.execSQL("INSERT INTO pessoa(nome, idade) VALUES('Gabriela Ferreira', 30)");
            //bancoDados.execSQL("INSERT INTO pessoa(nome, idade) VALUES('Maria Ferreira', 55)");
            //bancoDados.execSQL("INSERT INTO pessoa(nome, idade) VALUES('José Ferreira', 45)");

            bancoDados.execSQL("UPDATE pessoa SET idade = 44 WHERE id = 1"); // ATUALIZA
            bancoDados.execSQL("DELETE FROM pessoa WHERE id = 3"); // APAGA

            //recuperar dados
            String consulta = "SELECT * FROM pessoa WHERE idade > 30";
            Cursor cursor = bancoDados.rawQuery(consulta, null);

            int indiceID = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst(); // volta pro inicio da lista
            while(cursor != null){
                String id = cursor.getString(indiceID);
                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);
                Log.i("RESULTADO - nome: ",nome+" idade: "+idade+" ("+id+")");
                cursor.moveToNext(); // proximo
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
