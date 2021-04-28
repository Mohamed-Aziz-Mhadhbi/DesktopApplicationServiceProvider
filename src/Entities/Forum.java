/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mohamed Aziz Mhadhbi
 */
public class Forum {

    private int id;
    private String title, description;
    private int id_user;

    private List<Post> posts;

    public Forum() {
        posts = new ArrayList<>();
    }

    public Forum(int id_user,int id, String title, String description) {
        posts = new ArrayList<>();
        this.id = id;
        this.title = title;
        this.description = description;
        this.id_user = id_user;
    }

    public Forum(String title, String description) {
        posts = new ArrayList<>();
        this.title = title;
        this.description = description;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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
        return "Forum{" + "id=" + id + ", title=" + title + ", description=" + description + ", id_user=" + id_user + '}';
    }

   
}
