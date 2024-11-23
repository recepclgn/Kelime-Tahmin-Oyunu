package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView hemenoyna, bolumler;

    // Kategoriler
    String[] meslekler = {"Doctor", "Engineer", "Teacher", "Nurse", "Artist", "Scientist", "Chef", "Writer", "Lawyer", "Actor", "Designer", "Developer",
            "Painter", "Musician", "Architect", "Photographer", "Manager", "Police", "Farmer", "Dentist"};
    String[] renkler = {"Red", "Blue", "Green", "Yellow", "Black", "White", "Purple", "Orange", "Pink", "Brown", "Gray", "Violet", "Indigo", "Beige", "Turquoise",
            "Maroon", "Cyan", "Gold", "Silver", "Lavender"};
    String[] yemekler = {"Pizza", "Burger", "Pasta", "Sushi", "Steak", "Salad", "Soup", "Chicken", "Tacos", "Noodles", "Burrito", "Kebab", "Ramen", "Pancake",
            "Waffles", "Tiramisu", "Sausage", "Lasagna", "Curry", "Stew"};
    String[] meyveler = {"Apple", "Banana", "Orange", "Mango", "Pineapple", "Strawberry", "Grapes", "Watermelon", "Peach", "Pear", "Cherry", "Blueberry", "Kiwi", "Plum", "Raspberry",
            "Lemon", "Apricot", "Papaya", "Blackberry", "Cantaloupe"};
    String[] hayvanlar = {"Lion", "Elephant", "Tiger", "Giraffe", "Zebra", "Kangaroo", "Panda", "Bear", "Wolf", "Cheetah", "Monkey", "Penguin", "Horse",
            "Rabbit", "Dog", "Cat", "Eagle", "Shark", "Dolphin", "Owl"};
    String[] ulkeler = {"Turkey", "Canada", "Brazil", "France", "Germany", "Italy", "Mexico", "Spain", "Russia", "China", "India", "Japan", "Australia",
            "Egypt", "Greece", "Poland", "Norway", "Sweden", "Argentina", "Chile"};
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hemenoyna = findViewById(R.id.hemenoyna);
        bolumler = findViewById(R.id.bolumler);

        hemenoyna.setOnClickListener(v -> {
            String category = getRandomCategory();//R Bölümlerin hepsini cagegory at
            String currentWord = getRandomWord(category);
            Intent intent = new Intent(MainActivity.this, hemen_oyna.class);
            intent.putExtra("category", category); // Seçilen kategoriyi gönder
            intent.putExtra("currentWord", currentWord); // Seçilen kelimeyi gönder
            startActivity(intent);
        });
        bolumler.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, com.example.myapplication.bolumler.class);
            intent.putExtra("mode", "category"); // Kategori seçimi modunu başlat
            startActivity(intent);
        });
    }
    private String getRandomCategory() {
        String[] categories = {"meslekler", "renkler", "yemekler", "meyveler", "hayvanlar", "ulkeler"};
        return categories[random.nextInt(categories.length)];
    }
    // Kategoriye göre rastgele kelime seçme
    private String getRandomWord(String category) {
        switch (category) {
            case "meslekler":
                return meslekler[random.nextInt(meslekler.length)];
            case "renkler":
                return renkler[random.nextInt(renkler.length)];
            case "yemekler":
                return yemekler[random.nextInt(yemekler.length)];
            case "meyveler":
                return meyveler[random.nextInt(meyveler.length)];
            case "hayvanlar":
                return hayvanlar[random.nextInt(hayvanlar.length)];
            case "ulkeler":
                return ulkeler[random.nextInt(ulkeler.length)];
            default:
                return "";
        }
    }
}
