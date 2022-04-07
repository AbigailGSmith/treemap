import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class Vis extends JPanel implements ActionListener, MouseInputListener {

    public static boolean collision;
    private Point mouseDown;
    private Node rootNode;
    Random r;
    String colorscheme;
    Long curTime;
    ArrayList<Node> nose;

    public Vis() {

        super();
        nose = new ArrayList<>();


        curTime = System.currentTimeMillis();
        setColorScheme("age");

        addMouseListener(this);
        addMouseMotionListener(this);
        r = new Random();
    }

    public Point2D.Float drawNode(Graphics g, Node n, Long parentSize, Point2D.Float p, float h, float w, String dir) {
        Point2D.Float original = new Point2D.Float(p.x,p.y);

        Color m = Color.WHITE;
        if (colorscheme.equals("type")) {

            if (n.type.equals(".odt") || n.type.equals(".pdf")) { //documents

                m = Color.RED;
            } else if (n.type.equals(".ods") || n.type.equals(".xls") || n.type.equals(".xlsx")) { //spreadsheets

                m = Color.ORANGE;
            } else if (n.type.equals(".ppt") || n.type.equals(".pptx") || n.type.equals(".odp")) { //slideshows

                m = Color.YELLOW;
            } else if (n.type.equals(".txt")) { //plaintext

                m = Color.GREEN;
            } else if (n.type.equals(".exe") || n.type.equals(".bat") || n.type.equals(".com") || n.type.equals(".inf") || n.type.equals(".ipa") || n.type.equals(".osx")) { //executables

                m = Color.BLUE;
            } else if (n.type.equals(".class")) { //source code

                m = Color.CYAN;
            } else if (n.type.equals(".obj") || n.type.equals(".o")) { //object code

                m = Color.MAGENTA;
            } else if (n.type.equals(".JPG") || n.type.equals(".png") || n.type.equals(".jpeg")) { //images

                m = Color.PINK;
            } else if (n.type.equals(".mp3") || n.type.equals(".wav") || n.type.equals(".wma")) { //audio

                m = Color.LIGHT_GRAY;
            }
        } else if (colorscheme.equals("age")) {

            Long fileAge = curTime - n.mod;

            if (fileAge < 3600*1000) {
                m = Color.BLUE;
            }
            else if (fileAge < 86400*1000) {
                m = Color.GREEN;
            }
            else if (fileAge < 604800*1000) {
                m = Color.PINK;
            }
            else {
                m = Color.WHITE;
            }

        } else if (colorscheme.equals("random")) {

            int red = r.nextInt(256);
            int green = r.nextInt(256);
            int blue = r.nextInt(256);
            m = new Color(red, green, blue);
        } else {

            m = Color.WHITE;
        }

        if (n.fileType.equals("file")) {

            if (dir.equals("v")) {

                g.setColor(m);
                g.fillRect((int) p.x, (int) p.y,(int) w, (int) h);
                g.setColor(Color.BLACK);
                g.drawRect((int) p.x, (int) p.y,(int) w, (int) h);
                p.x = p.x + w;
                n.setCoords((int) p.x, (int) p.y,(int) w, (int) h);
                return p;
            } else {

                g.setColor(m);
                g.fillRect((int) p.x,(int) p.y,(int) w, (int) h);
                g.setColor(Color.BLACK);
                g.drawRect((int) p.x,(int) p.y,(int) w, (int) h);
                p.y = p.y + h;
                n.setCoords((int) p.x, (int) p.y,(int) w, (int) h);
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

        for (Node n : nose) {

            if (n.collides(x, y)) {

                setToolTipText(n.toolTipText());
            }
        }
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



    //adding tool tips to each point
    @Override
    public void mouseMoved(MouseEvent e) {

        for (Node n : nose) {

            if (n.collides(e.getX(), e.getY())) {

                setToolTipText(n.toolTipText());
            }
        }


    }

    public void reset() {

    }

    public void setRootNode(Node n) {
        rootNode = n;
    }

    public void setColorScheme(String s) {
        colorscheme = s;
    }

    public void setNose(ArrayList<Node> n) {

        nose = n;
    }

}

