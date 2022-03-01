package com.example.library_app;

public class Book
{
    private int ID;
    private String title, author, imageURL, year, description, rating, noOfPages, bookURL;
    private boolean isExtended;

    public Book(int id, String title, String author, String imageURL, String year, String description, String rating, String noOfPages, String bookURL)
    {
        setID(id);
        setTitle(title);
        setAuthor(author);
        setImageURL(imageURL);
        setYear(year);
        setDescription(description);
        setRating(rating);
        setNoOfPages(noOfPages);
        setBookURL(bookURL);
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getImageURL()
    {
        return imageURL;
    }

    public void setImageURL(String imageURL)
    {
        this.imageURL = imageURL;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getRating()
    {
        return rating;
    }

    public void setRating(String rating)
    {
        this.rating = rating;
    }

    public String getNoOfPages()
    {
        return noOfPages;
    }

    public void setNoOfPages(String noOfPages)
    {
        this.noOfPages = noOfPages;
    }

    public boolean isExtended()
    {
        return isExtended;
    }

    public void setExtended(boolean extended)
    {
        isExtended = extended;
    }

    public String getBookURL()
    {
        return bookURL;
    }

    public void setBookURL(String bookURL)
    {
        this.bookURL = bookURL;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", year='" + year + '\'' +
                ", description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                ", noOfPages='" + noOfPages + '\'' +
                ", bookURL='" + bookURL + '\'' +
                ", isExtended=" + isExtended +
                '}';
    }
}
