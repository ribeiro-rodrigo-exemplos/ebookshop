package br.com.rodrigo.ebookshop.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDAO {

    @Insert
    void insert(Book book);
    @Update
    void update(Book book);
    @Delete
    void delete(Book book);

    @Query("SELECT * FROM book_table WHERE category_id==:categoryId")
    LiveData<List<Book>> getBooks(int categoryId);
}
