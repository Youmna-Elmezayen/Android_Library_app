package com.example.library_app;

import static com.example.library_app.BookActivity.BOOK_ID_KEY;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private ArrayList<Book> books = new ArrayList<>();
    Context context;
    private String callingActivity;

    public RecyclerViewAdapter(Context context, String callingActivity)
    {
        this.context = context;
        this.callingActivity = callingActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_books_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.title.setText(books.get(position).getTitle());
        holder.author.setText(books.get(position).getAuthor());
        holder.year.setText(books.get(position).getYear());

        holder.description.setText(books.get(position).getDescription());
        holder.rating.setText(books.get(position).getRating());
        holder.noOfPages.setText(books.get(position).getNoOfPages());


        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra(BOOK_ID_KEY, books.get(holder.getAbsoluteAdapterPosition()).getID());
                context.startActivity(intent);
            }
        });

                Glide.with(context).asBitmap().load(books.get(position).getImageURL()).into(holder.image);

        if(books.get(position).isExtended())
        {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.extended.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

            if(callingActivity.equals("AllBooksActivity"))
            {
                holder.deleteBtn.setVisibility(View.GONE);
            }
            else if(callingActivity.equals("FavoritesActivity"))
            {
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setMessage("Are you sure you want to delete this item?");
                        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utils.getInstance(context).removeFromFavorites(books.get(holder.getAbsoluteAdapterPosition())))
                                {
                                    Snackbar snackbar = Snackbar.make(view, "Deleted successfully. Please refresh", BaseTransientBottomBar.LENGTH_INDEFINITE)
                                            .setAction("Refresh", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent intent = new Intent(context, FavoritesActivity.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                    context.startActivity(intent);

                                                }
                                            });
                                    snackbar.show();
                                }
                            }
                        });
                        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alertDialogBuilder.create().show();
                    }
                });
            }
            else if(callingActivity.equals("WishlistActivity"))
            {
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setMessage("Are you sure you want to delete this item?");
                        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utils.getInstance(context).removeFromWishlist(books.get(holder.getAbsoluteAdapterPosition())))
                                {
                                    Snackbar snackbar = Snackbar.make(view, "Deleted successfully. Please refresh", BaseTransientBottomBar.LENGTH_INDEFINITE)
                                            .setAction("Refresh", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent intent = new Intent(context, WishlistActivity.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                    context.startActivity(intent);

                                                }
                                            });
                                    snackbar.show();
                                }
                            }
                        });
                        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alertDialogBuilder.create().show();
                    }
                });
            }
            else if(callingActivity.equals("ReadActivity"))
            {
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setMessage("Are you sure you want to delete this item?");
                        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utils.getInstance(context).removeFromRead(books.get(holder.getAbsoluteAdapterPosition())))
                                {
                                    Snackbar snackbar = Snackbar.make(view, "Deleted successfully. Please refresh", BaseTransientBottomBar.LENGTH_INDEFINITE)
                                            .setAction("Refresh", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent intent = new Intent(context, ReadActivity.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                    context.startActivity(intent);

                                                }
                                            });
                                    snackbar.show();
                                }
                            }
                        });
                        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alertDialogBuilder.create().show();
                    }
                });
            }
            else if(callingActivity.equals("ReadingActivity"))
            {
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setMessage("Are you sure you want to delete this item?");
                        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                    if(Utils.getInstance(context).removeFromReading(books.get(holder.getAbsoluteAdapterPosition())))
                                    {
                                        Snackbar snackbar = Snackbar.make(view, "Deleted successfully. Please refresh", BaseTransientBottomBar.LENGTH_INDEFINITE)
                                                .setAction("Refresh", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        Intent intent = new Intent(context, ReadingActivity.class);
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                        context.startActivity(intent);

                                                    }
                                                });
                                        snackbar.show();
                                    }
                            }
                        });
                        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alertDialogBuilder.create().show();
                    }
                });
            }
        }
        else
        {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.extended.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount()
    {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books)
    {
        this.books = books;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title, author, year, description, rating, noOfPages, deleteBtn;
        private ImageView image;
        private CardView parent;

        private ImageView upArrow, downArrow;
        private RelativeLayout extended;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.bookTitle);
            author = itemView.findViewById(R.id.bookAuthor);
            year = itemView.findViewById(R.id.yearOfPublish);
            image = itemView.findViewById(R.id.image);
            parent = itemView.findViewById(R.id.parent);

            extended = itemView.findViewById(R.id.extendedCardView);
            upArrow = itemView.findViewById(R.id.upArrow);
            downArrow = itemView.findViewById(R.id.downArrow);
            description = itemView.findViewById(R.id.bookDesc);
            rating = itemView.findViewById(R.id.bookRating);
            noOfPages = itemView.findViewById(R.id.bookNoOfPages);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAbsoluteAdapterPosition());
                    book.setExtended(!book.isExtended());
                    notifyItemChanged(getAbsoluteAdapterPosition());
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAbsoluteAdapterPosition());
                    book.setExtended(!book.isExtended());
                    notifyItemChanged(getAbsoluteAdapterPosition());
                }
            });
        }
    }
}
