package br.com.rodrigo.ebookshop;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;

import br.com.rodrigo.ebookshop.model.Book;

public class BooksDiffCallback extends DiffUtil.Callback {

    ArrayList<Book> oldBooksList;
    ArrayList<Book> newBookList;

    public BooksDiffCallback(ArrayList<Book> oldBooksList, ArrayList<Book> newBookList) {
        this.oldBooksList = oldBooksList;
        this.newBookList = newBookList;
    }

    @Override
    public int getOldListSize() {
        return  oldBooksList != null ? oldBooksList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newBookList != null ? newBookList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldBookPosition, int newBookPosition) {
        return oldBooksList.get(oldBookPosition).getBookId() == newBookList.get(newBookPosition).getBookId();
    }

    @Override
    public boolean areContentsTheSame(int oldBookPosition, int newBookPosition) {
        return oldBooksList.get(oldBookPosition).equals(newBookList.get(newBookPosition).getBookId());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
