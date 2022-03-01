package com.example.library_app;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils
{
    private static final String ALL_BOOKS_KEY = "all books";
    private static final String READING_BOOKS_KEY = "reading books";
    private static final String READ_BOOKS_KEY = "read books";
    private static final String FAVORITE_BOOKS_KEY = "favorite books";
    private static final String WISHLIST_BOOKS_KEY = "wishlist books";

    private SharedPreferences sharedPreferences;

    private static Utils instance;

    private Utils(Context context)
    {
        sharedPreferences = context.getSharedPreferences("dataBase", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if(getAllBooks() == null)
        {
            loadAllBooks();
        }
        if(getReadBooks() == null)
        {
            editor.putString(READ_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(getReadingBooks() == null)
        {
            editor.putString(READING_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(getFavorites() == null)
        {
            editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(getWishlist() == null)
        {
            editor.putString(WISHLIST_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

    }


    private void loadAllBooks()
    {
        ArrayList <Book> books = new ArrayList<>();
        books.add(new Book(0, "Harry Potter And The Cursed Child", "Jack Thorne", "https://image.shutterstock.com/image-photo/kuala-lumpur-august-10-2016-600w-466404632.jpg",
                "2016", "British two-part play written by Jack Thorne based on an original story by J. K. Rowling, John Tiffany, and Thorne.", "3/5", "328"
                , "https://www.amazon.com/Harry-Potter-Cursed-Child-Production-ebook/dp/B01BMJWU4Q"));
        books.add(new Book(1, "Game Of Thrones", "George R.R. Martin", "https://image.shutterstock.com/image-photo/bucharest-romania-march-15-2015-600w-260748869.jpg",
                "1996", "Here is the first volume in George R. R. Martin’s magnificent cycle of novels that includes A Clash of Kings and A Storm of Swords. As a whole, this series comprises a genuine masterpiece of modern fantasy, bringing together the best the genre has to offer."
                , "5/5", "835", "https://www.amazon.com/Game-Thrones-Song-Fire-Book-ebook/dp/B000QCS8TW/ref=sr_1_1?keywords=game+of+thrones+book+1&qid=1644655633&s=digital-text&sprefix=game+of+thrones%2Cdigital-text%2C294&sr=1-1"));
        books.add(new Book(2, "A Mind For Numbers", "Barbara Oakley", "https://barbaraoakley.com/wp-content/uploads/2016/12/mind-for-numbers-front.jpg",
                "2014", "Whether you are a student struggling to fulfill a math or science requirement, or you are embarking on a career change that requires a new skill set, A Mind for Numbers offers the tools you need to get a better grasp of that intimidating material.",
                "4/5", "332", "https://www.amazon.com/Mind-Numbers-Science-Flunked-Algebra-ebook/dp/B00G3L19ZU/ref=sr_1_1?crid=YP1F0OTXA8W1&keywords=a+mind+for+numbers&qid=1644655669&s=digital-text&sprefix=a+mind+for+%2Cdigital-text%2C226&sr=1-1"));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit(); // can use apply() instead to have it run the background if data is large
    }


    public static Utils getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new Utils(context);
        }
        return instance;
    }


    public ArrayList<Book> getAllBooks()
    {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getReadingBooks()
    {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(READING_BOOKS_KEY, null), type);
        return books;
    }


    public ArrayList<Book> getReadBooks()
    {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(READ_BOOKS_KEY, null), type);
        return books;
    }


    public ArrayList<Book> getFavorites()
    {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS_KEY, null), type);
        return books;
    }


    public ArrayList<Book> getWishlist()
    {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WISHLIST_BOOKS_KEY, null), type);
        return books;
    }



    @Nullable
    public Book getBookById(int id)
    {
        ArrayList<Book> books = instance.getAllBooks();
        if(books != null)
        {
            for (Book book : books)
            {
                if (book.getID() == id)
                {
                    return book;
                }
            }
        }
        return null;
    }


    public boolean addToFavorites(Book book)
    {
        ArrayList<Book> books = getFavorites();
        if(books != null)
        {
            if(books.add(book))
            {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(FAVORITE_BOOKS_KEY);
                editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWishlist(Book book)
    {
        ArrayList<Book> books = getWishlist();
        if(books != null)
        {
            if(books.add(book))
            {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(WISHLIST_BOOKS_KEY);
                editor.putString(WISHLIST_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToReading(Book book)
    {
        ArrayList<Book> books = getReadingBooks();
        if(books != null)
        {
            if(books.add(book))
            {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(READING_BOOKS_KEY);
                editor.putString(READING_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToRead(Book book)
    {
        ArrayList<Book> books = getReadBooks();
        if(books != null)
        {
            if(books.add(book))
            {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(READ_BOOKS_KEY);
                editor.putString(READ_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromFavorites(Book book)
    {
        ArrayList<Book> books = getFavorites();
        if(books != null)
        {
            for (Book b : books)
            {
                if(b.getID() == book.getID())
                {
                    if(books.remove(b))
                    {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(FAVORITE_BOOKS_KEY);
                        editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromWishlist(Book book)
    {
        ArrayList<Book> books = getWishlist();
        if(books != null)
        {
            for (Book b : books)
            {
                if(b.getID() == book.getID())
                {
                    if(books.remove(b))
                    {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(WISHLIST_BOOKS_KEY);
                        editor.putString(WISHLIST_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromRead(Book book)
    {
        ArrayList<Book> books = getReadBooks();
        if(books != null)
        {
            for (Book b : books)
            {
                if(b.getID() == book.getID())
                {
                    if(books.remove(b))
                    {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(READ_BOOKS_KEY);
                        editor.putString(READ_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromReading(Book book)
    {
        ArrayList<Book> books = getReadingBooks();
        if(books != null)
        {
            for (Book b : books)
            {
                if(b.getID() == book.getID())
                {
                    if(books.remove(b))
                    {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(READING_BOOKS_KEY);
                        editor.putString(READING_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
