package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class bolumler extends AppCompatActivity {

    ImageView bolum_back,meslekler,renkler,yemekler,meyveler,hayvanlar,ulkeler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bolumler);
                bolum_back = findViewById(R.id.bolum_back);

                meslekler = findViewById(R.id.meslek);
                renkler = findViewById(R.id.renk);
                yemekler = findViewById(R.id.yemek);
                meyveler = findViewById(R.id.meyve);
                hayvanlar = findViewById(R.id.hayvan);
                ulkeler = findViewById(R.id.ulke);

                // Meslekler kategorisini seçme
                meslekler.setOnClickListener(v -> {
                    startGame("meslekler");
                });

                // Renkler kategorisini seçme
                renkler.setOnClickListener(v -> {
                    startGame("renkler");
                });

                yemekler.setOnClickListener(v ->{
                   startGame("yemekler");
                 });

                meyveler.setOnClickListener(v -> {
                    startGame("meyveler");
                });

                // Hayvanlar kategorisini seçme
                hayvanlar.setOnClickListener(v -> {
                    startGame("hayvanlar");
                });
                ulkeler.setOnClickListener( v -> {
                    startGame("ulkeler");
                });

                bolum_back.setOnClickListener(v->{
                    Intent intent = new Intent(bolumler.this,MainActivity.class);
                    startActivity(intent);

                });
            }
            private void startGame(String category) {
                Intent intent = new Intent(bolumler.this, hemen_oyna.class);
                intent.putExtra("category", category); // Seçilen kategoriyi intent ile gönderiyoruz
                startActivity(intent);
            }
        }

