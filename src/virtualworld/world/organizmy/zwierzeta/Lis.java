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
import virtualworld.world.XY;
import virtualworld.world.organizmy.Organizm;
import virtualworld.world.organizmy.Zwierze;

/**
 *
 * @author brink
 */
public class Lis extends Zwierze {
    
    public Lis(int x, int y, int turaPowst, World world)
    {
        super(x, y, 3, 7, turaPowst, Pole.LIS, true, world);
    }
    
    public Lis(int x, int y, int turaPowst, World world, int sila)
    {
        super(x, y, sila, 7, turaPowst, Pole.LIS, true, world);
    }

    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world) 
    {
        return new Lis(x, y, turaPowst, world);
    }

    @Override
    public Organizm makeNew(int x, int y, int turaPowst, World world, int sila)
    {
        return new Lis(x, y, turaPowst, world, sila);
    }
    
    @Override
    public ImageIcon getImage() {
        return new ImageIcon("C:\\vWorldImg\\lis.png");
    }
    
    @Override
    public void akcja()
    {
        
	XY xy = znajdzBezpiecznePole();
	if (xy.x != -1 && xy.y != -1)
	{
            if (world.isXYfree(xy.x, xy.y))
            {
                world.setPole(x, y, Pole.EMPTY);
                world.setPole(xy.x, xy.y, typPola);
                x = xy.x;
                y = xy.y;
            }
            else
            {
                Organizm org = world.getOrganizm(xy.x, xy.y);
                if (org != null)
                {
                    switch (org.kolizja(this))
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
	}
    }
    
    public XY znajdzBezpiecznePole()
    {
        XY xy = new XY();
	Random gen = new Random();
	int losowy = gen.nextInt(8);
	int zwiekszany = losowy;
	do
	{
		switch (zwiekszany)
		{
		case 0:
			if (world.isXYinWorld(x - 1, y) && (world.isXYfree(x - 1, y) || isWeaker(x - 1, y)))
			{
				xy.x = x - 1;
				xy.y = y;
				return xy;
			}
			break;
		case 1:
			if (world.isXYinWorld(x - 1, y - 1) && (world.isXYfree(x - 1, y - 1) || isWeaker(x - 1, y - 1)))
			{
				xy.x = x - 1;
				xy.y = y - 1;
				return xy;
			}
			break;
		case 2:
			if (world.isXYinWorld(x - 1, y + 1) && (world.isXYfree(x - 1, y + 1) || isWeaker(x - 1, y + 1)))
			{
				xy.x = x - 1;
				xy.y = y + 1;
				return xy;
			}
			break;
		case 3:
			if (world.isXYinWorld(x, y - 1) && (world.isXYfree(x, y - 1) || isWeaker(x, y - 1)))
			{
				xy.x = x;
				xy.y = y - 1;
				return xy;
			}
			break;
		case 4:
			if (world.isXYinWorld(x, y + 1) && (world.isXYfree(x, y + 1) || isWeaker(x, y + 1)))
			{
				xy.x = x;
				xy.y = y + 1;
				return xy;
			}
			break;
		case 5:
			if (world.isXYinWorld(x + 1, y - 1) && (world.isXYfree(x + 1, y - 1) || isWeaker(x + 1, y - 1)))
			{
				xy.x = x + 1;
				xy.y = y - 1;
				return xy;
			}
			break;
		case 6:
			if (world.isXYinWorld(x + 1, y) && (world.isXYfree(x + 1, y) || isWeaker(x + 1, y)))
			{
				xy.x = x + 1;
				xy.y = y;
				return xy;
			}
			break;
		case 7:
			if (world.isXYinWorld(x + 1, y + 1) && (world.isXYfree(x + 1, y + 1) || isWeaker(x + 1, y + 1)))
			{
				xy.x = x + 1;
				xy.y = y + 1;
				return xy;
			}
			break;
		}
		zwiekszany = (zwiekszany + 1) % 8;
	} while (losowy != zwiekszany);
	xy.x = -1;
	xy.y = -1;
	return xy;
    }
    
    public boolean isWeaker(int x, int y)
    {
        Organizm org = world.getOrganizm(x, y);
	if (org != null)
	{
		if ((org.getSila() < this.getSila()) || ((org.getSila() == this.getSila()) && (org.getTuraPowst() < this.getTuraPowst())))
			return true;
		else
			return false;
	}
	return true;
    }
    
}
