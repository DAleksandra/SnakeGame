import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gamepanel extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 500, HEIGHT = 500;
    private int last_key = 1;
    private Thread thread;
    private boolean running = false;
    private BodyPart b;
    private ArrayList<BodyPart> snake;
    private int xCoor = 10, yCoor = 10, size = 5;
    private int ticks = 0;
    private boolean right = true, left = false, up = false, down = false, space = false;
    private Apple apple;
    private ArrayList<Apple> apples;
    private Random r;

    public Gamepanel(){
        setFocusable(true);
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        snake = new ArrayList<BodyPart>();
        apples = new ArrayList<Apple>();
        r = new Random();
        Object[] options = {"OK"};

                JOptionPane.showOptionDialog(this,
                        "It's snake! Click OK when yo're ready to start the game. ","Snake",
                        JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options[0]);
        start();
    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();

    }
    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e ) {
            e.printStackTrace();
        }
    }
    public void tick() {
        if (snake.size() == 0) {
            b = new BodyPart(xCoor, yCoor, 10);
            snake.add(b);
        }
        if(apples.size() == 0) {
            int xCoor = r.nextInt(50);
            int yCoor = r.nextInt(50);

            apple = new Apple(xCoor, yCoor, 10);
            apples.add(apple);
        }

        for(int i = 0; i < apples.size(); i++) {
            if(xCoor == apples.get(i).getxCoor() &&
                    yCoor == apples.get(i).getyCoor()) {
                size++;
                apples.remove(i);
                i++;
            }
        }

        for(int i =0; i < snake.size(); i++) {
            if(xCoor == snake.get(i).getxCoor() &&
                    yCoor == snake.get(i).getyCoor()) {
                if(i != snake.size() - 1) {
                    System.out.println("Game over");
                    stop();
                }
            }
        }
        if(xCoor < 0 || xCoor > 49 || yCoor < 0 || yCoor > 49) {
            System.out.println("Game over");
            stop();
        }


        ticks++;

        if(ticks > 250000) {
            if(right) xCoor++;
            if(left) xCoor--;
            if(up) yCoor--;
            if(down) yCoor++;

            ticks = 0;

            b = new BodyPart(xCoor, yCoor, 10);
            snake.add(b);

            if(snake.size() > size) {
                snake.remove(0);
            }
            last_key = 1;
        }
    }
    public void paint(Graphics g) {
        g.clearRect(0,0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0,0, WIDTH, HEIGHT);
        for (int i = 0; i < WIDTH/10; i++) {
            g.drawLine(i * 10, 0, i*10, HEIGHT );
        }
        for (int i = 0; i < HEIGHT/10; i++) {
            g.drawLine(0, i*10, HEIGHT, i*10 );
        }
        for(int i = 0; i < snake.size(); i++) {
            snake.get(i).draw(g);
        }
        for(int i = 0; i < apples.size(); i++) {
            apples.get(i).draw(g);
        }
    }

    public void run() {

        while(running) {

        if(space == true)
        {
            try{
                thread.sleep(1000);
            }catch (InterruptedException e ) {
                e.printStackTrace();
            }

        }
                tick();
                repaint();
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT && !left && last_key == 1) {
            up = false;
            down = false;
            right = true;
            last_key = 0;
        }
        if(key == KeyEvent.VK_LEFT && !right && last_key == 1) {
            up = false;
            down = false;
            left = true;
            last_key = 0;
        }
        if(key == KeyEvent.VK_UP && !down && last_key == 1) {
            left = false;
            right = false;
            up = true;
            last_key = 0;
        }
        if(key == KeyEvent.VK_DOWN && !up && last_key == 1) {
            left = false;
            right = false;
            down = true;
            last_key = 0;
        }
        if(key == KeyEvent.VK_SPACE ) {
            space = !(space);

        }

    }

    public void keyReleased(KeyEvent arg0) {
    }
    public void keyTyped(KeyEvent arg0) {
    }
}
