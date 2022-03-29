package com.hoang.travel.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments", schema = "tourism_products")
public class CommentEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;
    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Timestamp createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userComment;
    @ManyToOne
    @JoinColumn(name = "idTour", referencedColumnName = "id")
    private TourEntity idTour;
    @ManyToOne
    @JoinColumn(name = "idResort", referencedColumnName = "id")
    private ResortEntity idResort;
    @ManyToOne
    @JoinColumn(name = "idSpecial", referencedColumnName = "id")
    private SpecialEntity idSpecial;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public UserEntity getUserComment() {
        return userComment;
    }

    public void setUserComment(UserEntity userComment) {
        this.userComment = userComment;
    }

    public TourEntity getIdTour() {
        return idTour;
    }

    public void setIdTour(TourEntity idTour) {
        this.idTour = idTour;
    }

    public ResortEntity getIdResort() {
        return idResort;
    }

    public void setIdResort(ResortEntity idResort) {
        this.idResort = idResort;
    }

    public SpecialEntity getIdSpecial() {
        return idSpecial;
    }

    public void setIdSpecial(SpecialEntity idSpecial) {
        this.idSpecial = idSpecial;
    }
}
