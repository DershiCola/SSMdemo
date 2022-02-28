package com.dershi.dao;

import com.dershi.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookMapper {
    List<Book> getAllBooks();
    Book getBookById(@Param("bookId") int id);
    int addBook(Map<String, Object> map);
    int deleteBookById(@Param("bookId") int id);
    int updateBookById(Map<String, Object> map);
}
