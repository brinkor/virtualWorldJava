/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld.world.organizmy;

import javax.swing.ImageIcon;
import virtualworld.world.Pole;
import virtualworld.world.World;
import virtualworld.world.Wynik;

/**
 *
 * @author brink
 */
public abstract class Organizm {
    protected int sila, inicjatywa, x, y, turaPowst;
    protected Pole typPola;
    protected boolean alive;
    protected World world;
    public Organizm(int x, int y, int sila, int inicjatywa, int turaPowst, Pole typPola, boolean alive, World world)
    {
        this.x=x;
        this.y=y;
        this.sila=sila;
        this.turaPowst=turaPowst;
        this.typPola=typPola;
        this.alive=alive;
        this.world=world;
        this.inicjatywa=inicjatywa;
    }
    abstract public ImageIcon getImage();
    abstract public void akcja();
    abstract public Organizm makeNew(int x, int y, int turaPowst, World world);
    abstract public Organizm makeNew(int x, int y, int turaPowst, World world, int sila);
    
    public Wynik kolizja(Organizm org)
    {
        boolean win;
        if (sila == org.getSila())
        {
            if (turaPowst < org.getTuraPowst())
                win = true;
            else
                win = false;
        }
        else if (sila > org.getSila())
            win = true;
        else
            win = false;
        if (win == true)
        {
            world.getKomentator().komentujWalke(this, org);
            org.die();
        }
        else
        {
            world.getKomentator().komentujWalke(org, this);
            this.die();
        }
        if (win == true)
            return Wynik.WIN;
        else
            return Wynik.LOST;
    }

    public int getSila() {
        return sila;
    }

    public int getInicjatywa() {
        return inicjatywa;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTuraPowst() {
        return turaPowst;
    }

    public Pole getTypPola() {
        return typPola;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }
    
    public void die()
    {
        world.setPole(x, y, Pole.EMPTY);
        alive = false;
    }
}
