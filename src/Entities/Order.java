/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.sql.Date;

/**
 *
 * @author Nour
 */
public class Order {
    private int id,idU,idS;
    private String message;
    private Date D;
    int st;

    public Order(int id, int idU, int idS, String message, Date D) {
        this.id = id;
        this.idU = idU;
        this.idS = idS;
        this.message = message;
        this.D = D;
    }

    
     public Order(int id, int idS, String message, Date D,int st) {
        this.id = id;
        this.idS = idS;
        this.message = message;
        this.D = D;
        this.st = st;
    }

    public Date getD() {
        return D;
    }

    public void setD(Date D) {
        this.D = D;
    }

    public int getSt() {
        return st;
    }

    public void setSt(int st) {
        this.st = st;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public int getIdS() {
        return idS;
    }

    public void setIdS(int idS) {
        this.idS = idS;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
