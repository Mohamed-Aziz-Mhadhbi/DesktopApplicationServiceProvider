/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author user16
 */
public class Offre {
    // private int user_id;

    private int id;
    private String Title;
    private String Description;
    private int DomainOffre;
    private Date CreatAt;
    private Double rating;

    public Offre(int id, Double rating) {
        this.id = id;
        this.rating = rating;
    }

    public Offre(int id,String Title, String Description, int DomainOffre) {
        this.id = id;
        this.Title = Title;
        this.Description = Description;
        this.DomainOffre = DomainOffre;
    }

    /* public Offre(int i,int user_id, String Title, String Description, int DomainOffre) {
        this.id = id;
         this.user_id = user_id;
        this.Title = Title;
        this.Description = Description;
        this.DomainOffre = DomainOffre;
       
    }

    public Offre(int i, String Title, String Description, int DomainOffre) {
       this.id = id;
        this.Title = Title;
        this.Description = Description;
        this.DomainOffre = DomainOffre;
    }*/
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    /*  public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }*/
    public void setCreatAt(Date CreatAt) {
        this.CreatAt = CreatAt;
    }

    public Date getCreatAt() {
        return CreatAt;
    }

    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getDomainOffre() {
        return DomainOffre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setDomainOffre(int DomainOffre) {
        this.DomainOffre = DomainOffre;
    }

    public Offre() {
    }

    public Offre(int id, String Title, String Description, int DomainOffre, Date CreatAt) {
        this.id = id;
        this.Title = Title;
        this.Description = Description;
        this.DomainOffre = DomainOffre;
        this.CreatAt = CreatAt;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", Title=" + Title + ", Description=" + Description + ", DomainOffre=" + DomainOffre + ", CreatAt=" + CreatAt + ", rating=" + rating + '}';
    }

}
