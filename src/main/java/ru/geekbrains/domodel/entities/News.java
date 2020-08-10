package ru.geekbrains.domodel.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность новости для связи ее с БД
 */
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "short_text", nullable = false)
    private String shortText;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "visible", nullable = false)
    private boolean visible;

    @Column(name = "picture")
    private String picture;

    @Column(name = "type")
    private String type;

    // Ссылка на пользователя - автора новости
    @OneToOne
    @JoinColumn(name = "author", nullable = false)
    private User author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public News() {
    }

    public News(Date creationDate, String shortText, String text, boolean visible, String picture, User author) {
        this.creationDate = creationDate;
        this.shortText = shortText;
        this.text = text;
        this.visible = visible;
        this.picture = picture;
        this.author = author;
    }

    @Override
    public String toString() {
        return String.format("News: [id = %d, short_text = %s, text = %s]", id, shortText, text);
    }
}
