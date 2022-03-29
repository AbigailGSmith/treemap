import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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

    public float drawNode(Graphics g, Node n, Long parentSize, float x, float y, float h, float w, String dir) {

        System.out.println("drawing " + n.path);
        System.out.println(x);
        System.out.println(y);

        if (n.fileType.equals("file")) {

            if (dir.equals("v")) {

                g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
                int i2 = (int) (w * n.length / parentSize);
                g.fillRect((int) x, (int) y, i2, (int) h);
                x = x + (w * (float) n.length / (float) parentSize);
                return x;
            } else {

                g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
                int h2 = (int) (h * n.length / parentSize);
                g.fillRect((int) x,(int) y,(int) w, h2 );
                y = y + (h * (float) n.length / (float) parentSize);
                return y;
            }
        } else {

            for (Node c : n.children) {


                if (dir.equals("v")) {
                    float ratio = (n.length == parentSize) ? w : w * c.length / parentSize;
                    x = drawNode(g, c, n.length, x, y, ratio, h, "h");
                } else {
                    float ratio = (n.length == parentSize) ? h : h * c.length / parentSize;
                    y = drawNode(g, c, n.length, x, y, w, ratio, "v");
                }
            }
        }

        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        drawNode(g,
                rootNode,
                rootNode.length,
                0,
                0,
                getWidth(),
                getHeight(),
                "v");
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

