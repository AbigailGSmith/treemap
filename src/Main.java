import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main extends JFrame {

    private final Vis contents;
    public String filePath;
    public File file;

    public Main() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,800);

        //lets initialize
        contents = new Vis();
        filePath = "/home/abigail";
        file = new File(filePath);

        //gets the full size of the root directory
        //Long size = file.length();
        //System.out.println(size);

        //panel
        JPanel panel = new JPanel();
        setContentPane(panel);
        setTitle("Tree map for " + filePath + " <3");
        var abigail = createMenu();
        setJMenuBar(abigail);
        setVisible(true);

        if (file.isFile()) {

            //load in properties of the file
            String type = file.getName().substring('.');
            Long mod = file.lastModified();
            String permissions;
            String path = file.toString();
            fileNode(type, mod, permissions, path);
        } else if (file.isDirectory()) {

            dirNode();
        }

    }

    //populates directory node
    public Node dirNode() {

    }

    //populates file node
    public Node fileNode() {

    }

    private JMenuBar createMenu() {

        JMenuBar mb = new JMenuBar();
        JMenu options = new JMenu("Options");

        //adding questions to the menu bar
        //options.add(reset);
        //mb.add(options);


        return mb;
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(Main::new);
    }
}
