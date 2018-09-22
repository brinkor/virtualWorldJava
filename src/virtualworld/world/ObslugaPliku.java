/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld.world;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import virtualworld.world.organizmy.Organizm;
import virtualworld.world.organizmy.zwierzeta.Czlowiek;

/**
 *
 * @author brink
 */
public class ObslugaPliku {
    private String fileName;
    private BufferedReader buff;
    private PrintWriter writer;
    
    public ObslugaPliku()
    {
        fileName = "save.txt";
    }
    public ObslugaPliku(String name)
    {
        fileName = name;
    }
    
    public boolean openToWrite()
    {
        try
        {
            writer = new PrintWriter(fileName);
        }
        catch (FileNotFoundException e)
        {
            return false;
        }
        return true;
    }
    
    public boolean openToRead()
    {
        try 
        {
            buff = new BufferedReader(new FileReader(fileName));
        }
        catch (FileNotFoundException e)
        {
            return false;
        }
        return true;
    }
    
    public void closeToRead()
    {
        try
        {
        buff.close();
        }
        catch (IOException e)
        {
            System.out.println("Nie mozna zamknac pliku");
        }
    }
    
    public void closeToWrite()
    {
        writer.close();
    }
    
    public void saveWorld(World world)
    {
        int koniec = world.isGameInProgress() ? 0 : 1;
        String str = Integer.toString(world.getX()) + " " + Integer.toString(world.getY()) + " " + Integer.toString(world.getTura()) + " " + Integer.toString(koniec);
        writer.println(str);
    }
    
    public void savePlayer(Czlowiek player)
    {
        String x = Integer.toString(player.getX());
        String y = Integer.toString(player.getY());
        String sila = Integer.toString(player.getSila());
        String licznik = Integer.toString(player.getLicznik());
        writer.println(x + " " + y + " " + sila + " " + licznik);
    }
    
    public void saveOrganizmy(List<Organizm> organizmy)
    {
        writer.println(Integer.toString(organizmy.size()));
        for (Organizm org : organizmy)
        {
            String orgClass = org.getClass().getSimpleName();
            if (Czlowiek.class.getSimpleName().equals(orgClass))
                continue;
            String x = Integer.toString(org.getX());
            String y = Integer.toString(org.getY());
            String sila = Integer.toString(org.getSila());
            String tura = Integer.toString(org.getTuraPowst());
            writer.println(orgClass + " " + x + " " + y + " " + sila + " " + tura);
        }
    }
    
   public boolean saveGame(World world)
   {
       if (openToWrite())
       {
           saveWorld(world);
           savePlayer((Czlowiek)world.getCzlowiek());
           saveOrganizmy(world.getOrganizmy());
           closeToWrite();
           return true;
       }
       else
           return false;
   }
   
   public boolean loadWorld(World world)
   {
       try
       {
           String str = buff.readLine();
           String[] part = str.split(" ");
           int x = Integer.parseInt(part[0]);
           int y = Integer.parseInt(part[1]);
           int tura = Integer.parseInt(part[2]);
           int koniec = Integer.parseInt(part[3]);
           boolean k = koniec > 0 ? true : false ;
           world.nowaPlansza(x, y, tura);
           world.setKoniec(k);
       }
       catch (IOException e)
       {
           System.out.println("Błąd odczytu pliku");
           return false;
       }
       return true;
   }
   
   public boolean loadPlayer(World world)
   {
       try
       {
           String str = buff.readLine();
           String[] part = str.split(" ");
           int x = Integer.parseInt(part[0]);
           int y = Integer.parseInt(part[1]);
           int sila = Integer.parseInt(part[2]);
           int licznik = Integer.parseInt(part[3]);
           world.dodajOrganizm(new Czlowiek(x, y, 0, world, sila, licznik)); // brak licznika!!
       }
       catch (IOException e)
       {
           System.out.println("Błąd odczytu pliku");
           return false;
       }
       return true;
   }
   
   public boolean loadOrganizmy(World world)
   {
       try
       {
           int orgNum = Integer.parseInt(buff.readLine());
           String str;
           String[] part;
           List<Organizm> organizmy = world.getOrgPocz();
           int x, y, sila, tura;
           for (int i = 0; i < orgNum - 1; i++) // -1 bo bez gracza
           {
               str = buff.readLine();
               part = str.split(" ");
               x = Integer.parseInt(part[1]);
               y = Integer.parseInt(part[2]);
               sila = Integer.parseInt(part[3]);
               tura = Integer.parseInt(part[4]);
               for (Organizm org : organizmy)
               {
                   if (org.getClass().getSimpleName().equals(part[0]))
                       world.dodajOrganizm(org.makeNew(x, y, tura, world, sila));
               }
           }
       }
       catch (IOException e)
       {
           System.out.println("Błąd odczytu pliku");
           return false;
       }
       return true;
   }
   
   public boolean loadGame(World world)
   {
       if (openToRead())
       {
           if (!loadWorld(world))
               return false;
           world.setOrganizmy(new LinkedList<Organizm>());
           if (!loadPlayer(world))
               return false;
           if (!loadOrganizmy(world))
               return false;
           closeToRead();
           return true;
       }
       else
           return false;
   }
}
