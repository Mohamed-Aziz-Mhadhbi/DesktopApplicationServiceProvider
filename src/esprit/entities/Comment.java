/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Comment {
    private int id ; 
    private String content ; 
    private Date date ;
    private int rating ;
    private int idP ; 

    public Comment() {
    }

    public Comment(int id, String content, int rating) {
        this.id = id;
        this.content = content;
        this.rating = rating;
    }

    public Comment(int id, String content, Date date, int rating, int idP) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.rating = rating;
        this.idP = idP;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", content=" + content + ", date=" + date + ", rating=" + rating + ", idP=" + idP + '}';
    }
    
    
    
}
