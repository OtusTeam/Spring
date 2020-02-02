package ru.orus.spring.domain;

public class Genre {
    private Long id;
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("id=").append(id);
        sb.append(", '").append(name).append('\'');
        return sb.toString();
    }
}
