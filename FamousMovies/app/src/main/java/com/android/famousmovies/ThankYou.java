package com.android.famousmovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;

/**
 * Created by Hakimi on 4/3/2020.
 */
public class ThankYou extends AppCompatActivity {

    ListView simpleList;
    String libraryList[] = {"Firebase", "RecyclerView", "Retrofit", "Cardview", "Picasso", "Butterknife", "FloatingActionButton", ""};
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thankyou);

        simpleList = findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.listview, R.id.textView, libraryList);
        simpleList.setAdapter(arrayAdapter);
        button = findViewById(R.id.button);
    }

    public void signOut(View view) {
        AuthUI.getInstance().signOut(this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
