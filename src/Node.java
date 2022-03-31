import java.util.ArrayList;

public class Node {

    String type;
    String fileType;
    Long mod;
    int permissions;
    String path;
    Long length;
    ArrayList<Node> children;

    //file node constructor
    public Node(String type, Long mod, int permissions, String path, Long length) {
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

        this.type = type;
        this.mod = mod;
        this.permissions = permissions;
        this.path = path;
        this.length = length;
        this.children = children;
        this.fileType = "dir";
    }

    //getter and setter for where the rectangle was drawn for a specific node
}
