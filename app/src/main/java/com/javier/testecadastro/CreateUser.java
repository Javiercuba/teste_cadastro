package com.javier.testecadastro;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.javier.testecadastro.helper.UserDAO;
import com.javier.testecadastro.model.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CreateUser extends AppCompatActivity {
    private User userData;
    private ImageView imageView;
    private EditText name_text, date_text, cod_text;
    private User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

         name_text = findViewById(R.id.name);
         date_text = findViewById(R.id.date);
         cod_text = findViewById(R.id.cod);
         Button foto = findViewById(R.id.foto);
         Button save = findViewById(R.id.save);
         imageView = findViewById(R.id.image);

         foto.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
              Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
              startActivityForResult(intent,1);
             }
         });


        userData = (User) getIntent().getSerializableExtra("UsuarioSelecionado");

        if ( userData != null ){
            name_text.setText( userData.getName() );
            date_text.setText( userData.getDataNasc() );
            cod_text.setText(userData.getCode());
            imageView.setImageBitmap(StringToBitMap(userData.getMap()));
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserDAO userDAO = new UserDAO( getApplicationContext() );
                String nameUser = name_text.getText().toString();
                String dateUser = date_text.getText().toString();
                String codUser = cod_text.getText().toString();

                if( !nameUser.isEmpty()){

                    user.setName( nameUser );
                    user.setDataNasc(dateUser);
                    user.setCode(codUser);

                    if ( userDAO.salvar( user ) ){
                        finish();
                        Toast.makeText(getApplicationContext(),
                                "Sucesso ao salvar usuario!",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),
                                "Erro ao salvar usuario!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null){

            Uri SelectImageLocal = data.getData();
            try {
                Bitmap image = MediaStore.Images.Media.getBitmap(getContentResolver(), SelectImageLocal);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG,30,stream);
                imageView.setImageBitmap(image);


                Log.i("UserDAO", BitMapToString(image));
               // user.setmap(BitMapToString(image));


            }catch (IOException e){
                e.printStackTrace();
            }
        }


    }
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

}
