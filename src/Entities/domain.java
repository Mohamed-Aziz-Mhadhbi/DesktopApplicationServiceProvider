/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author marwe
 */
public class domain {
    int id ;
    String title;
    String color;

    public domain(int id, String title, String color) {
        this.id = id;
        this.title = title;
        this.color = color;
    }

    public domain() {
    }

   

    @Override
    public String toString() {
        return "domain{" + "id=" + id + ", title=" + title + ", color=" + color + '}';
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
}
