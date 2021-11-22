package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {
    private Retrofit retrofit;
    private Retro retro;
    private String newString;
    private DogsList dogsList;
    private String image;
    private ImageView imageView;
    private Button button;
    private TextView textView;
    private int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.textView);

        getBreed();
        button = findViewById(R.id.newImage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBreed();

            }
        });

    }

    private void getImage() {
        Call<DogsList> call = retro.getImages(newString);
        call.enqueue(new Callback<DogsList>() {
            @Override
            public void onResponse(Call<DogsList> call, Response<DogsList> response) {
                dogsList = response.body();
                Random ran = new Random();
                x = ran.nextInt(dogsList.getMessage().length) ;
                try{
                image = dogsList.getImages(x);
                Log.d("Main2", " " + dogsList.getMessage().length + "  " + x);
                putImage(image);}catch (Exception e){
                    textView.setText("" + e);
                }
            }

            @Override
            public void onFailure(Call<DogsList> call, Throwable t) {

            }
        });
    }

    private void putImage(String image) {
        imageView = (ImageView) findViewById(R.id.dogImage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(dogsList.getImages(x));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        Log.d("Main2", " "+ image);
        Glide.with(this).load(image).into(imageView);

    }

    private void getBreed() {
        Intent intent = getIntent();
        newString = intent.getStringExtra("IMAGE");
        Log.d("Main2", " "+ newString);
        textView.setText(newString);
        createRetro();
    }

    private void createRetro() {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://dog.ceo/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            retro =retrofit.create(Retro.class);
            getImage();
        }
}

