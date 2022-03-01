package com.example.library_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookID";

    private ImageView bookImage;
    private TextView title, author, year, desc, rating, noOfPages, bookURL;
    private Button favoritesBtn, wishlistBtn, readBtn, readingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       Intent intent = getIntent();
       if(intent != null)
       {
           int bookID = intent.getIntExtra(BOOK_ID_KEY, -1);
           if(bookID != -1)
           {
                Book inBook = Utils.getInstance(this).getBookById(bookID);
                if(inBook != null)
                {
                    setBookData(inBook);
                    handleFavoritesBtn(inBook);
                    handleWishlistBtn(inBook);
                    handleReadBtn(inBook);
                    handleReadingBtn(inBook);
                }
           }
       }
    }

    private void handleFavoritesBtn(Book book)
    {
        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavorites();
        boolean existsInFavorites = false;

        for(Book b: favoriteBooks)
        {
            if(b.getID() == book.getID())
            {
                existsInFavorites = true;
            }
        }
        if(existsInFavorites)
        {
            favoritesBtn.setEnabled(false);
        }
        else
        {
            favoritesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToFavorites(book))
                    {
                        Toast.makeText(BookActivity.this, "Added to favorites", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavoritesActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(BookActivity.this, "Could not add, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWishlistBtn(Book book)
    {
        ArrayList<Book> wishlistBooks = Utils.getInstance(this).getWishlist();
        boolean existsInWishlist= false;

        for(Book b: wishlistBooks)
        {
            if(b.getID() == book.getID())
            {
                existsInWishlist = true;
            }
        }
        if(existsInWishlist)
        {
            wishlistBtn.setEnabled(false);
        }
        else
        {
            wishlistBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToWishlist(book))
                    {
                        Toast.makeText(BookActivity.this, "Added to wishlist", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WishlistActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(BookActivity.this, "Could not add, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleReadBtn(Book book)
    {
        ArrayList<Book> readBooks = Utils.getInstance(this).getReadBooks();
        boolean existsInRead = false;

        for(Book b: readBooks)
        {
            if(b.getID() == book.getID())
            {
                existsInRead = true;
            }
        }
        if(existsInRead)
        {
            readBtn.setEnabled(false);
        }
        else
        {
            readBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToRead(book))
                    {
                        Toast.makeText(BookActivity.this, "Added to read books", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, ReadActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(BookActivity.this, "Could not add, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleReadingBtn(Book book)
    {
        ArrayList<Book> readingBooks = Utils.getInstance(this).getReadingBooks();
        boolean existsInReading = false;

        for(Book b: readingBooks)
        {
            if(b.getID() == book.getID())
            {
                existsInReading = true;
            }
        }
        if(existsInReading)
        {
            readingBtn.setEnabled(false);
        }
        else
        {
            readingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToReading(book))
                    {
                        Toast.makeText(BookActivity.this, "Added to reading list", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, ReadingActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(BookActivity.this, "Could not add, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setBookData(Book book)
    {
        System.out.println(book);
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        year.setText(book.getYear());
        desc.setText(book.getDescription());
        rating.setText(book.getRating());
        noOfPages.setText(book.getNoOfPages());
        bookURL.setText(book.getBookURL());
        bookURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookActivity.this, WebViewActivity.class);
                intent.putExtra("url", book.getBookURL());
                startActivity(intent);
            }
        });
        Glide.with(this).asBitmap().load(book.getImageURL()).into(bookImage);
    }

    private void initViews()
    {
        bookImage = findViewById(R.id.imageView);
        title = findViewById(R.id.titleText);
        author = findViewById(R.id.authorText);
        year = findViewById(R.id.yearText);
        desc = findViewById(R.id.descText);
        rating = findViewById(R.id.ratingText);
        noOfPages = findViewById(R.id.pagesText);
        favoritesBtn = findViewById(R.id.favoritesBookBtn);
        wishlistBtn = findViewById(R.id.wishlistBookBtn);
        readBtn = findViewById(R.id.readBookBtn);
        readingBtn = findViewById(R.id.readingBookBtn);
        bookURL = findViewById(R.id.bookLink);
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