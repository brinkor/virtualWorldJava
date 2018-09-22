/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld.world;

import java.util.LinkedList;
import java.util.List;
import virtualworld.world.organizmy.Organizm;
import virtualworld.world.organizmy.Roslina;

/**
 *
 * @author brink
 */
public class Komentator {
    private final World world;
    private final List<String> komentarze;
    public Komentator(World world)
    {
        this.world = world;
        komentarze = new LinkedList<String>();
        dodajKomentarz("Powstal nowy swiat!");
    }

    public List<String> getKomentarze()
    {
        return komentarze;
    }

    private void dodajKomentarz(String koment)
    {
        komentarze.add(koment);
    }

    public void komentujWalke(Organizm orgWin, Organizm orgLost)
    {
        String kom;
        String win = orgWin.getClass().getSimpleName();
        String lost = orgLost.getClass().getSimpleName();
        if (orgWin.getClass().getSuperclass().getSimpleName().equals(Roslina.class.getSimpleName()))
            kom = lost + " zjada " + win + " i umiera";
        else if (orgLost.getClass().getSuperclass().getSimpleName().equals(Roslina.class.getSimpleName()))
            kom = win + " zjada " + lost;
        else
            kom = lost + " polegl w walce z " + win;
        dodajKomentarz(kom);
    }

    public void komentujRozmnazanie(Organizm org)
    {
        String roz = org.getClass().getSimpleName();
        String kom = roz + " rozmnozyl sie";
        dodajKomentarz(kom);
    }

    public void komentujZasianie(Organizm org)
    {
        String zas = org.getClass().getSimpleName();
        String kom = zas + " zasial nowa rosline";
        dodajKomentarz(kom);
    }

    public void komentujSmiercCzlowieka()
    {
	String str = "Gracz umarl, koniec gry.";
	dodajKomentarz(str);
    }

    public void komentujUcieczkaAntylopy()
    {
        String kom = "Antylopa ucieka przed walka";
	dodajKomentarz(kom);
    }

    public void komentujZabojstwoBarszczu(Organizm org)
    {
        String dead = org.getClass().getSimpleName();
        String kom = "Barszcz Sosnowskiego zabija " + dead;
	dodajKomentarz(kom);
    }

    public void komentujOdbicieAtaku(Organizm atak, Organizm obr)
    {
        String atakujacy = atak.getClass().getSimpleName();
        String odbijajacy = obr.getClass().getSimpleName();
        String kom = odbijajacy + " odbija atak " + atakujacy;
        dodajKomentarz(kom);
    }

    public void komentujAktywacjeUm()
    {
	String kom = "Czlowiek aktywowal swoja umiejetnosc specjalna, posiada teraz 10 sily.";
	dodajKomentarz(kom);
    }

    public void komentujZapis()
    {
	String kom = "Zapisano gre.";
	dodajKomentarz(kom);
    }

    public void komentujWczytanie()
    {
	String kom = "Wczytano gre.";
	dodajKomentarz(kom);
    }

    public void usunKomentarze()
    {
        komentarze.removeAll(komentarze);
    }
    
}
