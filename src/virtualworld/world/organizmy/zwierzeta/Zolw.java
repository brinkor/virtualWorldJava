/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld.world.organizmy.zwierzeta;

import java.util.Random;
import javax.swing.ImageIcon;
import virtualworld.world.Pole;
import virtualworld.world.World;
import virtualworld.world.Wynik;
import virtualworld.world.organizmy.Organizm;
import virtualworld.world.organizmy.Zwierze;

/**
 *
 * @author brink
 */
public class Zolw extends Zwierze {
    
    public Zolw(int x, int y, int turaPowst, World world)
    {
        super(x, y, 2, 1, turaPowst, Pole.ZOLW, true, world);
    }
    
    public Zolw(int x, int y, int turaPowst, World world, int sila)
    {
        super(x, y, sila, 1, turaPowst, Pole.ZOLW, true, world);
    }

    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world) 
    {
        return new Zolw(x, y, turaPowst, world);
    }

    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world, int sila)
    {
        return new Zolw(x, y, turaPowst, world, sila);
    }
    
    @Override
    public ImageIcon getImage() {
        return new ImageIcon("C:\\vWorldImg\\zolw.png");
    }
    
    @Override
    public void akcja()
    {
        Random gen = new Random();
        if (gen.nextInt(100) + 1 > 75)
            super.akcja();
    }
    
    @Override
    public Wynik kolizja(Organizm org)
    {
        if (org.getSila() < 5)
	{
		world.getKomentator().komentujOdbicieAtaku(org, this);
		return Wynik.NEW;
	}
	else
		return super.kolizja(org);
    }
}
