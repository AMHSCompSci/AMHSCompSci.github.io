import java.awt.*;
import java.applet.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.Image;
import java.util.Random;
public class Draw extends JApplet implements KeyListener, MouseListener, MouseMotionListener
{
    int m, n;
    int potato = 0;
    int rad;
    int w = getWidth();
    int h = getHeight();
    Color c;
    boolean splashScreen, instructionScreen, clearScreen;
    public void init()
    {
        setFocusable(true);
        resize(800, 600);

        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        m   = 100;
        n   = 100;
        rad = 10;

        clearScreen       = false;
        instructionScreen = false;
        splashScreen      = true;

        potato = 19; //Black colour by default
    }
 
    public void paint (Graphics g)
    {
        showStatus("hi" + potato + " " + m);

        if(clearScreen)
        {
            Dimension d = getSize();
            g.setColor(Color.white);
            g.fillRect(0, 0, d.width, d.height);
            clearScreen = !clearScreen; //Clears screen on one and only one cycle of paint.
        }

        if(instructionScreen)
        {
            g.setColor(Color.white);
            g.fillRect(40, 40, 150, 150);
            g.setColor(Color.black);
            g.drawString("1-red", 50, 50);
            g.drawString("2-orange", 50, 60);
            g.drawString("3-yellow", 50, 70);
            g.drawString("4-green", 50, 80);
            g.drawString("5-blue", 50, 90);
            g.drawString("6-black", 50, 100);
            g.drawString("0-eraser", 50, 110);
            g.drawString("q-decrease radius", 50, 120);
            g.drawString("w-increase radius", 50, 130);
            g.drawString("l(+click and drag)-clear screen", 50, 140);
            g.drawString("a-'house' rubber stamp", 50, 150);
            g.drawString("s-random color", 50, 160);
        }

        if (splashScreen)
        {   
            g.setColor(Color.red);
            g.drawString("Paint", 250, 250);
            g.drawString("by Shreyas", 250, 300);
            g.drawString("Press P to continue", 250, 350);
        }
        //g.fillRect(0, 0, w, h);
        if(potato == 1)
        {
            g.setColor(Color.red);
        }
        if(potato == 2)
        {
            g.setColor(Color.orange);
        }
        if(potato == 3)
        {
            g.setColor(Color.yellow);
        }
        if(potato == 4)
        {
            g.setColor(Color.green);
        }  
        if(potato == 19)
        {
            g.setColor(Color.black);
        }  
        if(potato == 5)
        {
            g.setColor(Color.blue);
        }
        if (potato == 0)
        {
            g.setColor(Color.white);
        }
        if (potato == 9)
        {
            g.fillOval(0, 0, 0, 0);
            g.drawLine(m, n, m, n + 100);
            g.drawLine(m, n, m + 100, n);
            g.drawLine(m, n + 100, m + 100, n + 100);
            g.drawLine(m + 100, n, m + 100, n + 100);
            //house triangle on top
            g.drawLine(m, n, m + 50, n - 50);
            g.drawLine(m + 100, n, m + 50, n - 50);
            //door
            g.drawLine(m + 25, n + 100, m + 25, n + 50);
            g.drawLine(m + 50, n + 100, m + 50, n + 50);
            g.drawLine(m + 25, n + 50, m + 50, n + 50);
        }
        if(potato == 11){
            Random rand = new Random();
            float r = rand.nextFloat();
            float gx = rand.nextFloat();
            float b = rand.nextFloat();
            Color randomColor = new Color(r, gx, b);
            g.setColor(randomColor);
        }

        g.fillOval(m,n,rad,rad);
    }
 
    public void keyPressed(KeyEvent e) {
 
    }
 
    public void keyReleased(KeyEvent e) {
 
    }
 
    public void keyTyped(KeyEvent e) {
        int tPotato;
        if(e.getKeyChar() == 'a')
        {
            potato  = 9;
        }
        if(e.getKeyChar() == '1')
        {
            potato = 1;
        }
        if(e.getKeyChar() =='2')
        {
            potato = 2;
        }
        if(e.getKeyChar() == '3')
        {
            potato = 3;
        }
        if(e.getKeyChar() == '4')
        {
            potato = 4;
        }
        if(e.getKeyChar() == '5')
        {
            potato = 5;
        }
        if(e.getKeyChar() == '6')
        {
            potato = 6;
        }
        if(e.getKeyChar() == '7')
        {
            potato = 7;
        }
        if(e.getKeyChar() == '0')
        {
            potato = 0;
        }
        if(e.getKeyChar() == 'q')
        {
            rad = rad - 5;
        }
        if(e.getKeyChar() == 'w')
        {
            rad = rad + 5;
        }
        if(e.getKeyChar() == 'l')
        {
            clearScreen       = true;
        }
        if(e.getKeyChar() == 's')
        {
            potato = 11;
        }
        if(e.getKeyChar() == 'p'){
            splashScreen      = false;
            clearScreen       = true;
            instructionScreen = true;
            repaint();
        }
    }
 
    public void mouseClicked(MouseEvent e) {}
 
    public void mousePressed(MouseEvent e) {
 
    }
 
    public void mouseReleased(MouseEvent e) {}
 
    public void mouseEntered(MouseEvent e) {}
 
    public void mouseExited(MouseEvent e) {}
 
    public void mouseDragged(MouseEvent e) {
        m = e.getX();
        n = e.getY();
        repaint();
    }
 
    public void mouseMoved(MouseEvent e) {
        m = e.getX();
    }
}