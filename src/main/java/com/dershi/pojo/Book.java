package com.dershi.pojo;

public class Book {
    private Integer bookId;
    private String bookName;
    private String author;
    private String press;
    private String imgPath;
    private String bookImpression;

    public Book() {
    }

    public Book(String bookName, String author, String press, String imgPath, String bookImpression) {
        this.bookName = bookName;
        this.author = author;
        this.press = press;
        this.imgPath = imgPath;
        this.bookImpression = bookImpression;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getBookImpression() {
        return bookImpression;
    }

    public void setBookImpression(String bookImpression) {
        this.bookImpression = bookImpression;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", bookImpression='" + bookImpression + '\'' +
                '}';
    }
}
