# 📘 Linux Emulator Documentation

## 📝 Introduction

This project is a simplified Linux command-line emulator built in Java. It simulates basic file system operations and user management, similar to what you’d find in a real Unix/Linux environment.

---

## ⚙️ Main Components

### 1. TreeNode.java

The core building block of the file system tree.

**Features:**

- Represents both directories and files
- Keeps track of:

  - Child directories
  - File names in the current directory
  - Subdirectory names in the current directory

- Provides methods for:

  - Adding/removing subdirectories
  - Managing files
  - Checking if files/directories exist

---

### 2. GenericTree.java

Implements the full tree structure of the file system.

**Key Functionalities:**

🔹 **File System Operations**

- `mkdir` → Create a directory
- `rmdir` → Remove a directory
- `touch` → Create a file
- `rm` → Delete a file
- `cd` → Change directory
- `ls` → List directory contents
- `pwd` → Show current path
- `cp` → Copy files/directories
- `mv` → Move files/directories

🔹 **Search**

- `dfs` → Depth-first search
- `bfs` → Breadth-first search
- `sp` → Find shared parent of two paths

🔹 **Utility**

- `sz` → Count child nodes (size)
- Navigation helpers like `cd_dot_dot`, `currentGoToRot`

---

### 3. Linux.java

The main driver class that runs the emulator.

**Features:**

- User session management
- Command parsing and execution
- Error handling with user-friendly messages
- Multi-user support with commands:

  - `useradd` → Add a new user
  - `userdel` → Delete a user
  - `su` → Switch users

---

## 🚀 How to Use

### Getting Started

1. Compile all Java files
2. Run `Linux.java`
3. Enter a username when prompted

### Available Commands

🔹 **File & Directory Management**

- `mkdir [directory]` → Create a new folder

  - Example: `mkdir documents`

- `rmdir [directory]` → Remove an empty folder
- `touch [filename]` → Create a file
- `rm [filename]` → Delete a file
- `cd [path]` → Change directory

  - `cd ..` → Go to parent directory
  - `cd ~` → Go to root

- `ls` → List contents of current folder
- `pwd` → Show the current working path
- `cp [src] [dest]` → Copy files/folders
- `mv [src] [dest]` → Move files/folders

🔹 **Search**

- `find [path] [type] [name] [method]`

  - type → `f` for file, `d` for directory
  - method → `dfs` or `bfs`
  - Example: `find /home d documents dfs`

- `sp [path1] [path2]` → Find common parent directory

🔹 **System Info**

- `sz` → Show number of child nodes

🔹 **User Management**

- `useradd [username]` → Add a user (root only)
- `userdel [username]` → Delete a user (root only)
- `su [username]` → Switch user

🔹 **Session Control**

- `exit` → Quit the emulator

---

## 🛠️ Implementation Details

### File System Structure

- The system is built as a tree
- Each `TreeNode` = a directory
- Files are stored as names in a `Set` inside directories
- The root node = user’s home directory

### Path Handling

- Absolute paths → start with `/`
- Relative paths → resolved from the current directory
- Special paths:

  - `..` → Parent
  - `~` → Root

### Search Algorithms

- DFS → Goes as deep as possible before backtracking
- BFS → Explores level by level

### Error Handling

The emulator shows clear error messages for:

- Invalid commands
- Non-existent files/directories
- Permission issues
- Duplicate names

---

## 🖥️ Example Run

```
Welcome to Linux Emulator
Please enter a username: user1
-->(user1-Linux-2024)-[/user1]
$ mkdir documents
-->(user1-Linux-2024)-[/user1]
$ cd documents
-->(user1-Linux-2024)-[/user1/documents]
$ touch file1.txt
-->(user1-Linux-2024)-[/user1/documents]
$ ls
file1.txt
-->(user1-Linux-2024)-[/user1/documents]
$ pwd
/user1/documents
-->(user1-Linux-2024)-[/user1/documents]
$ exit
```

---

## ⚠️ Limitations

1. No file content storage (only file names are tracked)
2. Simple permission system (only root manages users)
3. No symbolic or hard links
4. No file attributes like timestamps or permissions

---

## 🔮 Future Enhancements

1. Support file content storage
2. Advanced permission system
3. File attributes (timestamps, permissions, etc.)
4. Command history and tab completion
5. Redirection and piping

---

This emulator provides a solid foundation for learning Linux file system operations and can easily be extended with more advanced features. 🚀
