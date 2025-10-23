package FileSizeFinder;

import java.io.File;
import java.util.*;

public class FileTree {
    private FileNode root;

    public FileTree(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("Path not found: " + path);
        }
        this.root = buildTree(file);
    }

    // TODO 1: Build the tree recursively
    private FileNode buildTree(File file) {
        FileNode node = new FileNode(file);

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                long totalSize = 0;
                for (File f : files) {
                    FileNode child = buildTree(f);
                    node.addChild(child);
                    totalSize += child.getSize();
                }
                node.setSize(totalSize);
            }
        }

        return node;
    }

    // TODO 2: Post-order iterator (depth-first)
    public Iterable<FileNode> postOrder() {
        List<FileNode> list = new ArrayList<>();
        postOrderTraversal(root, list);
        return list;
    }

    private void postOrderTraversal(FileNode node, List<FileNode> list) {
        for (FileNode child : node.getChildren()) {
            postOrderTraversal(child, list);
        }
        list.add(node);
    }

    // TODO 3: Breadth-first iterator (level-order)
    public Iterable<FileNode> breadthFirst() {
        List<FileNode> list = new ArrayList<>();
        Queue<FileNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            FileNode current = queue.poll();
            list.add(current);
            queue.addAll(current.getChildren());
        }
        return list;
    }
}
