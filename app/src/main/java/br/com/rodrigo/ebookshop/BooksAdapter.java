package br.com.rodrigo.ebookshop;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.rodrigo.ebookshop.databinding.BookListItemBinding;
import br.com.rodrigo.ebookshop.model.Book;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder>{

    private OnItemClickListener listener;
    private ArrayList<Book> books = new ArrayList<>();

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        BookListItemBinding bookListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.book_list_item,
                parent,
                false
        );

        return new BookViewHolder(bookListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bookListItemBinding.setBook(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> newBooks) {
        //this.books = books;
        //notifyDataSetChanged();

        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(
                new BooksDiffCallback(books,newBooks),
                false
        );

        books = newBooks;
        result.dispatchUpdatesTo(BooksAdapter.this);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class BookViewHolder extends RecyclerView.ViewHolder{

        private BookListItemBinding bookListItemBinding;

        public BookViewHolder(@NonNull BookListItemBinding bookListItemBinding) {
            super(bookListItemBinding.getRoot());
            this.bookListItemBinding = bookListItemBinding;

            this.bookListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();

                    if(listener != null && clickedPosition != RecyclerView.NO_POSITION){
                        listener.onItemClick(books.get(clickedPosition));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Book book);
    }
}