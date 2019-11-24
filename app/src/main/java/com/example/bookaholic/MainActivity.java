package com.example.bookaholic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.background_pic);
        //locate button
       button =(Button) findViewById(R.id.button);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openSearch();

           }
       });



        //Glide.with(this).load(R.drawable.back1).into(imageView);
    }
    public void openSearch(){
        Intent intent=new Intent(this,search.class);
        startActivity(intent);
    }
    public int getImage(String imageName) {
        return this.getResources().getIdentifier(imageName, "drawable", this.getPackageName());
    }
}
