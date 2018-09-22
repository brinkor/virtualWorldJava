/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld.world.organizmy.zwierzeta;

import javax.swing.ImageIcon;
import virtualworld.world.Pole;
import virtualworld.world.World;
import virtualworld.world.organizmy.Organizm;
import virtualworld.world.organizmy.Zwierze;

/**
 *
 * @author brink
 */
public class Owca extends Zwierze {
    
    public Owca(int x, int y, int turaPowst, World world)
    {
        super(x, y, 4, 4, turaPowst, Pole.OWCA, true, world);
    }
    
    public Owca(int x, int y, int turaPowst, World world, int sila)
    {
        super(x, y, sila, 4, turaPowst, Pole.OWCA, true, world);
    }

    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world) 
    {
        return new Owca(x, y, turaPowst, world);
    }

    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world, int sila)
    {
        return new Owca(x, y, turaPowst, world, sila);
    }
    
    @Override
    public ImageIcon getImage() {
        return new ImageIcon("C:\\vWorldImg\\owca.png");
    }
    
}
