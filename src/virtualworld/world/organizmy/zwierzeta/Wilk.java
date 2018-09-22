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
public class Wilk extends Zwierze {
    
    public Wilk(int x, int y, int turaPowst, World world)
    {
        super(x, y, 9, 5, turaPowst, Pole.WILK, true, world);
    }
    
    public Wilk(int x, int y, int turaPowst, World world, int sila)
    {
        super(x, y, sila, 5, turaPowst, Pole.WILK, true, world);
    }

    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world) 
    {
        return new Wilk(x, y, turaPowst, world);
    }
    
    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world, int sila)
    {
        return new Wilk(x, y, turaPowst, world, sila);
    }

    @Override
    public ImageIcon getImage() {
        return new ImageIcon("C:\\vWorldImg\\wilk.png");
    }
    
}
