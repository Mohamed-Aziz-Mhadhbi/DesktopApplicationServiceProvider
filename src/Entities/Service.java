/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Nour
 */
public class Service {
     private int prix ;
    private String title , description , image;
    private Date creat_at ;
    private int id;

    public Service() {
    }

    public Service(int id, int prix, String title, String description, String image, Date creat_at) {
        this.id = id;
        this.prix = prix;
        this.title = title;
        this.description = description;
        this.image = image;
        this.creat_at = creat_at;
    }
     public Service(int id, String title, String description, int prix, Date creat_at) {
        this.id = id;
        
        this.title = title;
        this.description = description;
        this.prix = prix;
        this.creat_at = creat_at;
    }
     

    @Override
    public String toString() {
        return "Service{" + "prix=" + prix + ", title=" + title + ", description=" + description + ", image=" + image + ", creat_at=" + creat_at + ", id=" + id + '}';
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreat_at() {
        return creat_at;
    }

    public void setCreat_at(Date creat_at) {
        this.creat_at = creat_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
