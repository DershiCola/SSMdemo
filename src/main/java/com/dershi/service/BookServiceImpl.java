package com.dershi.service;

import com.dershi.dao.BookMapper;
import com.dershi.pojo.Book;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    private final BookMapper mapper;

    @Autowired
    public BookServiceImpl(BookMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public PageInfo<Book> queryAllBooks(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Book> books = mapper.getAllBooks();
        return new PageInfo<>(books);
    }

    @Override
    public Book queryOneBook(int id) {
        return mapper.getBookById(id);
    }

    @Override
    public int addOneBook(Map<String, Object> map) {
        return mapper.addBook(map);
    }

    @Override
    public int deleteOneBook(int bookId) {
        return mapper.deleteBookById(bookId);
    }

    @Override
    public int updateOneBook(int bookId, Map<String, Object> map) {
        map.put("bookId", bookId);
        return mapper.updateBookById(map);
    }
}
