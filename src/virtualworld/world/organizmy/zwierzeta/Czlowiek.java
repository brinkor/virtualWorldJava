/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld.world.organizmy.zwierzeta;

import javax.swing.ImageIcon;
import virtualworld.world.Kierunek;
import virtualworld.world.Pole;
import virtualworld.world.World;
import virtualworld.world.Wynik;
import virtualworld.world.organizmy.Organizm;
import virtualworld.world.organizmy.Zwierze;

/**
 *
 * @author brink
 */
public class Czlowiek extends Zwierze {
    private static final int SUPER_SILA = 10;
    Kierunek kierunek;
    int licznik;
    
    public Czlowiek(int x, int y, int turaPowst, World world)
    {
        super(x, y, 5, 4, turaPowst, Pole.CZLOWIEK, true, world);
        kierunek = Kierunek.NIC;
        licznik = 0;
    }
    
    public Czlowiek(int x, int y, int turaPowst, World world, int sila, int licznik)
    {
        super(x, y, sila, 4, turaPowst, Pole.CZLOWIEK, true, world);
        this.licznik = licznik;
        kierunek = Kierunek.NIC;
    }
    
    @Override
    public void akcja()
    {
        Organizm org;
        switch (kierunek)
        {
            
	case GORA:
		if (!(world.isXYinWorld(x, y - 1)))
			break;
		if (world.isXYfree(x, y - 1))
			przesun(0, -1);
		else
		{
			org = world.getOrganizm(x, y - 1);
			if (org.kolizja(this) == Wynik.LOST)
				przesun(0, -1);
		}
		break;
	case DOL:
		if (!(world.isXYinWorld(x, y + 1)))
			break;
		if (world.isXYfree(x, y + 1))
			przesun(0, 1);
		else
		{
			org = world.getOrganizm(x, y + 1);
			if (org.kolizja(this) == Wynik.LOST)
				przesun(0, 1);
		}
		break;
	case LEWO:
		if (!(world.isXYinWorld(x - 1, y)))
			break;
		if (world.isXYfree(x - 1, y))
			przesun(-1, 0);
		else
		{
			org = world.getOrganizm(x - 1, y);
			if (org.kolizja(this) == Wynik.LOST)
				przesun(-1, 0);
		}
		break;
	case PRAWO:
		if (!(world.isXYinWorld(x + 1, y)))
			break;
		if (world.isXYfree(x + 1, y))
			przesun(1, 0);
		else
		{
			org = world.getOrganizm(x + 1, y);
			if (org.kolizja(this) == Wynik.LOST)
				przesun(1, 0);
		}
		break;
	}
    }
    
    @Override
    public void die()
    {
        super.die();
        world.smiercCzlowieka();
        world.getKomentator().komentujSmiercCzlowieka();
    }
    
    public Kierunek getKierunek()
    {
        return kierunek;
    }
    
    public void setKierunek(Kierunek kier)
    {
        kierunek = kier;
    }
    
    public void przesun(int oX, int oY)
    {
	if (oX == 0 && oY != 0)
	{
		world.setPole(x, y, Pole.EMPTY);
		y += oY;
		world.setPole(x, y, Pole.CZLOWIEK);
	}
	if (oX != 0 && oY == 0)
	{
		world.setPole(x, y, Pole.EMPTY);
		x += oX;
		world.setPole(x, y, Pole.CZLOWIEK);
	}
    }
    
    public boolean aktywujUmiejetnosc()
    {
        if (licznik == 0)
        {
            licznik = SUPER_SILA;
            sila = SUPER_SILA + 1;
            return true;
        }
        return false;
    }
    
    public void sprawdzUmiejetnosc()
    {
	if (licznik != 0)
	{
		if (licznik >= SUPER_SILA/2)
			sila--;
		licznik--;
	}
    }
    
    public int getLicznik()
    {
        return licznik;
    }
    
    @Override
    public ImageIcon getImage() {
        return new ImageIcon("C:\\vWorldImg\\czlowiek.png");
    }

    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world) {
        return new Czlowiek(x, y, turaPowst, world);
    }
    
    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world, int sila)
    {
        return new Czlowiek(x, y, turaPowst, world, sila, 0);
    }
}
