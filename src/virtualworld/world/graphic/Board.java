/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld.world.graphic;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author brink
 */
public class Board extends JPanel{
    private final int x, y;
    private final Window window;
    
    public Board(int x, int y, Window w)
    {
        super();
        this.x = x;
        this.y = y;
        window = w;
        setLayout(null);
        setBounds(window.BREAK,window.BREAK, x*window.FIELD_SIZE, y*window.FIELD_SIZE);
        setBackground(Color.red);
        setFocusable(true);
        addKeyListener(new Movement(window));
    }
    
}
