package com.example.library_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity
{
    private RecyclerView allBooksRecView;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        initViews();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, "AllBooksActivity");
        adapter.setBooks(Utils.getInstance(this).getAllBooks());
        allBooksRecView.setAdapter(adapter);
        allBooksRecView.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(AllBooksActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initViews()
    {
        allBooksRecView = findViewById(R.id.allBooksRecView);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}