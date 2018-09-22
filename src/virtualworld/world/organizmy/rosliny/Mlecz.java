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
public class Mlecz extends Roslina{
    public Mlecz(int x, int y, int turaPowst, World world)
    {
        super(x, y, 0, 0, turaPowst, Pole.MLECZ, true, world);
    }
    
    public Mlecz(int x, int y, int turaPowst, World world, int sila)
    {
        super(x, y, sila, 0, turaPowst, Pole.MLECZ, true, world);
    }
    
    @Override
    public void akcja()
    {
        for (int i = 0; i < 3; i++)
            super.akcja();
    }
    
    @Override
    public ImageIcon getImage() {
        return new ImageIcon("C:\\vWorldImg\\mlecz.png");
    }

    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world) {
        return new Mlecz(x, y, turaPowst, world);
    }
    
    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world, int sila)
    {
        return new Mlecz(x, y, turaPowst, world, sila);
    }
}
