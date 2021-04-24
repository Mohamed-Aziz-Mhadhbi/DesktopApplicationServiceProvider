/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Forum {
   private int id ;
   private String title;
   private String description ; 
   
   private List<Post> posts;

    public Forum() {
        posts = new ArrayList<>();
    }

    public Forum(int id, String title, String description) {
         posts = new ArrayList<>();
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Forum(String title, String description) {
         posts = new ArrayList<>();
        this.title = title;
        this.description = description;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Forum{" + "id=" + id + ", title=" + title + ", description=" + description + ", posts=" + posts + '}';
    }

   
   
   
}
