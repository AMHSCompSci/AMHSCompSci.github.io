public class Blip {
    private double x, y, v, theta;
    private int width, height;
    private boolean bounce = true;

    Blip() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        v = 0;
        theta = 0;
        bounce = false;

    }

    Blip(int x1, int y1, int _width, int _height) {
        x = x1 - ((double) _width / 2);
        y = y1 - ((double) _height / 2);
        width = _width;
        height = _height;
        v = 4;
        theta = -0.6;
        bounce = false;
    }

    public void reset() {
        x = 403;
        y = 450;
        v = 4;
        if (Breaker.lives == 2)
            theta = -0.7;
        if (Breaker.lives == 1)
            theta = -0.83;
        bounce = false;
    }

    public void bounce() {
        theta = -theta;
        bounce = true;
    }


    public void move() {
        x = x + v * Math.cos(theta);
        y = y + v * Math.sin(theta);
        if (getcenX() > Paddle.getX() && getcenX() < Paddle.getX() + Paddle.getWidth() && y + height >= Paddle.getY() && bounce == true) {
            theta = -theta;
            Breaker.bouncer = true;
            bounce = false;
        }
        if (x < 0 || x > 805 - width) {
            theta = Math.PI - theta;
        }
        if (y < 0) {
            theta = -theta;
        }

        /*
        for (int i = 0; i < Breaker.broken.size(); i++) {
            if (getcenX() >= Breaker.broken.get(i).getX() && getcenX()<=Breaker.broken.get(i).getX()+Breaker.broken.get(i).getWidth() && (y+height<=Breaker.broken.get(i).getY())) {
                theta = - theta;
                bounce = true;
                break;
            }
        }
        */

        if (y >= 600) {
            Breaker.lives--;
            reset();
        }
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getcenX() {
        return (int) x + width / 2;
    }

    public int getcenY() {
        return (int) y + height / 2;
    }

    public boolean getBounce() {
        return bounce;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
