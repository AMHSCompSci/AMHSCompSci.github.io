import java.awt.*;
import java.util.Random;

/**
 * Created by rahul on 12/8/13.
 */
public class Paddle {
    static Random r = new Random();
    private static int x, y, width, height;
    private static Color c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static int getcenX() {
        return x + width / 2;
    }

    public static int getcenY() {
        return y + height / 2;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static Color getColor() {
        return c;
    }

    public static void setX(int _x) {
        if (_x - (width / 2) >= 0 && _x + (width / 2) <= 805) {
            x = _x - (width / 2);
        }
    }

    public static void setY(int _y) {
        if (_y - (height / 2) >= 0 && _y + (height / 2) <= 600) {
            y = _y - (height / 2);
        }
    }

    public static void setHeight(int _height) {
        height = _height;
        setY(y);
    }

    public static void setWidth(int _width) {
        width = _width;
        setX(x);
    }

}
