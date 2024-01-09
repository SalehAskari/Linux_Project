import java.util.Set;
import java.util.*;
public class GenericTree<T> {
    private TreeNode<T> root;
    private TreeNode<T> current;
    private TreeNode<T> target_1;
    private TreeNode<T> target_2;

    public GenericTree(T sudo){
        root = new TreeNode();
        root.setData(sudo);
        current = target_1 = target_2 = root;
    }
    public boolean mkdir(T data) {
        if (!current.isHasinChldrenFile(data.toString())) {
            TreeNode<T> newTreeNode = new TreeNode<>();
            newTreeNode.setData(data);
            return current.setChildren(newTreeNode);
        }
        return false;
    }
    public boolean touch(T data) {
            return current.setChildrenFile(data.toString());

    }

    public boolean rmdir(T data) {
        if (!current.isHasinChldrenFile(data.toString())) {
            Set<TreeNode<T>> temp = current.getChildren();
            for (TreeNode<T> child : temp) {
                if (child.getData().equals(data)) {
                    return temp.remove(child);
                }
            }
        }
        return false;

    }
    public boolean rm(String enter){
       return current.removeChildrenAsFile(enter);
    }
    public String pwd() {
        StringBuilder path = new StringBuilder();
        TreeNode<T> currentNode = current;

        while (currentNode != null) {
            path.insert(0, "/" + currentNode.getData());
            currentNode = getParent(currentNode, root);
        }
        return path.toString();
    }

    private TreeNode<T> getParent(TreeNode<T> node, TreeNode<T> currentNode) {
        for (TreeNode<T> child : currentNode.getChildren()) {
            if (child == node) {
                return currentNode;
            }
            TreeNode<T> parent = getParent(node, child);
            if (parent != null) {
                return parent;
            }
        }
        return null;
    }
    public String ls() {
        StringBuilder list = new StringBuilder();
        for (TreeNode<T> treeNode : current.getChildren()) {
            list.append(treeNode.getData()+ "   ");
        }
        for (String temp : current.getNameCildrenFile()){
            list.append(temp+"   ");
        }
        return list.toString();
    }
    public void cd(T data) {
        if (!current.isHasinChldrenFile(data.toString())) {
            Set<TreeNode<T>> children = current.getChildren();
            for (TreeNode<T> child : children) {
                if (child.getData().equals(data)) {
                    current = child;
                    return;
                }
            }
        }
        System.out.println("cd: no such file or directory: "+data.toString());
    }

    public boolean cp(String enter_1 , String enter_2){

        TreeNode<T> copy_current = current;
        current = root;
        String[] split_1 = enter_1.split("[\\s/]+");
        String[] split_2 = enter_2.split("[\\s/]+");
        for (int i = 2 ; i < split_1.length-1 ; i++){
            cd((T)split_1[i]);
        }
        target_1 = current;
        current = root;
        for (int i = 2 ; i < split_2.length ; i++){
            cd((T)split_2[i]);
        }
        target_2 = current;
        current = copy_current;
        if (target_1.getNameCildrenFile().contains(split_1[split_1.length-1])){
            return target_2.getNameCildrenFile().add(split_1[split_1.length-1]);
        }
        current = target_1;
        cd((T)split_1[split_1.length-1]);
        target_1 = current;
        current = copy_current;
        return target_2.setChildren(target_1);
    }
public boolean mv(String enter_1 , String enter_2){

    TreeNode<T> copy_current = current;
    current = root;
    String[] split_1 = enter_1.split("[\\s/]+");
    String[] split_2 = enter_2.split("[\\s/]+");
    for (int i = 2 ; i < split_1.length-1 ; i++){
        cd((T)split_1[i]);
    }
    target_1 = current;
    current = root;
    for (int i = 2 ; i < split_2.length ; i++){
        cd((T)split_2[i]);
    }
    target_2 = current;
    current = copy_current;
    if (target_1.removeChildrenAsFile(split_1[split_1.length-1])){
        return target_2.setChildrenFile(split_1[split_1.length-1]);
    }
    current = target_1;
    cd((T)split_1[split_1.length-1]);
    target_1 = current;
    current = copy_current;
    if (rmdir(target_1.getData())) {
        return target_2.setChildren(target_1);
    }
    return false;
}


    public int sz() {
        return countSubnodes(current);
    }

