package FileSizeFinder;

public class FileSizeFinder {
    public static void main(String[] args) {

        String filePath = "/Users/nusretkelle/Downloads/FileSizeTest";

        System.out.println("File size listing");
        System.out.println("===============================================================");

        FileTree tree = new FileTree(filePath);

        // Post-order (depth-first)
        for (FileNode node : tree.postOrder()) {
            System.out.printf("%-80s %d%n", node.getFile().getPath(), node.getSize());
        }

        System.out.println("===============================================================");
        System.out.println("Breadth first listing of files");

        // Breadth-first
        for (FileNode node : tree.breadthFirst()) {
            System.out.printf("%-80s %d%n", node.getFile().getPath(), node.getSize());
        }
    }
}
