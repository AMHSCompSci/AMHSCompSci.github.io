import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: rahul
 * Date: 12/2/13
 * Time: 9:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class Brick {
    private int x, y, width, height;
    private Color c;

    Brick() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        c = new Color(0, 0, 0);
    }

    Brick(int _x, int _y, int _width, int _height) {
        x = _x;
        y = _y;
        width = _width;
        height = _height;
        c = new Color(0, 0, 0);
    }

    Brick(int _x, int _y, int _width, int _height, Color _c) {
        x = _x;
        y = _y;
        width = _width;
        height = _height;
        c = _c;
    }

    public void setX(int _x) {
        x = _x;
    }

    public void setY(int _y) {
        y = _y;
    }

    public void setWidth(int _width) {
        width = _width;
    }

    public void setHeight(int _height) {
        height = _height;
    }

    public void setColor(Color _c) {
        c = _c;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getcenX() {
        return x + width / 2;
    }

    public int getcenY() {
        return y + height / 2;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getC() {
        return c;
    }
}
