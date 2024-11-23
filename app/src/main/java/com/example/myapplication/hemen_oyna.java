    package com.example.myapplication;

    import android.annotation.SuppressLint;
    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    import java.util.Arrays;
    import java.util.Collections;
    import java.util.List;
    import java.util.Random;
    import java.util.stream.Stream;

    public class hemen_oyna extends AppCompatActivity {

        TextView tv_info, tv_word;
        EditText ed_guess;
        ImageView ımage_check, ımage_new, kelimeal_balyoz, harfal_dinamit,hemenoyna_back;
        String currentWord;
        Random r;

        // Kategoriler
        String[] meslekler = {"Doctor", "Engineer", "Teacher", "Nurse", "Artist", "Scientist", "Chef", "Writer", "Lawyer", "Actor", "Designer", "Developer",
                "Painter", "Musician", "Architect", "Photographer", "Manager", "Police","Farmer", "Dentist"};
        String[] renkler = {
                "Red", "Blue", "Green", "Yellow", "Black", "White", "Purple", "Orange", "Pink", "Brown", "Gray", "Violet", "Indigo", "Beige", "Turquoise",
                "Maroon", "Cyan", "Gold", "Silver", "Lavender"
        };
        String[] yemekler = {
                "Pizza", "Burger", "Pasta", "Sushi", "Steak", "Salad", "Soup", "Chicken", "Tacos", "Noodles", "Burrito", "Kebab", "Ramen", "Pancake",
                "Waffles", "Tiramisu", "Sausage", "Lasagna", "Curry", "Stew"};
        String[] meyveler = {"Apple", "Banana",
                "Orange", "Mango", "Pineapple", "Strawberry", "Grapes", "Watermelon", "Peach", "Pear", "Cherry", "Blueberry", "Kiwi", "Plum", "Raspberry",
                "Lemon", "Apricot", "Papaya", "Blackberry", "Cantaloupe"};
        String[] hayvanlar = {"Lion", "Elephant", "Tiger", "Giraffe", "Zebra", "Kangaroo", "Panda", "Bear", "Wolf", "Cheetah", "Monkey", "Penguin", "Horse",
                "Rabbit", "Dog", "Cat", "Eagle", "Shark", "Dolphin", "Owl"};
        String[] ulkeler = {"Turkey", "Canada", "Brazil", "France", "Germany", "Italy", "Mexico", "Spain", "Russia", "China", "India", "Japan", "Australia",
                "Egypt", "Greece", "Poland", "Norway", "Sweden", "Argentina", "Chile"
        };
        String[] currentDictionary;  // Seçilen kategoriye ait kelimeler
        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_hemen_oyna);
                    tv_info = findViewById(R.id.tv_info);
                    tv_word = findViewById(R.id.tv_word);
                    ed_guess = findViewById(R.id.ed_guess);
            ed_guess.setSelection(ed_guess.getText().length());
                    ımage_check = findViewById(R.id.ımage_check);
                    ımage_new = findViewById(R.id.ımage_new);
                    kelimeal_balyoz = findViewById(R.id.ımage_balyoz);
                    harfal_dinamit = findViewById(R.id.ımage_dinamit);
                    hemenoyna_back = findViewById(R.id.hemenoyna_back);
                    r = new Random();
                    // Intent'ten gelen kategoriyi al
                    String category = getIntent().getStringExtra("category");
                    if (category != null) {
                        switch (category) {
                            case "meslekler":
                                currentDictionary = meslekler;
                                break;
                            case "renkler":
                                currentDictionary = renkler;
                                break;
                            case "yemekler":
                                currentDictionary= yemekler;
                                break;
                            case "meyveler":
                                currentDictionary=meyveler;
                                break;
                            case "hayvanlar":
                                currentDictionary = hayvanlar;
                                break;
                            case "ulkeler":
                                currentDictionary=ulkeler;
                                break;
                        }
                        newGame();
                    }
                    // Hemen Oyna: Random oyun başlatma
                    ımage_check.setOnClickListener(v -> {
                        if (ed_guess.getText().toString().equalsIgnoreCase(currentWord)) {
                            tv_info.setText("Mükemmel");
                            ımage_check.setEnabled(false);
                            ımage_new.setEnabled(true);
                        }
                        String boslukuyarı = ed_guess.getText().toString();
                        if (boslukuyarı.isEmpty()){
                            tv_info.setText("Lütfen Kelimeyi Yazınız");
                        }
                    });
                          ımage_new.setOnClickListener(v -> {
                        newGame();
                        tv_info.setText("");
                    });
                    kelimeal_balyoz.setOnClickListener(v -> {
                        String guess = ed_guess.getText().toString();
                        tv_info.setText(guess);
                        ed_guess.setText(currentWord);
                        ed_guess.setSelection(ed_guess.getText().length());
                        if (guess.equals(currentWord)) {
                            tv_info.setText("Daha Fazla Kelime Alamazsınız");
                            kelimeal_balyoz.setEnabled(false);
                        }
                    });
                    harfal_dinamit.setOnClickListener(v -> {
                        String guess = ed_guess.getText().toString();
                        try {
                            int correctPrefixLength = getPrefixLength(currentWord, guess);
                            ed_guess.setText(currentWord.substring(0, correctPrefixLength + 1));
                            ed_guess.setSelection(ed_guess.getText().length());
                            tv_info.setText("Bir Harf Yüklendi");
                            if (correctPrefixLength == currentWord.length() - 1) {
                                harfal_dinamit.setEnabled(false);
                            }
                        } catch (Exception e) {
                            tv_info.setText("Kelime Tamamen Yazdınız Başka Harf Alamazsınız");
                        }
                    });
                    hemenoyna_back.setOnClickListener(v -> {
                        Intent intent = new Intent(hemen_oyna.this,MainActivity.class);
                        startActivity(intent);

                    });
                }
                private int getPrefixLength(String word, String guess) {
                    int length = 0;
                    while (length < word.length() && length < guess.length() && word.charAt(length) == guess.charAt(length)) {
                        length++;
                    }
                    return length;
                }
                private String shuffleWord(String word) {
                    List<String> letters = Arrays.asList(word.split(""));
                    Collections.shuffle(letters);
                    StringBuilder shuffled = new StringBuilder();
                    for (String letter : letters) {
                        shuffled.append(letter);
                    }
                    return shuffled.toString();
                }
                private void newGame() {
                    currentWord = currentDictionary[r.nextInt(currentDictionary.length)];
                    tv_word.setText(shuffleWord(currentWord));
                    ed_guess.setText("");
                    ımage_new.setEnabled(false);
                    ımage_check.setEnabled(true);
                    kelimeal_balyoz.setEnabled(true);
                    harfal_dinamit.setEnabled(true);
                    // Rastgele bir kelime seç
                }
            }
