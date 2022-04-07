import java.awt.*;
import java.util.ArrayList;

public class Node {

    String type;
    String fileType;
    Long mod;
    int permissions;
    String path;
    Long length;
    ArrayList<Node> children;
    Rectangle getRect;


    //file node constructor
    public Node(String type, Long mod, int permissions, String path, Long length) {
        getRect = new Rectangle(0,0,0,0);
        this.children = new ArrayList<>();
        this.type = type;
        this.mod = mod;
        this.permissions = permissions;
        this.path = path;
        this.length = length;
        this.fileType = "file";
    }


    //dir node constructor
    public Node(String type, Long mod, int permissions, String path, Long length, ArrayList<Node> children) {
        getRect = new Rectangle(0,0,0,0);
        this.type = type;
        this.mod = mod;
        this.permissions = permissions;
        this.path = path;
        this.length = length;
        this.children = children;
        this.fileType = "dir";
    }

    //getter and setter for where the rectangle was drawn for a specific node
    public void setCoords(int x, int y, int w, int h) {

        getRect = new Rectangle(x, y, w, h);
    }

    public boolean collides(int x, int y) {

        return getRect.contains(x, y);
    }

    public String toolTipText() {

        return ("Type: " + this.type + "; Last modified: " + this.mod + "; Permissions: " + this.permissions + "; Path: " + this.path + "; Size: " + this.length);
    }
}
