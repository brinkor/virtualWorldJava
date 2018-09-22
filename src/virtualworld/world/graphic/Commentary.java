/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld.world.graphic;

import java.util.List;
import javax.swing.JTextArea;

/**
 *
 * @author brink
 */
public class Commentary extends JTextArea {
    private final int x, y;
    private int i;
    private final Window window;
    //private JScrollPane textSpace;
    
    public Commentary(int x, int y, Window w)
    {
        super("Nowa gra!");
        i = 0;
        this.x = 750;
        this.y = y;
        window = w;
        //textSpace = new JScrollPane(this);
        //textSpace.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
        setBounds(window.BREAK * 2 +  window.getX() * window.FIELD_SIZE, window.BREAK, this.x, this.y);
    }
    
    void addAllComments()
    {
        List<String> com = window.getWorld().getKomentator().getKomentarze();
        for (String str : com)
        {
            if (i == 40)
            {
                setText(null);
                i = 0;
            }
            if (i == 0)
                setText(str);
            else
            {
                if (i%2 == 0)
                {
                append("\n");
                append(str);
                }
                else
                {
                    append("\t\t");
                    append(str);
                }
                
            }
            i++;
        }
        window.getWorld().getKomentator().usunKomentarze();
    }
}
