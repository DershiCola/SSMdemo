package com.dershi.dao;

import com.dershi.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestBookMapper {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookMapper mapper = context.getBean(BookMapper.class);
        List<Book> allBooks = mapper.getAllBooks();
        for (Book book : allBooks) {
            System.out.println(book);
        }
    }
}
