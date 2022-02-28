package com.dershi.controller;

import com.dershi.pojo.Book;
import com.dershi.service.BookServiceImpl;
import com.dershi.utils.DefalutNum;
import com.dershi.utils.IdUtils;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookServiceImpl bookService;

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/page/{pageNo}")
    protected ModelAndView bookList(@PathVariable int pageNo, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String loginInfo = (String) session.getAttribute("loginInfo");
        if (loginInfo != null) {
            PageInfo<Book> pageInfo = bookService.queryAllBooks(pageNo, DefalutNum.DEFALUT_SIZE.getNum());
            if (pageNo > pageInfo.getPages()) {
                mv.setViewName("forward:/page/" + pageInfo.getPages());
                return mv;
            }
            mv.addObject("pageInfo", pageInfo);
            List<Book> books = pageInfo.getList();
            mv.addObject("books", new Gson().toJson(books));
            mv.setViewName("book/show");
        } else {
            mv.setViewName("user/login");
        }
        return mv;
    }

    @RequestMapping("/edit")
    protected ModelAndView bookEdit(String pageNo) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageNo", pageNo);
        mv.setViewName("book/edit");
        return mv;
    }

    @RequestMapping("/edit/{id}")
    protected ModelAndView bookEdit(@PathVariable int id, String pageNo) {
        ModelAndView mv = new ModelAndView();
        if (id > 0) {
            Book book = bookService.queryOneBook(id);
            if (book != null) {
                mv.addObject("bookId", book.getBookId());
                mv.addObject("pageNo", pageNo);
                mv.addObject("bookName", book.getBookName());
                mv.addObject("author", book.getAuthor());
                mv.addObject("press", book.getPress());
                mv.addObject("imgPath", book.getImgPath());
                mv.addObject("bookImpression", book.getBookImpression());
            }
        }
        mv.setViewName("book/edit");
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    protected String addBook(String pageNo, String bookName, String author, String press, MultipartFile bookImgFile, String bookImpression, HttpSession session) throws IOException {
        Map<String, Object> map = new HashMap<>();
        int add = 0;
        if (bookName != null) {
            if (bookImgFile != null)
                if (getImgPath(bookImgFile, session) != null) {
                    map = getImgPath(bookImgFile, session);
                    map.put("imgPath", map.get("filePath"));
                }
            map.put("bookName", bookName);
            map.put("author", author);
            map.put("press", press);
            map.put("bookImpression", bookImpression);
            add = bookService.addOneBook(map);
        }
        if (add > 0) {
            if (map.get("realPath") != null)
                bookImgFile.transferTo(new File((String) map.get("realPath")));
            return "redirect:/book/page/" + pageNo;
        } else {
            return "forward:/book/edit";
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    protected String deleteBook(@PathVariable int id, int pageNo, HttpSession session) {
        int delete = bookService.deleteOneBook(id);
        session.setAttribute("deleteMsg", delete);
        return "redirect:/book/page/" + pageNo;
    }

    @RequestMapping(value = "/update/{id}")
    protected String updateBook(@PathVariable int id, String bookName, String author, String press, MultipartFile bookImgFile, String bookImpression, String pageNo, HttpSession session) throws IOException {
        Map<String, Object> map = new HashMap<>();
        int update = 0;
        if (bookName != null) {
            if (bookImgFile != null)
                if (getImgPath(bookImgFile, session) != null) {
                    map = getImgPath(bookImgFile, session);
                    map.put("imgPath", map.get("filePath"));
                }
            map.put("bookName", bookName);
            map.put("author", author);
            map.put("press", press);
            map.put("bookImpression", bookImpression);
            update = bookService.updateOneBook(id, map);
        }
        if (update > 0) {
            if (map.get("realPath") != null)
                bookImgFile.transferTo(new File((String) map.get("realPath")));
            return "redirect:/book/page/" + pageNo;
        } else {
            return "forward:/book/edit/" + id;
        }
    }

    protected Map<String, Object> getImgPath(MultipartFile bookImgFile, HttpSession session) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String oldName = bookImgFile.getOriginalFilename();
        String suffixName;
        if (!"".equals(oldName) && oldName != null)
            suffixName = oldName.substring(oldName.lastIndexOf("."));
        else
            return null;
        String fileName = IdUtils.generateId() + suffixName;
        String path = session.getServletContext().getRealPath("static/img/book");
        File book = new File(path);
        if (!book.exists())
            book.mkdir();
        map.put("realPath", path + File.separator + fileName);
        map.put("filePath", "/static/img/book/" + fileName);
        return map;
    }
}
