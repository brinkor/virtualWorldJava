/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import virtualworld.world.organizmy.Organizm;
import virtualworld.world.organizmy.zwierzeta.*;
import virtualworld.world.organizmy.rosliny.*;
import virtualworld.world.graphic.Window;
import virtualworld.world.organizmy.Roslina;

/**
 *
 * @author brink
 */
public class World {
    private List <Organizm> organizmy;
    private final List <Organizm> organizmyPoczatkowe;
    private int tura;
    private int x, y;
    private Pole[][] pole;
    private final Komentator komentator;
    private boolean koniecGry;
    private static final int LICZBA_ROZNYCH_ORGANIZMOW = 11;
    private JFrame window;
    
    public World(int x, int y)
    {
        this.x=x;
        this.y=y;
        //staly rozmiar planszy
        //this.x = 20;
        //this.y = 20;
        koniecGry = false;
        tura = 0;
        komentator = new Komentator(this);
        organizmy = new LinkedList<Organizm>();
        organizmyPoczatkowe = new LinkedList<Organizm>();
        pole = new Pole[y][x];
	for (int i = 0; i < y; i++)
	{
		for (int j = 0; j < x; j++)
			pole[i][j] = Pole.EMPTY;
	}
    }
    
    private void ustawOrgPocz() // dopisac organizm do list w celu dodania do programu
    {
        for (Pole typ : Pole.values())
        {
            switch (typ) // dodawanie poczatkowych organizmow do listy 
            {
                case CZLOWIEK:
                    organizmyPoczatkowe.add(new Czlowiek(0, 0, tura, this));
                    break;
                case TRAWA:
                    organizmyPoczatkowe.add(new Trawa(0, 0, tura, this));
                    break;
                case OWCA:
                    organizmyPoczatkowe.add(new Owca(0, 0, tura, this));
                    break;
                case WILK:
                    organizmyPoczatkowe.add(new Wilk(0, 0, tura, this));
                    break;
                case LIS:
                    organizmyPoczatkowe.add(new Lis(0, 0, tura, this));
                    break;
                case ZOLW:
                    organizmyPoczatkowe.add(new Zolw(0, 0, tura, this));
                    break;
                case ANTYLOPA:
                    organizmyPoczatkowe.add(new Antylopa(0, 0, tura, this));
                    break;
                case MLECZ:
                    organizmyPoczatkowe.add(new Mlecz(0, 0, tura, this));
                    break;
                case GUARANA:
                    organizmyPoczatkowe.add(new Guarana(0, 0, tura, this));
                    break;
                case WILCZE_JAG:
                    organizmyPoczatkowe.add(new WilczeJagody(0, 0, tura, this));
                    break;
                case BARSZCZ:
                    organizmyPoczatkowe.add(new BarszczSosnowskiego(0, 0, tura, this));
                    break;
            }
        }
    }
    
    public List<Organizm> getOrgPocz()
    {
        return organizmyPoczatkowe;
    }
    
