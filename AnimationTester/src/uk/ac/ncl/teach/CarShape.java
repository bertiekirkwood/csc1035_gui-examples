package uk.ac.ncl.teach;


import java.awt.*;
import java.awt.geom.*;

public class CarShape implements MoveableShape {
    private int x;
    private int y;
    private int width;

    public CarShape(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public void draw(Graphics2D g2) {
        Rectangle2D.Double body = new Rectangle2D.Double(x, y + width / 6,
                width - 1, width / 6);
        Ellipse2D.Double frontTyre = new Ellipse2D.Double(x + width / 6, y
                + width / 3, width / 6, width / 6);
        Ellipse2D.Double rearTyre = new Ellipse2D.Double(x + width * 2 / 3, y
                + width / 3, width / 6, width / 6);

        Point2D.Double r1 = new Point2D.Double(x + width / 6, y + width / 6);
        Point2D.Double r2 = new Point2D.Double(x + width / 3, y);
        Point2D.Double r3 = new Point2D.Double(x + width * 2 / 3, y);
        Point2D.Double r4 = new Point2D.Double(x + width * 5 / 6, y + width / 6);

        Line2D.Double frontWindscreen = new Line2D.Double(r1, r2);
        Line2D.Double rooftop = new Line2D.Double(r2, r3);
        Line2D.Double rearWindscreen = new Line2D.Double(r3, r4);

        g2.draw(body);
        g2.draw(frontTyre);
        g2.draw(rearTyre);
        g2.draw(frontWindscreen);
        g2.draw(rooftop);
        g2.draw(rearWindscreen);
    }

    public boolean contains(Point2D p) {
        return x <= p.getX() && p.getX() <= x + width && y <= p.getY()
                && p.getY() <= y + width / 2;
    }

    public void translate(int dx, int dy) {
        x += dx;
        y += dy;
    }
}
