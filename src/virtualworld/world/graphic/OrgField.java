/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld.world.graphic;

import javax.swing.JMenuItem;
import virtualworld.world.organizmy.Organizm;

/**
 *
 * @author brink
 */
public class OrgField extends JMenuItem{
    private final Organizm org;
    public OrgField(String name, Organizm org)
    {
        super(name);
        this.org = org;
    }

    public Organizm getOrg() {
        return org;
    }
}
