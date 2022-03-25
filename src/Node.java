
public class Node {

    String type;
    Long mod;
    String permissions;
    String path;

    //file node constructor
    public Node(String type, Long mod, String permissions, String path) {

        this.type = type;
        this.mod = mod;
        this.permissions = permissions;
        this.path = path;
    }

    //dir node constructor
    public Node() {

        //here
    }
}
