package com.dershi.service;

import com.dershi.pojo.Book;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface BookService {
    // 查询书
    PageInfo<Book> queryAllBooks(int pageNo, int pageSize);
    Book queryOneBook(int id);

    // 添加一本书的记录
    int addOneBook(Map<String, Object> map);

    // 删除一本书的记录
    int deleteOneBook(int bookId);

    // 更新一本书的内容,Map里需要设置要更新的内容
    int updateOneBook(int bookId, Map<String, Object> map);
}
