package ru.geekbrains.domodel.news.entities;

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

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "short_text")
    private String shortText;

    @Column(name = "text")
    private String text;

    @Column(name = "author_id")
    private String authorId;

    @Column(name = "active")
    private boolean active;

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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public News() {
    }

    public News(Long id, Date creationDate, String shortText, String text, String authorId, boolean active) {
        this.id = id;
        this.creationDate = creationDate;
        this.shortText = shortText;
        this.text = text;
        this.authorId = authorId;
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("News: [id = %d, short_text = %s, author_id = %d]", id, shortText, authorId);
    }

}