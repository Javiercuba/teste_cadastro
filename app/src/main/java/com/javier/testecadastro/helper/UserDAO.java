package com.javier.testecadastro.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.javier.testecadastro.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public UserDAO(Context context) {
        DbHelper db = new DbHelper( context );
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }
    @Override
    public boolean salvar(User user) {

        ContentValues cv = new ContentValues();
        cv.put("nome", user.getName() );
        cv.put("date",user.getDataNasc() );
        try {
            write.insert(DbHelper.TABLE_USERS, null, cv );
            Log.i("INFO", "Usuario salvo com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar Usuario " + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(User user) {

        ContentValues cv = new ContentValues();
        cv.put("nome", user.getName() );
        cv.put("date", user.getDataNasc() );
        try {
            String[] args = {user.getId().toString()};
            write.update(DbHelper.TABLE_USERS, cv, "id=?", args );
            Log.i("INFO", "Usuario atualizado com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao atualizada usuario " + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(User user) {

        try {
            String[] args = { user.getId().toString() };
            write.delete(DbHelper.TABLE_USERS, "id=?", args );
            Log.i("INFO", "Usuario removido com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao remover Usuario " + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public List<User> listar() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABLE_USERS + " ;";
        Cursor c = read.rawQuery(sql, null);

        while (c.moveToNext()) {

            User user = new User();

            Long id = c.getLong(c.getColumnIndex("id"));
            String name = c.getString(c.getColumnIndex("nome"));
            String userDate = c.getString(c.getColumnIndex("date"));

            user.setId(id);
            user.setName(name);
            user.setDataNasc(userDate);
            users.add(user);
        }
        return users;
    }

}
