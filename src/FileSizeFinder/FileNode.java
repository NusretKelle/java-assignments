package FileSizeFinder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileNode {
    private File file;
    private long size;
    private List<FileNode> children;

    public FileNode(File file) {
        this.file = file;
        this.size = file.isFile() ? file.length() : 0;
        this.children = new ArrayList<>();
    }

    public File getFile() {
        return file;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public List<FileNode> getChildren() {
        return children;
    }

    public void addChild(FileNode child) {
        children.add(child);
    }

    @Override
    public String toString() {
        return file.getAbsolutePath() + "   " + size;
    }
}
