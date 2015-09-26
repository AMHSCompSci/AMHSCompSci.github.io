import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.*;

public class ThreesPuzzle extends JApplet implements KeyListener
{
    // instance variables - replace the example below with your own
    private int x;
    int grid[][] = new int[4][4];
    
    Random entropy = new Random();
    
    public void init()
    {
        resize(getWidth(), (getWidth()/grid[0].length) * grid.length);
    }

    public void populate()
    {
        for(int y = 0; y < grid.length; y++)
        {
            for(int x = 0; x < grid[0].length; x++)
            {
                grid[y][x] = 0;
            }
        }
    }
    
    public void newNumber()
    {
        int ry, rx, tCyc = 0;
        do
        {
            ry = entropy.nextInt(grid.length);
            rx = entropy.nextInt(grid[0].length);
        } while(grid[ry][rx] != 0);
        
        switch(entropy.nextInt(5) + 1)
        {
            case 1: tCyc = 2; break;
            case 2: tCyc = 2; break;
            case 3: tCyc = 4; break;
            case 4: tCyc = 2; break;
        }
        
        grid[ry][rx] = tCyc;
    }
    
    public void move(char direction)
    {
        switch(direction)
        {
            case 'd' : for(int x = 0; x < grid[0].length; x++)
                       {
                           for(int y = grid.length - 2; y >= 0; y--)
                           {
                               if(grid[y+1][x] == 0)
                               {
                                  grid[y+1][x] = grid[y][x];
                                  grid[y][x] = 0;
                               }
                           }
                       }
            
            case 'r' :
            
            case 'u' :
            
            case 'l' : 
        }
    }
    
    /**
     * Called by the browser or applet viewer to inform this JApplet that it 
     * should start its execution. It is called after the init method and 
     * each time the JApplet is revisited in a Web page. 
     */
    public void start()
    {
        // provide any code requred to run each time 
        // web page is visited
    }

    public void stop()
    {
        // provide any code that needs to be run when page
        // is replaced by another page or before JApplet is destroyed 
    }

    public void paint(Graphics g)
    {
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawRect(0, 0, getWidth(), getHeight());
        g.
    }
    
    public void keyTyped(KeyEvent k) {}
    public void keyPressed(KeyEvent k) {}
    public void keyReleased(KeyEvent k) {}
    
    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * is being reclaimed and that it should destroy any resources that it
     * has allocated. The stop method will always be called before destroy. 
     */
    public void destroy()
    {
        // provide code to be run when JApplet is about to be destroyed.
    }


    /**
     * Returns information about this applet. 
     * An applet should override this method to return a String containing 
     * information about the author, version, and copyright of the JApplet.
     *
     * @return a String representation of information about this JApplet
     */
    public String getAppletInfo()
    {
        // provide information about the applet
        return "Title:   \nAuthor:   \nA simple applet example description. ";
    }


    /**
     * Returns parameter information about this JApplet. 
     * Returns information about the parameters than are understood by this JApplet.
     * An applet should override this method to return an array of Strings 
     * describing these parameters. 
     * Each element of the array should be a set of three Strings containing 
     * the name, the type, and a description.
     *
     * @return a String[] representation of parameter information about this JApplet
     */
    public String[][] getParameterInfo()
    {
        // provide parameter information about the applet
        String paramInfo[][] = {
                 {"firstParameter",    "1-10",    "description of first parameter"},
                 {"status", "boolean", "description of second parameter"},
                 {"images",   "url",     "description of third parameter"}
        };
        return paramInfo;
    }
}
