import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JButton;

public class Main extends JFrame {

    private final Vis contents;
    public static String filePath;
    public static File file;
    ArrayList<Node> nose;

    public Main() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,800);


        //lets initialize
        contents = new Vis();
        //instantiate color scheme here?
        nose = new ArrayList<>();

        file = selectFile();

        //panel
        setContentPane(contents);
        setTitle("Tree map for " + filePath + " <3");
        var abigail = createMenu();
        setJMenuBar(abigail);
        setVisible(true);

        setFolder(file);
        contents.repaint();
    }

    public void setFolder(File f) {
        nose.clear();
        contents.setRootNode(dirNode(file));

        contents.setNose(nose);

    }

    //populates directory node
    public Node dirNode(File file) {

        int permissions = 0;

        if (file.canRead()) {

            permissions += 1;
        }
        if (file.canWrite()) {

            permissions += 2;
        }
        if (file.canExecute()) {

            permissions += 4;
        }

        //load in properties of the file
        String type = "";
        if (file.getName().contains(".")) {

            int i = file.getName().lastIndexOf('.');
            type = file.getName().substring(i);
        }
        Long mod = file.lastModified();
        String path = file.toString();
        Long length = 0l;

        ArrayList<Node> children = new ArrayList<>();
        File[] flies = file.listFiles();
        Path p = file.toPath();
        if (!Files.isSymbolicLink(p)) {
            if (flies != null) {
                for (File f : flies) {

                    Node n;

                    if (f.isFile()) {

                        n = fileNode(f);
                        children.add(n);
                        length += n.length;
                    } else if (f.isDirectory()) {

                        n = dirNode(f);
                        children.add(n);
                        length += n.length;

                    }

                }
            }
        }

        Node n = new Node(type, mod, permissions, path, length, children);
        nose.add(n);
        return n;
    }

    //populates file node
    public Node fileNode(File file) {

        int permissions = 0;

        if (file.canRead()) {

            permissions += 1;
        }
        if (file.canWrite()) {

            permissions += 2;
        }
        if (file.canExecute()) {

            permissions += 4;
        }

        //load in properties of the file

        String type = "";
        if (file.getName().contains(".")) {
            int i = file.getName().lastIndexOf('.');
            type = file.getName().substring(i);
        }
        Long mod = file.lastModified();
        System.out.println(mod);
        String path = file.toString();
        Long length = file.length();
        Node n = new Node(type, mod, permissions, path, length);
        nose.add(n);

        return n;
    }

    private JMenuBar createMenu() {

        JMenuBar mb = new JMenuBar();
        JMenu options = new JMenu("Options");


        JMenuItem ageScheme = new JMenuItem("Color by age"); //queries done :)
        ageScheme.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                contents.setColorScheme("age");
                contents.repaint();
            }
        });

        JMenuItem typeScheme = new JMenuItem("Color by file type"); //queries done :)
        typeScheme.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                contents.setColorScheme("type");
                contents.repaint();
            }
        });

        JMenuItem randomScheme = new JMenuItem("Random colors"); //queries done :)
        randomScheme.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                contents.setColorScheme("random");
                contents.repaint();
            }
        });

        JMenuItem noScheme = new JMenuItem("No colors"); //queries done :)
        noScheme.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                contents.setColorScheme("no");
                contents.repaint();
            }
        });

        JMenuItem reset = new JMenuItem("Select new directory"); //queries done :)
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectFile();
                contents.setRootNode(dirNode(file));
                contents.repaint();
            }
        });

        options.add(ageScheme);
        options.add(typeScheme);
        options.add(randomScheme);
        options.add(noScheme);
        options.add(reset);
        mb.add(options);
        return mb;
    }

    public File selectFile() {

        JButton open = new JButton();
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File("/home/abigail"));
        fc.setDialogTitle("Please select a directory.");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            // a formality

        }
        filePath = fc.getSelectedFile().getAbsolutePath();
        return new File(filePath);
    }

    public String setFilePath(String s) {

        file = new File(s);
        return "";
    }

    public static void main(String[] args) {


        javax.swing.SwingUtilities.invokeLater(Main::new);


    }
}