    public int getTura()
    {
        return tura;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public Komentator getKomentator()
    {
        return komentator;
    }
    
    public List<Organizm> getOrganizmy()
    {
        return organizmy;
    }
    
    public Organizm getOrganizm(int x,int y)
    {
        Organizm org;
        for (int i = 0; i < organizmy.size(); i++)
        {
            org = organizmy.get(i);
            if (org.getX() == x && org.getY() == y)
                return org;
        }
        return null;
    }
    
    public Organizm getCzlowiek()
    {
        Organizm org;
        for (int i = 0; i < organizmy.size(); i++)
        {
            org = organizmy.get(i);
            if (org instanceof Czlowiek)
                return org;
        }
        return null;
    }
    
    public void nowaPlansza(int x, int y, int tura)
    {
        this.x = x;
        this.y = y;
        pole = new Pole[y][x];
	for (int i = 0; i < y; i++)
	{
            for (int j = 0; j < x; j++)
		pole[i][j] = Pole.EMPTY;
	}
	this.tura = tura;
    }
    
    public void setKoniec(boolean koniec)
    {
        koniecGry = koniec;
    }
    
    public void setTura(int tura)
    {
        this.tura=tura;
    }
    
    public void setOrganizmy(List<Organizm> orgs)
    {
        organizmy = orgs;
    }
    
    public void setPole(int x, int y, Pole typPola)
    {
        pole[y][x]=typPola;
    }
    
    public boolean isXYfree(int x, int y)
    {
        if (pole[y][x] == Pole.EMPTY)
            return true;
        else
            return false;
        /*
        if (getOrganizm(x, y) == null)
            return true;
        else
            return false;
            */
    }
    
    public boolean isXYinWorld(int x, int y)
    {
        if (x >= 0 && x < this.x && y >= 0 && y < this.y)
            return true;
        else
            return false;
    }
    
    public XY findEmpty(int x, int y)
    {
        XY xy = new XY();
	if (isXYinWorld(x - 1, y) && isXYfree(x - 1, y))
	{
		xy.x = x - 1;
		xy.y = y;
		return xy;
	}
	if (isXYinWorld(x - 1, y - 1) && isXYfree(x - 1, y - 1))
	{
		xy.x = x - 1;
		xy.y = y - 1;
		return xy;
	}
	if (isXYinWorld(x - 1, y + 1) && isXYfree(x - 1, y + 1))
	{
		xy.x = x - 1;
		xy.y = y + 1;
		return xy;
	}
	if (isXYinWorld(x, y - 1) && isXYfree(x, y - 1))
	{
		xy.x = x ;
		xy.y = y - 1;
		return xy;
	}
	if (isXYinWorld(x, y + 1) && isXYfree(x, y + 1))
	{
		xy.x = x;
		xy.y = y + 1;
		return xy;
	}
	if (isXYinWorld(x + 1, y - 1) && isXYfree(x + 1, y - 1))
	{
		xy.x = x + 1;
		xy.y = y - 1;
		return xy;
	}
	if (isXYinWorld(x + 1, y) && isXYfree(x + 1, y))
	{
		xy.x = x + 1;
		xy.y = y;
		return xy;
	}
	if (isXYinWorld(x + 1, y + 1) && isXYfree(x + 1, y + 1))
	{
		xy.x = x + 1;
		xy.y = y + 1;
		return xy;
	}
	xy.x = -1;
	xy.y = -1;
	return xy;
    }
    
    public void dodajOrganizm(Organizm org)
    {
        setPole(org.getX(), org.getY(), org.getTypPola());
        organizmy.add(org);
        //organizmy.sort(new OrgComparator());
        Collections.sort(organizmy, new Comparator<Organizm>()
                {
                    @Override
                    public int compare(Organizm org1, Organizm org2)
                    {
                        int in1 = org1.getInicjatywa();
                        int in2 = org2.getInicjatywa();
                        if (in1 == in2)
                            return org1.getTuraPowst() - org2.getTuraPowst();
                        else
                            return in2 - in1;
                    }
                }
        );
    }
    
    public XY findAnyField(int x, int y)
    {
        Random generator = new Random();
        XY xy = new XY();
	boolean found = false;
        int los = generator.nextInt(8);
	while (!found)
	{
		switch (los)
		{
		case 0:
			if (isXYinWorld(x + 1, y + 1))
			{
				found = true;
				xy.x = x + 1;
				xy.y = y + 1;
			}
			break;
		case 1:
			if (isXYinWorld(x + 1, y))
			{
				found = true;
				xy.x = x + 1;
				xy.y = y;
			}
			break;
		case 2:
			if (isXYinWorld(x - 1, y))
			{
				found = true;
				xy.x = x - 1;
				xy.y = y;
			}
			break;
		case 3:
			if (isXYinWorld(x, y + 1))
			{
				found = true;
				xy.x = x;
				xy.y = y + 1;
			}
			break;
		case 4:
			if (isXYinWorld(x, y - 1))
			{
				found = true;
				xy.x = x;
				xy.y = y - 1;
			}
			break;
		case 5:
			if (isXYinWorld(x + 1, y - 1))
			{
				found = true;
				xy.x = x + 1;
				xy.y = y - 1;
			}
			break;
		case 6:
			if (isXYinWorld(x - 1, y - 1))
			{
				found = true;
				xy.x = x - 1;
				xy.y = y - 1;
			}
			break;
		case 7:
			if (isXYinWorld(x - 1, y + 1))
			{
				found = true;
				xy.x = x - 1;
				xy.y = y + 1;
			}
			break;
		}
                los = (los + 1) % 8;
	}
	return xy;
    }
    
    public void smiercCzlowieka()
    {
        koniecGry = true;
        //wyswietlic napis w okienku ?
    }
    
    public boolean czyRoslina(Organizm org)
    {
        if (org != null)
        {
        if (org.getClass().getSuperclass().getSimpleName().equals(Roslina.class.getSimpleName()))
            return true;
        else
            return false;
        }
        else
            return false;
    }
    
    public boolean isGameInProgress()
    {
        return !koniecGry;
    }
    
    public void generujSwiat()
    {
        if (tura == 0)
        {
            Random generator = new Random();
            Organizm org;
            boolean wpisano = false;
            int losX, losY;
            Pole rodzaj_organizmu;
            int lOrg;
            for (Organizm o : organizmyPoczatkowe)
            {
                if (o.getTypPola() == Pole.CZLOWIEK)
                    lOrg=1;
                else
                    lOrg = generator.nextInt(2) + 2;
                for (int j = 0; j < lOrg; j++)
                {
                    while(!wpisano)
                    {
                        losX = generator.nextInt(x);
                        losY = generator.nextInt(y);
                        if (pole[losY][losX] == Pole.EMPTY)
                        {
                            org = o.makeNew(losX, losY, tura, this);
                            dodajOrganizm(org);
                            wpisano = true;
                        }
                    }
                    wpisano = false;
                }
            }
        }
    }
    
    public void usunCiala()
    {
        List<Organizm> rem = new ArrayList<Organizm>();
        for (Organizm org : organizmy)
        {
            if (org.isAlive() == false)
                rem.add(org);
        }
        organizmy.removeAll(rem);
    }
    
    public Pole getPoleType(int x, int y)
    {
        return pole[y][x];
    }
    
    public void zyj()
    {
        boolean dzialaj = true;
        ustawOrgPocz();
        generujSwiat();
        window = new Window(x, y, this);
        //rysworld
        tura = 1;
        Czlowiek czlowiek = (Czlowiek) getCzlowiek();
    }
    
    public void wykonajTure()
    {
        if (tura == 0)
            generujSwiat();
        else
        {
            ((Czlowiek)getCzlowiek()).sprawdzUmiejetnosc();
            List<Organizm> akcje = new ArrayList<Organizm>();
            for (Organizm org : organizmy)
            {
                    akcje.add(org);
            }
            for (Organizm org : akcje)
            {
                if (org.isAlive() == true)
                    org.akcja();
            }
            usunCiala();
            //rysuj
        }   
        tura++;
    }
}