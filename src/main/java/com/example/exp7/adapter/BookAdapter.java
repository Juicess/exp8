package com.example.exp7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.exp7.R;
import com.example.exp7.domain.Book;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    private int resourceId;

    public BookAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Book> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Book book = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView bookName = (TextView) view.findViewById(R.id.book_name);
        TextView bookCategory = (TextView) view.findViewById(R.id.book_category);
        TextView bookPrice = (TextView) view.findViewById(R.id.book_price);
        bookName.setText(book.getName() + "");
        bookCategory.setText(book.getCategory());
        bookPrice.setText(book.getPrice() + "");
        return view;
    }
}
