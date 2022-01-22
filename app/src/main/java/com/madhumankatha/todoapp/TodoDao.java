package com.madhumankatha.todoapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    void insert(Todo todo);

    @Query("SELECT * from todo")
    List<Todo> getAll();

    @Update
    void update(Todo todo);

    @Delete
    void delete(Todo todo);

}
