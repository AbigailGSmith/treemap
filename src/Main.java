import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

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

        //panel
        setContentPane(contents);
        setTitle("Tree map for " + filePath + " <3");
        var abigail = createMenu();
        setJMenuBar(abigail);
        setVisible(true);

        contents.setRootNode(dirNode(file));
        contents.repaint();
    }

    //populates directory node
    public Node dirNode(File file) {
        System.out.println(file.getAbsolutePath());

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

        System.out.println(type);
        System.out.println(mod);
        System.out.println(permissions);
        System.out.println(path);
        System.out.println(length);


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

        return new Node(type, mod, permissions, path, length, children);
    }

    //populates file node
    public Node fileNode(File file) {
        System.out.println(file.getAbsolutePath());

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
            System.out.println(file.getName());
            int i = file.getName().lastIndexOf('.');
            type = file.getName().substring(i);
        }
        Long mod = file.lastModified();
        String path = file.toString();
        Long length = file.length();
        return new Node(type, mod, permissions, path, length);
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
