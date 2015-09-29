import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: rahul
 * Date: 12/2/13
 * Time: 10:12 AM
 * To change this template use File | Settings | File Templates.
 */

public class Breaker extends Applet implements Runnable, KeyListener {

    ArrayList<Brick> broken = new ArrayList<Brick>();
    ArrayList<Blip> ball = new ArrayList<Blip>();
    Color[] c = new Color[10];
    Random r = new Random();
    Thread t = new Thread(this);
    AudioClip gameOver, bricky;
    AudioClip bouncy;
    public static boolean bouncer = false;
    int time = 25;
    int ycon = 185;
    boolean start = true;
    boolean game = false;
    public static boolean redraw = false;
    public static int lives = 3;
    int[] x = new int[10];

    public void init() {
        setFocusable(true);
        resize(805, 600);
        addKeyListener(this);
        Paddle.setX(403);
        Paddle.setY(570);
        Paddle.setWidth(40);
        Paddle.setHeight(20);
        for (int i = 0; i < x.length; i++)
            x[i] = 5;
        for (int i = 0; i < c.length; i++)
            c[i] = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 32; i++) {
                broken.add(new Brick(x[j], ycon, 20, 15, c[j]));
                x[j] += 25;
            }
            ball.add(new Blip(403, 450, 8, 8));
            ycon -= 20;
        }
        gameOver = getAudioClip(getClass().getResource("/gameover.wav"));
        bricky = getAudioClip(getClass().getResource("/beep-08.wav"));
        bouncy = getAudioClip(getClass().getResource("/beep-07.wav"));
    }

    public void paint(Graphics g) {
        start(g);
        if (bouncer == true) {
            bouncy.play();
            bouncer = false;
        }
        if (game == true) {
            babu(g);
            showStatus("Lives: " + lives);
        }
        if (lives == 0 || broken.size() == 0) {
            game = false;
            reset(g);
            gameOver.play();
        }
    }

    public void run() {
        while (true) {
            try {
                ball.get(0).move();
                repaint();
                t.sleep(15);
                for (int i = 0; i < broken.size(); i++) {
                    if (ball.get(0).getBounce() == false && ball.get(0).getcenX() > broken.get(i).getcenX() && ball.get(0).getcenX() < broken.get(i).getcenX() + 20
                            && ball.get(0).getcenY() <= broken.get(i).getcenY() + 15 && ball.get(0).getcenY() >= broken.get(i).getcenY()) {
                        ball.get(0).bounce();
                        broken.remove(i);
                        bricky.play();
                        System.out.println("Good job");
                    }

                }
                repaint();

            } catch (InterruptedException ex) {}
        }

    }

    //update() method from StackOverflow
    //URL: http://stackoverflow.com/questions/10508042/how-do-you-double-buffer-in-java-for-a-game
    public void update(Graphics g) {
        Graphics offgc;
        Image offscreen = null;
        Dimension d = size();

        // create the offscreen buffer and associated Graphics
        offscreen = createImage(d.width, d.height);
        offgc = offscreen.getGraphics();
        // clear the exposed area
        offgc.setColor(getBackground());
        offgc.fillRect(0, 0, d.width, d.height);
        offgc.setColor(getForeground());
        // do normal redraw
        paint(offgc);
        // transfer offscreen to window
        g.drawImage(offscreen, 0, 0, this);
    }

    public void drawBall(Blip b, Graphics g) {
        g.fillOval(b.getX(), b.getY(), b.getWidth(), b.getHeight());
    }

    public void drawBrick(Brick b, Graphics g) {
        g.setColor(b.getC());
        g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
    }

    public void drawPaddle(Graphics g) {
        g.setColor(Paddle.getColor());
        g.fillRect(Paddle.getX(), Paddle.getY(), Paddle.getWidth(), Paddle.getHeight());
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (game == true) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    Paddle.setX(Paddle.getcenX() + 10);
                    repaint();
                    break;
                case KeyEvent.VK_LEFT:
                    Paddle.setX(Paddle.getcenX() - 10);
                    repaint();
                    break;
                case KeyEvent.VK_ENTER:
                    while (broken.size() > 0)
                        broken.remove(0);
                    break;
            }
        }
        if (start == true) {
            switch (e.getKeyChar()) {
                case 'Y':
                    game = true;
                    start = false;
                    t.start();
                    break;
                case 'N':
                    if (game == false)
                        System.exit(0);
                    break;
            }
        }

        if (lives == 0) {
            switch (e.getKeyChar()) {
                case 'A':
                    lives = 3;
                    game = true;
                    break;
                case 'T':
                    if (game == false)
                        System.exit(0);
                    break;
            }
        }

    }

    public void keyReleased(KeyEvent e) {

    }

    public void start(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 805, 600);
        g.setColor(Color.black);
        g.drawString("Press 'Y' to start, and 'N' to self-destruct the Babu Shell.", 200, 250);
    }

    public void babu(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 805, 600);
        g.setColor(Color.black);
        drawPaddle(g);
        for (int i = 0; i < broken.size(); i++) {
            drawBrick(broken.get(i), g);
        }
        drawBall(ball.get(0), g);
    }

    public void reset(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 805, 600);
        g.setColor(Color.black);
        g.drawString("To re-activate the Babu Shell, press 'A' for activate.", 200, 250);
        g.drawString("To close the Babu Shell, press 'T' for terminate.", 200, 260);
    }


}
