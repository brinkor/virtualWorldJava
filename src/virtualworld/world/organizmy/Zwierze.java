/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld.world.organizmy;

import virtualworld.world.Pole;
import virtualworld.world.World;
import virtualworld.world.Wynik;
import virtualworld.world.XY;

/**
 *
 * @author brink
 */
public abstract class Zwierze extends Organizm
{
    public Zwierze(int x, int y, int sila, int inicjatywa, int turPowst, Pole typPola, boolean alive, World world)
    {
        super(x, y, sila, inicjatywa, turPowst, typPola, alive, world);
    }
    
    @Override
    public void akcja()
    {
        //System.out.println("akcja zwirzaka");
        XY xy = world.findAnyField(x, y);
        if (world.isXYfree(xy.x, xy.y))
        {
            //System.out.println("wolne");
            world.setPole(x, y, Pole.EMPTY);
            world.setPole(xy.x, xy.y, this.getTypPola());
            x = xy.x;
            y = xy.y;
        }
        else
        {
            //System.out.println("zajete");
            Organizm org = world.getOrganizm(xy.x,xy.y);
            if(org != null)
            {
                //System.out.println("przez kogos");
                switch (org.kolizja(this))
                {
                    case LOST:
                        world.setPole(x, y, Pole.EMPTY);
                        world.setPole(xy.x, xy.y, this.getTypPola());
                        x = xy.x;
                        y = xy.y;
                        break;
                    case WIN:
                        world.setPole(x, y, Pole.EMPTY);
                        break;
                }
            }
            else
                System.out.println("nie ma organizmu na zajetym polu ????");
        }
    }
    
    @Override
    public Wynik kolizja(Organizm org)
    {
                //System.out.println("kolizja zw");
            if (org.getClass() == this.getClass())
            {
                //System.out.println("rozmnazanie");
                rozmnazaj();
                return Wynik.NEW;
            }
            return super.kolizja(org);
    }
    
    public void rozmnazaj()
    {
        XY puste = world.findEmpty(x, y);
        if (puste.x == -1 || puste.y == -1)
        {
            return;
        }
        //world.setPole(puste.x, puste.y, typPola);
        world.dodajOrganizm(makeNew(puste.x, puste.y, world.getTura(), world));
        world.getKomentator().komentujRozmnazanie(this);
    }
}
