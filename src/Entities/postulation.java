/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author user16
 */
public class postulation {
    private int id ;
    private int offre_id ;
    private int postulation_user_id;
    private String motivation;
    private int price;
    private int duration ;
 public postulation( int offre_id, int postulation_user_id, String motivation, int price, int duration) {
        
        this.offre_id = offre_id;
        this.postulation_user_id = postulation_user_id;
        this.motivation = motivation;
        this.price = price;
        this.duration = duration;
    }
   // public int getPostulation_user_id() {
    //    return postulation_user_id;
    //}

    /*public void setPostulation_user_id(int postulation_user_id) {
        this.postulation_user_id = postulation_user_id;
    }*/

    public postulation(int id, String motivation, int price, int duration) {
        this.id = id;
        this.motivation = motivation;
        this.price = price;
        this.duration = duration;
    }

    public int getPostulation_user_id() {
        return postulation_user_id;
    }

    public void setPostulation_user_id(int postulation_user_id) {
        this.postulation_user_id = postulation_user_id;
    }

    

    public int getId() {
        return id;
    }

    public int getOffre_id() {
        return offre_id;
    }

    public String getMotivation() {
        return motivation;
    }

    public int getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOffre_id(int offre_id) {
        this.offre_id = offre_id;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public postulation() {
    }

    /*public postulation(int id, int offre_id, String motivation, int price, int duration) {
        this.id = id;
        this.offre_id = offre_id;
        this.motivation = motivation;
        this.price = price;
        this.duration = duration;
    }*/

    public postulation(int id, int offre_id, int postulation_user_id, String motivation, int price, int duration) {
        this.id = id;
        this.offre_id = offre_id;
        this.postulation_user_id = postulation_user_id;
        this.motivation = motivation;
        this.price = price;
        this.duration = duration;
    }
   

    @Override
    public String toString() {
        return "postulation{" + "id=" + id + ", offre_id=" + offre_id + ", postulation_user_id=" + postulation_user_id + ", motivation=" + motivation + ", price=" + price + ", duration=" + duration + '}';
    }
    
    

}
