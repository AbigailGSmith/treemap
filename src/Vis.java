import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Random;

public class Vis extends JPanel implements ActionListener, MouseInputListener {

    public static boolean collision;
    private Point mouseDown;
    private Node rootNode;
    Random r;

    public Vis() {

        super();

        addMouseListener(this);
        addMouseMotionListener(this);
        r = new Random();
    }

    public Point2D.Float drawNode(Graphics g, Node n, Long parentSize, Point2D.Float p, float h, float w, String dir) {
        Point2D.Float original = new Point2D.Float(p.x,p.y);
        System.out.println("drawing " + n.path);
        System.out.println(p.x);
        System.out.println(p.y);

        if (n.fileType.equals("file")) {

            if (dir.equals("v")) {

                g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
                g.fillRect((int) p.x, (int) p.y,(int) w, (int) h);
                p.x = p.x + w;
                return p;
            } else {

                g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));

                g.fillRect((int) p.x,(int) p.y,(int) w, (int) h );
                p.y = p.y + h;
                return p;
            }
        } else {

            for (Node c : n.children) {

                if (dir.equals("h")) {
                    float ratio = w * c.length / n.length;
                    p = drawNode(g, c, n.length, p, h, ratio, "v");
                } else {
                    float ratio = h * c.length / n.length;
                    p = drawNode(g, c, n.length, p, ratio, w, "h"); //swapped?
                }

            }

            if (dir.equals("h")) {

                return new Point2D.Float(original.x, original.y + h);
            } else {

                return new Point2D.Float(original.x + w, original.y);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        drawNode(g,
                rootNode,
                null,
                new Point2D.Float(0,0),
                getHeight(),
                getWidth(),
                "h");
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();
        mouseDown = new Point(x,y);
        boolean set = false;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        repaint();
    }

    public void checkCollision(int x, int y) {

        collision = false;
    }

    //adding tool tips to each point
    @Override
    public void mouseMoved(MouseEvent e) {


        //repaint();

        //TODO draw tooltip

    }

    public void reset() {

    }

    public void setRootNode(Node n) {
        rootNode = n;
    }
}

