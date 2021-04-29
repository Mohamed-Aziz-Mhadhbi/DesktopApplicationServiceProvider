/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Mohamed Aziz Mhadhbi
 */
public class Post {

    private int id;
    private String title;
    private String description;
    private int views;
    private int noc;
    private String date;
    private int idF;
    private int usr_id;

    public Post() {
    }

    public Post(String title, String description, int idF, int usr_id) {
        this.title = title;
        this.description = description; 
        this.idF = idF;
        this.usr_id = usr_id;
    }

    public Post(int id, String title, String description, int noc, int views) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.noc = noc;
        this.views = views;
    }

    public Post(int usr_id, int id, String title, String description, int views, int noc, int idF) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.views = views;
        this.noc = noc;
        this.idF = idF;
        this.usr_id = usr_id;
    }
    
    public Post(int id, String title, String description, int views, int noc, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.views = views;
        this.noc = noc;
        this.date = date;
    }

    

   

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getNoc() {
        return noc;
    }

    public void setNoc(int noc) {
        this.noc = noc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", title=" + title + ", description=" + description + ", views=" + views + ", noc=" + noc + ", date=" + date + ", idF=" + idF + ", usr_id=" + usr_id + '}';
    }

}
