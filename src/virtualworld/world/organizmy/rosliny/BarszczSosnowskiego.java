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
public class BarszczSosnowskiego extends Roslina{
    public BarszczSosnowskiego(int x, int y, int turaPowst, World world)
    {
        super(x, y, 10, 0, turaPowst, Pole.BARSZCZ, true, world);
    }
    
    public BarszczSosnowskiego(int x, int y, int turaPowst, World world, int sila)
    {
        super(x, y, sila, 0, turaPowst, Pole.BARSZCZ, true, world);
    }
    
    @Override
    public void akcja()
    {
        Organizm org;
	if (world.isXYinWorld(x - 1, y) && (world.isXYfree(x - 1, y)) == false && !(world.czyRoslina(world.getOrganizm(x - 1, y))))
	{
		org = world.getOrganizm(x - 1, y);
		if (org != null)
		{
			world.getKomentator().komentujZabojstwoBarszczu(org);
			org.die();
		}
	}
	if (world.isXYinWorld(x - 1, y - 1) && (world.isXYfree(x - 1, y - 1)) == false && !(world.czyRoslina(world.getOrganizm(x - 1, y - 1))))
	{
		org = world.getOrganizm(x - 1, y - 1);
		if (org != null)
		{
			world.getKomentator().komentujZabojstwoBarszczu(org);
			org.die();
		}
	}
	if (world.isXYinWorld(x - 1, y + 1) && (world.isXYfree(x - 1, y + 1)) == false && !(world.czyRoslina(world.getOrganizm(x - 1, y + 1))))
	{
		org = world.getOrganizm(x - 1, y + 1);
		if (org != null)
		{
			world.getKomentator().komentujZabojstwoBarszczu(org);
			org.die();
		}
	}
	if (world.isXYinWorld(x, y - 1) && (world.isXYfree(x, y - 1)) == false && !(world.czyRoslina(world.getOrganizm(x, y - 1))))
	{
		org = world.getOrganizm(x, y - 1);
		if (org != null)
		{
			world.getKomentator().komentujZabojstwoBarszczu(org);
			org.die();
		}
	}
	if (world.isXYinWorld(x, y + 1) && (world.isXYfree(x, y + 1)) == false && !(world.czyRoslina(world.getOrganizm(x, y + 1))))
	{
		org = world.getOrganizm(x, y + 1);
		if (org != null)
		{
			world.getKomentator().komentujZabojstwoBarszczu(org);
			org.die();
		}
	}
	if (world.isXYinWorld(x + 1, y - 1) && (world.isXYfree(x + 1, y - 1)) == false && !(world.czyRoslina(world.getOrganizm(x + 1, y - 1))))
	{
		org = world.getOrganizm(x + 1, y - 1);
		if (org != null)
		{
			world.getKomentator().komentujZabojstwoBarszczu(org);
			org.die();
		}
	}
	if (world.isXYinWorld(x + 1, y) && (world.isXYfree(x + 1, y)) == false && !(world.czyRoslina(world.getOrganizm(x + 1, y))))
	{
		org = world.getOrganizm(x + 1, y);
		if (org != null)
		{
			world.getKomentator().komentujZabojstwoBarszczu(org);
			org.die();
		}
	}
	if (world.isXYinWorld(x + 1, y + 1) && (world.isXYfree(x + 1, y + 1)) == false && !(world.czyRoslina(world.getOrganizm(x + 1, y + 1))))
	{
		org = world.getOrganizm(x + 1, y + 1);
		if (org != null)
		{
			world.getKomentator().komentujZabojstwoBarszczu(org);
			org.die();
		}
	}
    }
    
    @Override
    public Wynik kolizja(Organizm org)
    {
        org.die();
        return super.kolizja(org);
    }
    
    @Override
    public ImageIcon getImage() {
        return new ImageIcon("C:\\vWorldImg\\barszcz.png");
    }

    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world) {
        return new BarszczSosnowskiego(x, y, turaPowst, world);
    }
    
    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world, int sila)
    {
        return new BarszczSosnowskiego(x, y, turaPowst, world, sila);
    }
}
