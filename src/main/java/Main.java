import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Main {
   public static Color snake_color = Color.CYAN;
    public Main() {
        JFrame frame = new JFrame();
        Gamepanel gamepanel = new Gamepanel();

        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;
        menuBar = new JMenuBar();

        menu = new JMenu("Option");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);
        //a group of JMenuItems
        menuItem = new JMenuItem("A text-only menu item",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menu.add(menuItem);

//a submenu
        menu.addSeparator();
        submenu = new JMenu("Change snake color");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("RED");
        menuItem.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent ev) {
                                          snake_color = Color.RED ;
                                       }
                                   });
        submenu.add(menuItem);
        menuItem = new JMenuItem("BLUE");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                snake_color = Color.BLUE ;
            }
        });
        submenu.add(menuItem);
        menuItem = new JMenuItem("CYAN");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                snake_color = Color.CYAN ;
            }
        });
        submenu.add(menuItem);
        menuItem = new JMenuItem("YELLOW");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                snake_color = Color.YELLOW ;
            }
        });
        submenu.add(menuItem);
        menu.add(submenu);


        frame.add(gamepanel);
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake");
        frame.setLocationRelativeTo(null);

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


    }



    public static void main(String[] args){

        new Main();
    }
}
