/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld.world.graphic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import virtualworld.world.Kierunek;
import virtualworld.world.organizmy.zwierzeta.Czlowiek;

/**
 *
 * @author brink
 */
public class Movement extends JFrame implements KeyListener {

    private final Window win;
    public Movement(Window w)
    {
        win = w;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if (!win.getWorld().isGameInProgress())
            return;
        switch(key)
        {
            case KeyEvent.VK_LEFT:
                ((Czlowiek)win.getWorld().getCzlowiek()).setKierunek(Kierunek.LEWO);
                win.getWorld().wykonajTure();
                win.getCommentary().addAllComments();
                win.updateBoard();
                break;
            case KeyEvent.VK_RIGHT:
                ((Czlowiek)win.getWorld().getCzlowiek()).setKierunek(Kierunek.PRAWO);
                win.getWorld().wykonajTure();
                win.getCommentary().addAllComments();
                win.updateBoard();
                break;
            case KeyEvent.VK_UP:
                ((Czlowiek)win.getWorld().getCzlowiek()).setKierunek(Kierunek.GORA);
                win.getWorld().wykonajTure();
                win.getCommentary().addAllComments();
                win.updateBoard();
                break;
            case KeyEvent.VK_DOWN:
                ((Czlowiek)win.getWorld().getCzlowiek()).setKierunek(Kierunek.DOL);
                win.getWorld().wykonajTure();
                win.getCommentary().addAllComments();
                win.updateBoard();
                break;
        }    
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
