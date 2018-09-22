/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld.world.graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import virtualworld.world.Kierunek;
import virtualworld.world.ObslugaPliku;
import virtualworld.world.World;
import virtualworld.world.organizmy.Organizm;
import virtualworld.world.organizmy.zwierzeta.Czlowiek;

/**
 *
 * @author brink
 */
public class Window extends JFrame implements ActionListener 
{
    private final JFrame f;
    private final JPopupMenu menu;
    private final List<OrgField> organizmyList;
    private JButton bNext, bUm, bLoad, bSave;
    private Board board;
    private Commentary com;
    private final JButton pole[][];
    private final int x;
    private final int y;
    private int fieldX, fieldY;
    private final World world;
    private final ObslugaPliku obs;
    protected final int WINDOW_HEIGHT;
    protected final int WINDOW_WIDTH;
    protected static final int BREAK = 50;
    protected static final int FIELD_SIZE = 16;
    protected static final int BUTTON_WIDTH = 150;
    protected static final int BUTTON_HEIGHT = 40;
    
    public Window(int x, int y, World w)
    {
        obs = new ObslugaPliku();
        this.x = x;
        this.y = y;
        WINDOW_HEIGHT = 4 * BREAK + y * FIELD_SIZE;
        WINDOW_WIDTH = 700 + 4 * BREAK + x * FIELD_SIZE;
        world = w;
        pole = new JButton[y][x];
        menu = new JPopupMenu("Nowy organizm");
        //tworzenie okna
        f = new JFrame("Virtual World");
        f.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        f.setLayout(null);
        //tworzenie menu org
        organizmyList = new LinkedList<>();
        createOrgSubMenu();
        //tworzenie przyciskow
        //dodawanie przyciskow do okna
        createButtons();
        //okno komentarzy
        addCommentary();
        //obsluga zdarzen przyciskow
        addActionListeners();
        //plansza
        createBoard();
        //ustawienia okna
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   
        f.setVisible(true);
        //pocz komentarze
        com.addAllComments();
    }
    
    private void addActionListeners()
    {
        bNext.addActionListener(this);
        bUm.addActionListener(this);
        bSave.addActionListener(this);
        bLoad.addActionListener(this);
    }
    
    private void createBoard()
    {
        board = new Board(x, y, this);
        Organizm org;
        setIcons();
        f.add(board);
    }
    
    private void createOrgSubMenu()
    {
        for (Organizm org : world.getOrgPocz())
        {
            if (org.getClass() == Czlowiek.class)
                continue;
            OrgField nowy = new OrgField(org.getClass().getSimpleName(), org);
            organizmyList.add(nowy);
            menu.add(nowy);
            nowy.addActionListener(this);
        }
        f.add(menu);
    }
    
    private void createButtons()
    {
        bNext = new JButton("Następna tura");
        bNext.setBounds(BREAK, 2 * BREAK + y * FIELD_SIZE, BUTTON_WIDTH, BUTTON_HEIGHT);
        bUm = new JButton("Aktywuj umiejętność");
        bUm.setBounds(2 * BREAK + BUTTON_WIDTH, 2 * BREAK + y * FIELD_SIZE, BUTTON_WIDTH + 10, BUTTON_HEIGHT);
        bLoad = new JButton("Wczytaj grę");
        bLoad.setBounds(4 * BREAK + 3 * BUTTON_WIDTH, 2 * BREAK + y * FIELD_SIZE, BUTTON_WIDTH + 10, BUTTON_HEIGHT);
        bSave = new JButton("Zapisz grę");
        bSave.setBounds(3 * BREAK + 2 * BUTTON_WIDTH, 2 * BREAK + y * FIELD_SIZE, BUTTON_WIDTH + 10, BUTTON_HEIGHT);
        f.add(bNext);
        f.add(bUm);
        f.add(bLoad);
        f.add(bSave);
    }
    
    private void addCommentary()
    {
        
        com = new Commentary(2 * x * FIELD_SIZE, y * FIELD_SIZE, this);
        com.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                board.requestFocusInWindow();
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
                board.requestFocusInWindow();
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
                board.requestFocusInWindow();
            }
            @Override
            public void mouseEntered(MouseEvent e)
            {
                board.requestFocusInWindow();
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
                board.requestFocusInWindow();
            }
        });
        f.add(com);
    }
    
    private void setIcons()
    {
        Organizm org;
        for (int i = 0; i < y; i++)
        {
            for (int j = 0; j < x; j++)
            {
                pole[i][j] = new JButton();
                pole[i][j].setBounds(j*FIELD_SIZE, i*FIELD_SIZE, FIELD_SIZE, FIELD_SIZE);
                org = world.getOrganizm(j, i);
                if (org == null)
                    pole[i][j].setIcon(new ImageIcon ("C:\\vWorldImg\\empty.png"));
                else
                    pole[i][j].setIcon(org.getImage());
                board.add(pole[i][j]);
                pole[i][j].addActionListener(this);
            }
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    
    public Commentary getCommentary()
    {
        return com;
    }
    
    public World getWorld()
    {
        return world;
    }
    
    public void updateBoard()
    {
        Organizm org;
        for (int i = 0; i < y; i++)
        {
            for (int j = 0; j < x; j++)
            {
                org = world.getOrganizm(j, i);
                if (org == null)
                    pole[i][j].setIcon(new ImageIcon ("C:\\vWorldImg\\empty.png"));
                else
                    pole[i][j].setIcon(org.getImage());
            }
        }
        //do usuniecia-----
        /*
        System.out.print("############################\n");
        for (int i = 0; i < y; i++)
        {
            for (int j = 0; j < x; j++)
            {
                if (world.isXYfree(j, i))
                    System.out.print(" ");
                else
                    System.out.print("x");
            }
            System.out.print("\n");
        }
        System.out.print("############################\n");
        */
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (world.isGameInProgress())
        {
            if (source == bNext)
            {
                ((Czlowiek)world.getCzlowiek()).setKierunek(Kierunek.NIC);
                world.wykonajTure();
                com.addAllComments();
                updateBoard();
            }
            else if (source == bUm)
            {
                if (((Czlowiek)world.getCzlowiek()).aktywujUmiejetnosc())
                {
                    world.getKomentator().komentujAktywacjeUm();
                    com.addAllComments();
                }
            }
            else if (source == bSave)
            {
                if (obs.saveGame(world))
                    world.getKomentator().komentujZapis();
            }
            for (int i = 0; i < y; i++)
            {
                for (int j = 0; j < x; j++)
                {
                    if (source == pole[i][j] && world.getOrganizm(j, i) == null)
                    {
                        fieldX = j;
                        fieldY = i;
                        menu.show(f, j * FIELD_SIZE + BREAK, i * FIELD_SIZE + BREAK);
                    }
                }
            }
            for (OrgField item : organizmyList)
            {
                if (source == item)
                {
                    Organizm o = item.getOrg().makeNew(fieldX, fieldY, world.getTura(), world);
                    world.dodajOrganizm(o);
                    updateBoard();
                }
            }
                
                
        }
        if (source == bLoad)
        {
            if (obs.loadGame(world))
            {
                world.getKomentator().komentujWczytanie();
                //x = world.getX();
                //y = world.getY();
                //pole = new JButton[y][x];
                //setIcons();
                Window www = new Window(world.getX(), world.getY(), world);
                f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                //updateBoard();
            }
        }
        board.requestFocusInWindow();
    }
}