    private int countSubnodes(TreeNode<T> node) {
        int count = node.getNameCildrenFile().size();
        for (TreeNode<T> child : node.getChildren()) {
            count++;
            count += countSubnodes(child);
        }
        return count;
    }
    public void cd_dot_dot() {
        TreeNode<T> parentNode = getParent(current, root);
        if (parentNode != null) {
            current = parentNode;
        } else {
            System.out.println("Parent node not found.");
        }
    }
    public void setCurrent(TreeNode<T> enter){
        this.current = enter;
    }
    public TreeNode<T> getCurrent(){
        return current;
    }
    public void currentGoToRot(){
        current = root;
    }
    public TreeNode<T> getRoot(){
        return root;
    }

    //BFS and DFS
    // DFS -> it means depth to depth

    public TreeNode<T> dfs(TreeNode<T> root, T enter , boolean directory) {
        if (root == null) {
            return null;
        }
        if (directory) {

            if (root.getData().equals(enter)) {
                return root;
            }

            for (TreeNode<T> child : root.getChildren()) {
                TreeNode<T> result = dfs(child, enter , directory);
                if (result != null) {
                    return result;
                }
            }
        }
        else {
            if (root.getNameCildrenFile().contains(enter.toString())){
                return root;
            }
            for (TreeNode<T> child : root.getChildren()) {
                TreeNode<T> result = dfs(child, enter , directory);
                if (result!= null && result.getNameCildrenFile().contains(enter.toString())) {
                    return result;
                }
            }
        }

        return null;
    }
    // BFS -> it means level to level

    public TreeNode<T> bfs(TreeNode<T> root, T enter , boolean directory) {
        if (root == null) {
            return null;
        }

            Queue<TreeNode<T>> queue = new LinkedList<>();
            queue.add(root);
        if (directory) {
            while (!queue.isEmpty()) {
                TreeNode<T> node = queue.poll();
                if (node.getData().equals(enter)) {
                    return node;
                }

                for (TreeNode<T> child : node.getChildren()) {
                    queue.add(child);
                }
            }
        }
        else {
            while (!queue.isEmpty()) {
                TreeNode<T> node = queue.poll();
                if (node.getNameCildrenFile().contains(enter.toString())) {
                    return node;
                }

                for (TreeNode<T> child : node.getChildren()) {
                    queue.add(child);
                }
            }
        }

        return null;
    }
    public TreeNode<T> sp(String enter_1, String enter_2) {
        String [] split_1 = enter_1.split("/");
        String [] split_2 = enter_2.split("/");

        TreeNode<T> node_1 = root ;
        for (int i = 1 ; i < split_1.length-1 ; i++){
            node_1 = dfs(node_1 , (T)split_1[i] ,true);
        }
        TreeNode<T> node_2 = root;
        for (int i = 1 ; i < split_2.length-1 ; i++){
            node_2 = dfs(node_2 , (T)split_2[i] ,true);
        }
        if (node_1.isHasChildren(split_1[split_1.length-1])) node_1 = dfs(node_1 , (T)split_1[split_1.length-1] ,true);
        else if (node_1.isHasinChldrenFile(split_1[split_1.length-1]))  dfs(node_1 , (T)split_1[split_1.length-1] ,false);
        if (node_2.isHasChildren(split_2[split_2.length-1])) node_2 = dfs(node_2 , (T)split_2[split_2.length-1] ,true);
        else if (node_2.isHasinChldrenFile(split_2[split_2.length-1]))  dfs(node_2 , (T)split_2[split_2.length-1] ,false);

        if (node_1 == node_2){
            return node_1;
        }

        if (node_1 != null && node_2 != null) {
            return findSharedParent(root, node_1, node_2);
        }

        return null;
    }

    private TreeNode<T> findSharedParent(TreeNode<T> current, TreeNode<T> node1, TreeNode<T> node2) {
        if (current == null || current == node1 || current == node2) {
            return current;
        }

        Set<TreeNode<T>> children = current.getChildren();
        TreeNode<T> sharedParent = null;

        for (TreeNode<T> child : children) {
            TreeNode<T> result = findSharedParent(child, node1, node2);
            if (result != null) {
                if (sharedParent != null) {
                    return current;
                }
                sharedParent = result;
            }
        }
        return sharedParent;
    }
}
