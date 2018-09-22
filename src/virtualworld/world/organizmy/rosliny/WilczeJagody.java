/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld.world.organizmy.rosliny;

import javax.swing.ImageIcon;
import virtualworld.world.Pole;
import virtualworld.world.World;
import virtualworld.world.Wynik;
import virtualworld.world.organizmy.Organizm;
import virtualworld.world.organizmy.Roslina;

/**
 *
 * @author brink
 */
public class WilczeJagody extends Roslina{
    public WilczeJagody(int x, int y, int turaPowst, World world)
    {
        super(x, y, 99, 0, turaPowst, Pole.WILCZE_JAG, true, world);
    }
    
    @Override
    public Wynik kolizja(Organizm org)
    {
        org.die();
        return super.kolizja(org);
    }
    
    @Override
    public ImageIcon getImage() {
        return new ImageIcon("C:\\vWorldImg\\wilczeJag.png");
    }

    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world) {
        return new WilczeJagody(x, y, turaPowst, world);
    }
    
    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world, int sila)
    {
        return new WilczeJagody(x, y, turaPowst, world);
    }
}
