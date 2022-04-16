package com.example.exp7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import com.example.exp7.utils.MyDatabaseUtil;

public class AddBookActivity extends AppCompatActivity {
    private MyDatabaseUtil dbUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        dbUtil = new MyDatabaseUtil(this, "library.db", null, 2);

        Button addBook = (Button) findViewById(R.id.add_data);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbUtil.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("price", 16.96);
                values.put("pages", 454);
                values.put("category_id", 1);
                db.insert("Book", null, values);
                values.clear();
                values.put("category_name", "经济类");
                values.put("category_code", 1);
                db.insert("Category", null, values);
                values.clear();
            }
        });
    }
}