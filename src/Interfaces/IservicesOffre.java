/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Offre;
import java.util.List;

/**
 *
 * @author user16
 */
public interface IservicesOffre {
   public void AjouterOffre(Offre O);
   public List<Offre> AfficherOffre();
   public void delete(Offre O);
   public void modifierOffre(Offre o);
   
   
}
