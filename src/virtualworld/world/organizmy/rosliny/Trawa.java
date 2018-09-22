/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld.world.organizmy.rosliny;

import javax.swing.ImageIcon;
import virtualworld.world.Pole;
import virtualworld.world.World;
import virtualworld.world.organizmy.Organizm;
import virtualworld.world.organizmy.Roslina;

/**
 *
 * @author brink
 */
public class Trawa extends Roslina{
    public Trawa(int x, int y, int turaPowst, World world)
    {
        super(x, y, 0, 0, turaPowst, Pole.TRAWA, true, world);
    }
    
    @Override
    public ImageIcon getImage() {
        return new ImageIcon("C:\\vWorldImg\\trawa.png");
    }

    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world) {
        return new Trawa(x, y, turaPowst, world);
    }
    
    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world, int sila)
    {
        return new Trawa(x, y, turaPowst, world);
    }
}
