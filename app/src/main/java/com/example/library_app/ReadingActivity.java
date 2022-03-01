package com.example.library_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReadingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView emptyText;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        initViews();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, "ReadingActivity");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(!Utils.getInstance(this).getReadingBooks().isEmpty())
        {
            adapter.setBooks(Utils.getInstance(this).getReadingBooks());
            emptyText.setVisibility(View.GONE);
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent intent = new Intent(ReadingActivity.this, ReadingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initViews()
    {
        recyclerView = findViewById(R.id.readingRecView);
        emptyText = findViewById(R.id.emptyTxt);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshReading);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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