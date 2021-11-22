package com.example.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private  DogsList dogs;
    private Retrofit retrofit;
    private ArrayList<String> breedList = new ArrayList<>();
    private Retro retro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createRetro();
        getBreedList();



    }

    private void createRecycler() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        ExampleAdapter mAdapter = new ExampleAdapter(breedList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("IMAGE", breedList.get(position));
                startActivity(intent);
            }

            @Override
            public void onItemDelete(int position) {

            }
        });

    }

    private void getBreedList() {
        Call<DogsList> call = retro.getList();
        call.enqueue(new Callback<DogsList>() {
            @Override
            public void onResponse(Call<DogsList> call, Response<DogsList> response) {
                dogs = response.body();
                for (String s : dogs.getMessage()){
                    breedList.add(s);
                    Log.d("Main", " "+breedList);
                    createRecycler();
                }
                Log.d("Main", " "+dogs);
            }

            @Override
            public void onFailure(Call<DogsList> call, Throwable t) {
            }
        });

    }

    private void createRetro() {
         retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retro =retrofit.create(Retro.class);
    }
}