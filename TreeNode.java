import java.util.HashSet;
import java.util.Set;
public class TreeNode<T>{
    private Set<TreeNode<T>> children;
    private Set<String> nameChildrenDirectory;
    private Set<String> nameCildrenFile;
    private T data;

    public TreeNode(){
        children = new HashSet<>();
        nameChildrenDirectory = new HashSet<>();
        nameCildrenFile = new HashSet<>();
        data = null;
    }
    public Set<TreeNode<T>> getChildren() {
        return children;
    }

    public boolean setChildren(TreeNode<T> children) {
        if (nameChildrenDirectory.add(children.getData().toString())) {
            this.children.add(children);
            return true;
        }
        return false;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public boolean setChildrenFile(String enter){
        return nameCildrenFile.add(enter);
    }
    public boolean isHasinChldrenFile(String enter){
        return nameCildrenFile.contains(enter);
    }
    public Set<String> getNameCildrenFile(){
        return nameCildrenFile;
    }
    public boolean isHasChildren(String enter){
        return nameChildrenDirectory.contains(enter);
    }
    public boolean removeChildrenAsFile(String enter){
        return nameCildrenFile.remove(enter);
    }
}
