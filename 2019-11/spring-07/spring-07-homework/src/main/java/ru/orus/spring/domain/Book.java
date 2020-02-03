package ru.orus.spring.domain;

public class Book {
    private Long id;
    private String caption;

    public Book() {
    }

    public Book(String caption) {
        this.caption = caption;
    }

    public Book(Long id, String caption) {
        this.id = id;
        this.caption = caption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("id=").append(id);
        sb.append(", '").append(caption).append("'");
        return sb.toString();
    }
}
