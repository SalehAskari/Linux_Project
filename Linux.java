import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Linux {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to Linux Emulator" + "\nPleas enter a username: ");
        String username = scanner.next();
        scanner.nextLine();
        Set<GenericTree<String>> group = new HashSet<>();
        Set<String> user = new HashSet<>();
        user.add(username);
        GenericTree<String> unix = new GenericTree<>(username);
        group.add(unix);
        boolean exit = false;
        while (!exit){
            System.out.print("-->(" + unix.getRoot().getData() + "-Linux-2024)-[" + unix.pwd() + "]\n");
            System.out.print("$ ");
            String enter = scanner.nextLine();
            String[]split = enter.split("[\\s/]+");
//            System.out.println(Arrays.toString(split)); show split
            switch (split[0]){
                case "ls":
                    System.out.println(unix.ls());
                    break;
                case "pwd":
                    System.out.println(unix.pwd());
                    break;
                case "mkdir":
                    if (split.length ==1 ){
                        System.out.println("mkdir: missing operand");
                        break;
                    }
                    boolean status = false;
                    if (split.length == 2){
                        status = unix.mkdir(split[1]);
                    }
                    else if (split.length > 2) {
                    if (enter.charAt(6) == '/'){
                        if (!isTrueCommand_1(enter, split , true)) {
                            System.out.println("mkdir: no such file or directory");
                            break;
                        }
                        TreeNode<String> current = unix.getCurrent();
                        unix.currentGoToRot();
                        for (int i = 1 ; i < split.length-1 ; i++){
                            unix.cd(split[i]);
                        }
                        status = unix.mkdir(split[split.length-1]);
                        unix.setCurrent(current);
                    }
                    else {
                        if (!isTrueCommand_1(enter, split , false)) {
                            System.out.println("mkdir: no such file or directory");
                            break;
                        }
                        TreeNode<String> current = unix.getCurrent();
                        for (int i = 1; i < split.length - 1; i++) {
                            unix.cd(split[i]);
                        }
                        status = unix.mkdir(split[split.length - 1]);
                        unix.setCurrent(current);
                    }
                    }
                    if (!status) System.out.println("mkdir: cannot create directory '" + split[split.length-1] + "' : File exists");
                    break;
                case "rmdir":
                    if (split.length ==1 ){
                        System.out.println("rmdir: missing operand");
                        break;
                    }
                    status = false;
                    if (split.length == 2){
                        status = unix.rmdir(split[1]);
                    }
                    else if (split.length > 2) {
                        if (enter.charAt(6) == '/'){
                            if (!isTrueCommand_1(enter, split , true)) {
                                System.out.println("rmdir: no such file or directory");
                                break;
                            }
                            TreeNode<String> current = unix.getCurrent();
                            unix.currentGoToRot();
                            for (int i = 1 ; i < split.length-1 ; i++){
                                unix.cd(split[i]);
                            }
                            status = unix.rmdir(split[split.length-1]);
                            unix.setCurrent(current);
                        }
                        else {
                            if (!isTrueCommand_1(enter, split , false)) {
                                System.out.println("rmdir: no such file or directory");
                                break;
                            }
                            TreeNode<String> current = unix.getCurrent();
                            for (int i = 1; i < split.length - 1; i++) {
                                unix.cd(split[i]);
                            }
                            status = unix.rmdir(split[split.length - 1]);
                            unix.setCurrent(current);
                        }
                    }
                    if (!status) System.out.println("rmdir: failed to remove '" + split[split.length-1] + "' : No such file or directory");
                    break;
                case "touch":
                    if (split.length ==1 ){
                        System.out.println("touch: missing operand");
                        break;
                    }
                    status = false;
                    if (split.length == 2){
                        status = unix.touch(split[1]);
                    }
                    else if (split.length > 2) {
                        if (enter.charAt(6) == '/'){
                            if (!isTrueCommand_1(enter, split , true)) {
                                System.out.println("touch: no such file or directory");
                                break;
                            }
                            TreeNode<String> current = unix.getCurrent();
                            unix.currentGoToRot();
                            for (int i = 1 ; i < split.length-1 ; i++){
                                unix.cd(split[i]);
                            }
                            status = unix.touch(split[split.length-1]);
                            unix.setCurrent(current);
                        }
                        else {
                            if (!isTrueCommand_1(enter, split , false)) {
                                System.out.println("touch: no such file or directory");
                                break;
                            }
                            TreeNode<String> current = unix.getCurrent();
                            for (int i = 1; i < split.length - 1; i++) {
                                unix.cd(split[i]);
                            }
                            status = unix.touch(split[split.length - 1]);
                            unix.setCurrent(current);
                        }
                    }
                    if (!status) System.out.println("touch: cannot create file '" + split[split.length-1] + "' : File exists");
                    break;
                case "rm":
                    if (split.length ==1 ){
                        System.out.println("rm: missing operand");
                        break;
                    }
                    status = false;
                    if (split.length == 2){
                        status = unix.rm(split[1]);
                    }
                    else if (split.length > 2) {
                        if (enter.charAt(6) == '/'){
                            if (!isTrueCommand_1(enter, split , true)) {
                                System.out.println("rm: no such file");
                                break;
                            }
                            TreeNode<String> current = unix.getCurrent();
                            unix.currentGoToRot();
                            for (int i = 1 ; i < split.length-1 ; i++){
                                unix.cd(split[i]);
                            }
                            status = unix.rm(split[split.length-1]);
                            unix.setCurrent(current);
                        }
                        else {
                            if (!isTrueCommand_1(enter, split , false)) {
                                System.out.println("rm: no such file");
                                break;
                            }
                            TreeNode<String> current = unix.getCurrent();
                            for (int i = 1; i < split.length - 1; i++) {
                                unix.cd(split[i]);
                            }
                            status = unix.rm(split[split.length - 1]);
                            unix.setCurrent(current);
                        }
                    }
                    if (!status) System.out.println("rm: failed to remove '" + split[split.length-1] + "' : No such file");
                    break;
                case "cp":
                    if (split.length ==1 ){
                        System.out.println("cp: missing file operand");
                        break;
                    }
                    if (!isTrueCommand_2(enter , enter.split(" ")[1] , enter.split(" ")[2])){
                        System.out.println("cp: no such file or directory");
                        break;
                    }
                    if (!unix.cp(enter.split(" ")[1] , enter.split(" ")[2])){
                        System.out.println("cp: failed");
                    }
                    break;
                case "mv":
                    if (split.length ==1 ){
                        System.out.println("mv: missing file operand");
                        break;
                    }
                    if (!isTrueCommand_2(enter , enter.split(" ")[1] , enter.split(" ")[2])){
                        System.out.println("mv: no such file or directory");
                        break;
                    }
                    if (!unix.mv(enter.split(" ")[1] , enter.split(" ")[2])){
                        System.out.println("mv: failed");
                    }
                    break;
                case "cd":
                    if (split.length ==1 ){
                        break;
                    }
                    if (enter.charAt(3) == '/'){
                        unix.currentGoToRot();
                        for (int i = 1 ; i < split.length-1 ; i++){
                            unix.cd(split[i]);
                        }
                    }
                    else if (split.length > 2) {
                        for (int i = 1 ; i < split.length-1 ; i++){
                            unix.cd(split[i]);
                        }
                    }
                    if (split[1].equals("..")) unix.cd_dot_dot();
                    else if (split[1].equals("~")) unix.currentGoToRot();
                    else unix.cd(split[1]);
                    break;
                case "find":
                    if (split.length == 1){
                        System.out.println("find: missing file operand");
                        break;
                    }
                    else {
                        // find absolute_path type name search
                        //  [0]      [1]       [2]  [3]   [4]
                        if (split.length != 5){
                            System.out.println("find: no such file or directory");
                            break;
                        }
                        if (split[4].equals("dfs")){
                            if (split[2].equals("f")){

                                TreeNode<String> start = unix.dfs(unix.getRoot() , split[1] , true);
                                if (start == null) {
                                    System.out.println("find: no such directory '" +split[1]+"'" );
                                    break;
                                }
                                TreeNode<String> target = unix.dfs(start , split[3] , false);
                                if (target == null){
                                    System.out.println("find: no such file '" +split[3]+"'" );
                                    break;
                                }
                                unix.setCurrent(target);

                            } else if (split[2].equals("d")) {
                                TreeNode<String> start = unix.dfs(unix.getRoot() , split[1] , true);
                                if (start == null) {
                                    System.out.println("find: no such directory '" +split[1]+"'" );
                                    break;
                                }
                                TreeNode<String> target = unix.dfs(start , split[3] , true);
                                if (target == null){
                                    System.out.println("find: no such directory '" +split[3]+"'" );
                                    break;
                                }
                                unix.setCurrent(target);
                                unix.cd_dot_dot();
                            }
                            else {
                                System.out.println("find: no such file or directory");
                                break;
                            }

                        } else if (split[4].equals("bfs")) {
                            if (split[2].equals("f")){
                                TreeNode<String> start = unix.bfs(unix.getRoot() , split[1] , true);
                                if (start == null){
                                    System.out.println("find: no such directory '" +split[1]+"'" );
                                    break;
                                }
                                TreeNode<String> target= unix.bfs(start , split[3] , false);
                                if (target == null){
                                    System.out.println("find: no such file '" +split[3]+"'" );
                                    break;
                                }
                                unix.setCurrent(target);

                            } else if (split[2].equals("d")) {
                                TreeNode<String> start = unix.bfs(unix.getRoot() , split[1] , true);
                                if (start == null){
                                    System.out.println("find: no such directory '" +split[1]+"'" );
                                    break;
                                }
                                TreeNode<String> target = unix.bfs(start , split[3] , true);
                                if (target == null){
                                    System.out.println("find: no such directory '" +split[3]+"'" );
                                    break;
                                }
                                unix.setCurrent(target);
                                unix.cd_dot_dot();
                            }
                            else {
                                System.out.println("find: no such file or directory");
                                break;
                            }
                        }
                        else System.out.println("find: no such file or directory");
                    }

                    break;
                case "sp":
                    if (split.length == 1){
                        System.out.println("sp: missing file operand");
                    }
                    if (!isTrueCommand_2(enter , enter.split(" ")[1] , enter.split(" ")[2])){
                        System.out.println("sp: no such file or directory");
                        break;
                    }
                    TreeNode<String> temp_temp = unix.sp(enter.split(" ")[1] , enter.split(" ")[2]);
                    if (temp_temp == null){
                        System.out.println("sp: not find");
                        break;
                    }
                    System.out.println(temp_temp.getData().toString());

                    break;
                case "sz":
                    System.out.println(unix.sz());
                    break;
                case "useradd":
                    if (split.length == 1){
                        System.out.println("useradd: missing file operand");
                    }
                    if (split.length > 2){
                        System.out.println("useradd: failed");
                        break;
                    }
                    if (!unix.getRoot().getData().equals(username)) {
                        System.out.println("useradd: you do not have super user access");
                        break;
                    }
                    if (user.add(split[1])) {
                        GenericTree<String> newUser = new GenericTree<>(split[1]);
                        group.add(newUser);
                    }
                    else System.out.println("useradd: user '" + split[1] + "' already exists");

                    break;
                case "su":
                    if (split.length == 1){
                        System.out.println("su: missing file operand");
                    }
                    if (split.length > 2){
                        System.out.println("su: failed");
                        break;
                    }
                    boolean chek = true;
                    for (GenericTree<String> temp: group){
                        if (temp.getRoot().getData().equals(split[1])){
                            unix = temp;
                            chek = false;
                            break;
                        }
                    }
                    if (chek)
                    System.out.println("su: user "+split[1]+" does not exist or the user entry does not contain all the required fields");
                    break;
                case "userdel":
                    if (split.length == 1){
                        System.out.println("userdel: missing file operand");
                    }
                    if (split.length > 2){
                        System.out.println("userdel: failed");
                        break;
                    }
                    if (!unix.getRoot().getData().equals(username)) {
                        System.out.println("userdel: you do not have super user access");
                        break;
                    }
                    if (split[1].equals(username)){
                        System.out.println("userdel: illegal");
                        break;
                    }
                    user.remove(split[1]);
                    for (GenericTree<String> temp : group){
                        if (temp.getRoot().getData().equals(split[1])) {
                            group.remove(temp);
                            break;
                        }
                    }
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println(split[0] + ": " + "command not found");
            }


        }


    }
    public static boolean isTrueCommand_1(String enter_1 , String[] enter_2 , boolean root){
        int space = 0;
        int slash = 0;
        for (char x : enter_1.toCharArray()){
            if (x == ' ') space++;
            else if (x == '/') slash++;
        }
        if (root) {
            if (space != 1) return false;
            if (slash != (enter_2.length -1)) return false;
        }
        else {
            if (space != 1) return false;
            if (slash != (enter_2.length -2)) return false;
        }
        return true;
    }
    public static boolean isTrueCommand_2(String enter , String enter_1 , String enter_2){
        String [] split = enter_1.split(" ");
        if (split.length != 3) return false;
        int space = 0;
        int slash = 0;
        for (char x : enter_1.toCharArray()){
            if (x == ' ') space++;
            else if (x == '/') slash++;
        }
        if (space != 0) return false;
        if (slash != (enter_1.length()-1)) return false;
        return true;
    }
}
