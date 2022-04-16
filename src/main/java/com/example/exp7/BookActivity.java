package com.example.exp7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.exp7.adapter.BookAdapter;
import com.example.exp7.domain.Book;
import com.example.exp7.utils.MyDatabaseUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {
    private MyDatabaseUtil dbUtil;
    private List<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        dbUtil = new MyDatabaseUtil(this, "library.db", null, 2);

        SQLiteDatabase db = dbUtil.getWritableDatabase();
        Cursor book = db.rawQuery("select name,category_name,price from Book INNER JOIN Category on category_id = category_code", null);
        if (book.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = book.getString(book.getColumnIndex("name"));
                @SuppressLint("Range") String category = book.getString(book.getColumnIndex("category_name"));
                @SuppressLint("Range") double price = book.getDouble(book.getColumnIndex("price"));
                Book books = new Book(name, category, price);
                bookList.add(books);
            } while (book.moveToNext());
        }
        book.close();
        BookAdapter bookAdapter = new BookAdapter(BookActivity.this, R.layout.book_item, bookList);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(bookAdapter);

        Button AddBook = (Button) findViewById(R.id.AddBook);
        AddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });
    }
}