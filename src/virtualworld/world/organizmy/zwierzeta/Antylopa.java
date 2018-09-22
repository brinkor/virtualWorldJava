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
import virtualworld.world.XY;
import virtualworld.world.organizmy.Organizm;
import virtualworld.world.organizmy.Zwierze;

/**
 *
 * @author brink
 */
public class Antylopa extends Zwierze {
    
    public Antylopa(int x, int y, int turaPowst, World world)
    {
        super(x, y, 4, 4, turaPowst, Pole.ANTYLOPA, true, world);
    }
    
    public Antylopa(int x, int y, int turaPowst, World world, int sila)
    {
        super(x, y, sila, 4, turaPowst, Pole.ANTYLOPA, true, world);
    }

    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world) 
    {
        return new Antylopa(x, y, turaPowst, world);
    }
    
    @Override
    public void akcja()
    {
        for (int i = 0; i < 2; i++)
        {
            //System.out.println("ruch " + i);
            if (this.isAlive())
                super.akcja();
        }
    }
    
    @Override
    public Wynik kolizja(Organizm org)
    {
        Random gen = new Random();
        boolean ucieczka = gen.nextBoolean();
            //System.out.println(ucieczka);
        if (this.getClass().getName().equals(org.getClass().getName()))
        {
            //System.out.println("rozmnazam");
            return super.kolizja(org);
        }
        else if (ucieczka == true)
        {
            //System.out.println("uciekam");
            world.getKomentator().komentujUcieczkaAntylopy();
            XY xy;
            do
            {
                xy = world.findAnyField(x, y);
            }while (xy.x == org.getX() && xy.y == org.getY());
            
            if (world.isXYfree(xy.x, xy.y))
            {
                world.setPole(x, y, Pole.EMPTY);
                world.setPole(xy.x, xy.y, typPola);
                x = xy.x;
                y = xy.y;
            }
            else
            {
                Organizm przeciwnik = world.getOrganizm(xy.x, xy.y);
                if (przeciwnik != null)
                {
                    switch (przeciwnik.kolizja(this))
                    {
                        case LOST:
                            world.setPole(x, y, Pole.EMPTY);
                            world.setPole(xy.x, xy.y, typPola);
                            x = xy.x;
                            y = xy.y;
                            break;
                        case WIN:
                            world.setPole(x, y, Pole.EMPTY);
                            break;
                    }
                }
            }
            return Wynik.LOST;
        }
        else
            return super.kolizja(org);
    }

    @Override
    public ImageIcon getImage() {
        return new ImageIcon("C:\\vWorldImg\\antylopa.png");
    }
    
    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world, int sila)
    {
        return new Antylopa(x, y, turaPowst, world, sila);
    }
}
