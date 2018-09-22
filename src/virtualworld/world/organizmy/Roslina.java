/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld.world.organizmy;

import java.util.Random;
import virtualworld.world.Pole;
import virtualworld.world.World;
import virtualworld.world.XY;

/**
 *
 * @author brink
 */
public abstract class Roslina extends Organizm{
        protected static final int TEMPO_ROZROSTU = 3;
    	public Roslina(int x, int y, int sila, int inicjatywa, int turaPowst, Pole typPola, boolean alive, World world)
        {
            super(x,y,sila,inicjatywa,turaPowst, typPola, alive, world);
        }
        @Override
        public void akcja()
        {
            Random generator = new Random();
            if (generator.nextInt(100) < TEMPO_ROZROSTU)
            {
                XY xy = world.findEmpty(x, y);
                if (xy.x == -1 || xy.y == -1)
                    return;
                world.dodajOrganizm(makeNew(xy.x, xy.y, world.getTura(), world));
                world.getKomentator().komentujZasianie(this);
            }
        }
}
