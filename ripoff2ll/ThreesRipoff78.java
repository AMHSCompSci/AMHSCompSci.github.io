import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ThreesRipoff78 extends Applet implements KeyListener, MouseListener
{
    int grid [][] = new int[4][4];
    int gridSave [][] = new int[4][4];
    int gappx = 30;
    int xoffset = 15, yoffset = 115, ybumper = 100, ytop = 100;
    Random entropy = new Random();
    
    Font f;
    Font tempF;
    int count = 0;
    boolean splashStart = true;
    boolean splash2 = false;
    boolean full = false;
    boolean endGame = false;
    boolean debug = false;
    boolean puzzle = false;
    
    int score = 0;
    int stuffOnBoard = 0;
    String generalStats = "";
    
    String startMessage = "Press Any Key to Start...";
    String start2 = "Use WASD to play.";
    String start3 = "Press space to continue.";
    String end = "You lose. Press any key to restart.";
    
    public void init()
    {
        resize(getWidth(), ((getHeight()/grid[0].length)*grid.length));
        //splashStarter();
        newGrid();
        addBlock();
        addBlock();
        full = false;
        endGame = false;
        debug = false;
        puzzle = false;
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        
//      try { FileInputStream odsjvn = new FileInputStream("hv.tff");
//          //piece = Font.createFont(Font.TRUETYPE_FONT, new File("hv.tff"));
//      } 
//      catch (FileNotFoundException e) {System.out.println("you done goofed. Trice.");}
//      //catch (FontFormatException e) {System.out.println("you done goofed.");} 
//      catch (IOException e) {System.out.println("you done goofed. Twice.");}
//      
//      piece = piece.deriveFont(36f);
        try { f = Font.createFont( Font.TRUETYPE_FONT, new FileInputStream("font.ttf") );
              f = f.deriveFont(Font.BOLD, 62);
        }
        catch (FileNotFoundException e) {count = 1010101010;}
        catch (FontFormatException e) {count = 1337;}
        catch (IOException e) {count = 888888;}
        score = 0;
        stuffOnBoard = 0;
        generalStats = "";
    }
    
    public void paint(Graphics g)
    {
        resize(600, 600 + ytop);
        g.setFont(f);
        g.clearRect(0,  0 , getWidth(), getHeight());
        g.setColor(new Color(250,248,239));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(new Color(188, 173, 159));
        g.fillRoundRect(xoffset, yoffset, getWidth() - gappx, getHeight() - (yoffset + xoffset), 12, 12);
        //Draw blocks
        for(int y = 0; y < grid.length; y++)
        {
            for(int x = 0; x < grid[0].length; x++)
            {
                g.setColor(Color.cyan); //Failsafe
                switch(grid[y][x])
                {
                    case 0: g.setColor(new Color(205, 192, 178));
                            break;
                    
                    case 2: g.setColor(new Color(239, 228, 218));
                            break;
                            
                    case 4: g.setColor(new Color(238, 223, 199));
                            break;
                    
                    case 8: g.setColor(new Color(246, 173, 117));
                            break;
                    
                    case 16:g.setColor(new Color(249, 150, 94));
                            break;
                             
                    case 32:g.setColor(new Color(250, 127, 91));
                            break;
                    
                    case 64:g.setColor(new Color(255, 96, 59));
                            break;
                            
                    case 128:g.setColor(new Color(242, 205, 108));
                            break;
                            
                    case 256:g.setColor(new Color(242, 201, 89));
                            break;
                            
                    case 512:g.setColor(new Color(243, 197, 69));
                            break;
                            
                    case 1024:g.setColor(new Color(243, 194, 47));
                            break;
                    
                    case 2048:g.setColor(new Color(237, 194, 46));
                            break;
                }
                
                g.fillRoundRect(xoffset + x * ((getWidth() - gappx)/grid[0].length) + 9, yoffset + y * ((getHeight() - gappx - ybumper)/grid.length) + 9, (getWidth() - gappx) / grid.length - 15, (getHeight() - gappx - ybumper) / grid[0].length - 15, (getWidth() - gappx) / grid.length / 12, (getHeight() - gappx - ybumper) / grid[0].length / 12);
                
                if(grid[y][x] != 0)
                {
                    if(grid[y][x] == 2 || grid[y][x] == 4)
                    {
                        g.setColor(new Color(120, 110, 101)); //Black (2-4)
                        f = f.deriveFont(Font.BOLD, 62);
                    }
                    if(grid[y][x] == 1024 || grid[y][x] == 2048)
                    {
                        tempF = f;
                        f = f.deriveFont(Font.BOLD, 48);
                        generalStats += "smallFont";
                    }
                    if(grid[y][x] == 8 || grid[y][x] == 16 || grid[y][x] == 32 || grid[y][x] == 64 || grid[y][x] == 128 || grid[y][x] == 256 || grid[y][x] == 512 || grid[y][x] == 1024 || grid[y][x] == 2048)
                        g.setColor(new Color(249, 246, 242)); //White (8-32)
                    String temp = grid[y][x] + "";
                    g.drawString(temp, xoffset + (((getWidth() - gappx) / grid.length * x) + ((getWidth() - gappx) / grid.length / 2) - (g.getFontMetrics().stringWidth(temp) / 2)), yoffset + ((getHeight() - gappx - ybumper)/ grid[0].length * y) + ((getHeight() - gappx - ybumper)/ grid[0].length / 2) + (g.getFontMetrics().getHeight() / 4));
                    if(grid[y][x] == 1024 || grid[y][x] == 2048)
                    {
                        f = tempF;
                    }
                }
            }
        }
        
        if(splashStart)
        {    
            g.setColor(new Color(0f, 0f, 0f, .73f));
            g.fillRoundRect(xoffset, yoffset, getWidth() - gappx, getHeight() - (yoffset + xoffset), 12, 12);
            f = f.deriveFont(Font.BOLD, 44);
            g.setColor(new Color(249, 246, 242));
            g.drawString(startMessage, (getWidth() / 2) - (g.getFontMetrics().stringWidth(startMessage) / 2), (getHeight() / 2) + (g.getFontMetrics().getHeight() / 4));
        }
        
        if(splash2)
        {    
            g.setColor(new Color(0f, 0f, 0f, .53f));
            g.fillRoundRect(xoffset, yoffset, getWidth() - gappx, getHeight() - (yoffset + xoffset), 12, 12);
            f = f.deriveFont(Font.BOLD, 24);
            g.setColor(new Color(249, 246, 242));
            g.drawString(start2, (getWidth() / 2) - (g.getFontMetrics().stringWidth(start2) / 2), (getHeight() / 2) + (g.getFontMetrics().getHeight() / 4));
            f = f.deriveFont(Font.BOLD, 44);
            g.drawString(start3, (getWidth() / 2) - (g.getFontMetrics().stringWidth(start3) / 2), (getHeight() / 2) + (getHeight() / 4) + (g.getFontMetrics().getHeight() / 4));
        }
        
        if(endGame)
        {    
            g.setColor(new Color(0f, 0f, 0f, .94f));
            g.fillRoundRect(xoffset, yoffset, getWidth() - gappx, getHeight() - (yoffset + xoffset), 12, 12);
            f = f.deriveFont(Font.BOLD, 44);
            g.setColor(new Color(249, 246, 242));
            g.drawString(end, (getWidth() / 2) - (g.getFontMetrics().stringWidth(end) / 2), (getHeight() / 2) + (g.getFontMetrics().getHeight() / 4));
        }
        
        showStatus("Score: " + score + "; Stuff on Board: " + stuffOnBoard + "; Game Over. Press n to start over." + " " + generalStats);
    }
    
//  public void fillRoundRect(int x, int y, int xysize, Graphics g)
//  {
//      g.fillOval(x, y, xysize/6, xysize/6);
//      g.fillOval((x * (xysize*(5/6))), y, xysize/6, xysize/6);
//      g.fillOval(x, (y * (xysize* (5/6))), xysize/6, xysize/6);
//      g.fillOval((x * (xysize*(5/6))), (xysize* (5/6)), xysize/6, xysize/6);
//      
//      g.fillRect((x + (xysize * (1/12))), y, xysize * (5/6), xysize * (1/12));
//      g.fillRect((x + (xysize * (1/12))), y * (5/6), xysize * (1/6), xysize * (1/12));
//      g.fillRect(x, y + (xysize * (1/12)), xysize, xysize * (1/6));
//  }
    
    public void splashStarter()
    {
        splashStart = true;
    }
    
    public boolean moveBlock(char direction)
    {   
        if(checkFull())
            full = false;
        
        for(int y = 0; y < grid.length; y++)
        {
            for(int x = 0; x < grid[0].length; x++)
            {
                gridSave[y][x] = grid[y][x];
            }
        }
        
        switch(direction)
        {
            case 'u': for(int x = 0; x < grid[0].length; x++)
                      {
                        for(int c = 0; c < grid.length - 1; c++)
                        {
                            for(int y = 1; y < grid.length - c; y++) //for(int x = 1; x < grid[0].length - c; x++)
                            {
                                if(grid[y-1][x] == 0 && full == false)
                                {
                                    grid[y-1][x] = grid[y][x];
                                    grid[y][x] = 0;
                                }
                            }
                        }
                      }
                      for(int x = 0; x < grid[0].length; x++)
                      {
                        for(int y = 0; y < grid.length - 1; y++)
                        {
                            if(grid[y][x] == grid[y+1][x] && grid[y][x] != 0)
                            {
                                grid[y][x] *= 2;
                                score += grid[y][x];
                                for(int c = y + 1; c < grid.length - 1; c++)
                                {
                                    grid[c][x] = grid[c+1][x];
                                }
                                grid[grid.length - 1][x] = 0;
                            }
                        }
                      }
                      break;
            case 'd': for(int x = 0; x < grid[0].length; x++)
                      {
                        for(int c = 0; c < grid.length - 1; c++)
                        {
                            for(int y = 0; y < grid.length - 1 - c; y++)
                            {
                                if(grid[y+1][x] == 0 && full == false)
                                {
                                    grid[y+1][x] = grid[y][x];
                                    grid[y][x] = 0;
                                }
                            }
                        }
                      }
                      for(int x = 0; x < grid[0].length; x++)
                      {
                        for(int y = grid.length - 1; y > 0; y--)
                        {
                            if(grid[y][x] == grid[y-1][x] && grid[y][x] != 0)
                            {
                                grid[y][x] *= 2;
                                score += grid[y][x];
                                for(int c = y - 1; c > 0; c--)
                                {
                                    grid[c][x] = grid[c-1][x];
                                }
                                grid[0][x] = 0;
                            }
                        }
                      }
                      break;
            case 'l': for(int y = 0; y < grid.length; y++)
                      {
                        for(int c = 0; c < grid.length - 1; c++)
                        {
                            for(int x = 1; x < grid[0].length - c; x++)
                            {
                                if(grid[y][x-1] == 0 && full == false)
                                {
                                    grid[y][x-1] = grid[y][x];
                                    grid[y][x] = 0;
                                }
                            }
                        }
                      }
                      for(int y = 0; y < grid.length; y++)
                      {
                        for(int x = 0; x < grid[0].length - 1; x++)
                        {
                            if(grid[y][x] == grid[y][x+1] && grid[y][x] != 0)
                            {
                                grid[y][x] *= 2;
                                score += grid[y][x];
                                for(int c = x + 1; c < grid[0].length - 1; c++)
                                {
                                    grid[y][c] = grid[y][c+1];
                                }
                                grid[y][grid[0].length - 1] = 0;
                            }
                        }
                      }
                      break;
            case 'r': for(int y = 0; y < grid.length; y++)
                      {
                        for(int c = 0; c < grid.length - 1; c++)
                        {
                            for(int x = 0; x < grid[0].length - 1 - c; x++)
                            {
                                if(grid[y][x+1] == 0 && full == false)
                                {
                                    grid[y][x+1] = grid[y][x];
                                    grid[y][x] = 0;
                                }
                            }
                        }
                      }
                      for(int y = 0; y < grid.length; y++)
                      {
                        for(int x = grid[0].length - 1; x > 0; x--)
                        {
                            if(grid[y][x] == grid[y][x-1] && grid[y][x] != 0)
                            {
                                grid[y][x] *= 2;
                                score += grid[y][x];
                                for(int c = x - 1; c > 0; c--)
                                {
                                    grid[y][c] = grid[y][c-1];
                                }
                                grid[y][0] = 0;
                            }
                        }
                      }
                      break;
        }
        
        if(full == true)
        {
            if(checkFull() == true)
            {
                endGame = true;
                gameOver();
                return false;
            }
            if(checkFull() == false)
            {
                full = false;
            }
        }
        
        for(int y = 0; y < grid.length; y++)
        {
            for(int x = 0; x < grid[0].length; x++)
            {
                if(grid[y][x] != gridSave[y][x])
                    return true;
            }
        }
        
        return false;
    }
    
    public void gameOver()
    {
        generalStats += "Game Over.";
        endGame = true;
    }
    
    public boolean checkFull()
    {
        for(int y = 0; y < grid.length; y++)
        {
            for(int x = 0; x < grid[0].length; x++)
            {
                if(grid[y][x] == 0)
                    return false;
            }
        }
        
        return true;
    }
    
    public void addBlock()
    {
        if(puzzle == false)
        {
            int tx = 0, ty = 0;
            do 
            {
                tx = entropy.nextInt(grid[0].length);
                ty = entropy.nextInt(grid.length);  
            }while(grid[ty][tx] != 0);
            
            switch(entropy.nextInt(4))
            {
                case 0: grid[ty][tx] = 2; break;
                case 1: grid[ty][tx] = 2; break;
                case 2: grid[ty][tx] = 2; break;
                case 3: grid[ty][tx] = 4; break;  
            }
            stuffOnBoard += grid[ty][tx];
        }
    }
    
    public void newGrid()
    {
        for(int y = 0; y < grid.length; y++)
        {
            for(int x = 0; x < grid[0].length; x++)
            {
               if(debug == true)
               {
                    if(x == 1)
                        grid[y][x] = 2048;
                    if(x == 0)
                        grid[y][x] = 0;
                    if(x == 2)
                        grid[y][x] = 1024;
                    if(x == 3)
                        grid[y][x] = 512;
               }
               if(debug == false)
                    grid[y][x] = 0;
            }
        }
    }

    public void keyPressed(KeyEvent e) 
    {
        
    }

    public void keyReleased(KeyEvent e) 
    {
        
    }

    public void keyTyped(KeyEvent e) 
    {
        if(splashStart)
        {
            splashStart = !splashStart;
            for(int h = 0; h < 1000000; h++)
            {
                splash2 = false;
            }
            splash2 = true;
        }
        if(splash2 && e.getKeyChar() == ' ')
        {
            splash2 = !splash2;
        }
        if(endGame)
        {
            init();
        }
        if(splashStart)
        {
            splashStart = !splashStart;
        }
        if(!splashStart && ! splash2 && !endGame)
        {
            if(e.getKeyChar() == 'w')
            {
                if(moveBlock('u'))
                    addBlock();
            }
            if(e.getKeyChar() == 's')
            {
                if(moveBlock('d'))
                    addBlock();
            }
            if(e.getKeyChar() == 'a')
            {
                if(moveBlock('l'))
                    addBlock();
            }
            if(e.getKeyChar() == 'd')
            {
                if(moveBlock('r'))
                    addBlock();
            }
            
            if(e.getKeyChar() == 'p')
            {
                for(int x = 0; x < 10; x++)
                {
                    if(moveBlock('u'))
                    addBlock();
                    repaint();
                    if(moveBlock('d'))
                    addBlock();
                    repaint();
                    if(moveBlock('l'))
                    addBlock();
                    repaint();
                    if(moveBlock('r'))
                    addBlock();
                    repaint();
                }
            }
            
            if(e.getKeyChar() == '~')
            {
                puzzle = !puzzle;
            }
            
            if(e.getKeyChar() == '_')
            {
                f = f.deriveFont(Font.BOLD, 48);
            }
            if(e.getKeyChar() == '+')
            {
                f = f.deriveFont(Font.BOLD, 72);
            }
            if(e.getKeyChar() == '0')
            {
                f = f.deriveFont(Font.BOLD, 62);
            }
            
            if(e.getKeyChar() == 'n')
            {
                init();
            }
        }
        repaint();
    }

    
    public void mouseClicked(MouseEvent e) {
        
    }

    
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}
//Ideas: No Death Mode
