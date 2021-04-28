/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.postulation;
import java.util.List;

/**
 *
 * @author user16
 */
public interface IservicesPostulation {
       public void AjouterPostulation(postulation p);
       public List<postulation> AfficherPostulation();
       public void delete(postulation p);
       public void modifierPostulation(postulation p);
    
}
