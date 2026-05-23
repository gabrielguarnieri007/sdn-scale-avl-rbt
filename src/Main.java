public class Main {
    public static void main(String[] args) {
        AVLRouterTree avlTree = new AVLRouterTree();
        RedBlackRouterTree redBlackTree = new RedBlackRouterTree();

        avlTree.insert(new PacketRule(30, "192.168.0.30", "10.0.0.30", 100));
        avlTree.insert(new PacketRule(20, "192.168.0.20", "10.0.0.20", 90));
        avlTree.insert(new PacketRule(10, "192.168.0.10", "10.0.0.10", 80));
        avlTree.insert(new PacketRule(40, "192.168.0.40", "10.0.0.40", 70));
        avlTree.insert(new PacketRule(50, "192.168.0.50", "10.0.0.50", 60));

        avlTree.delete(20);

        System.out.println("SDN-Scale: AVL vs Red-Black");

        System.out.println("\nAVL");
        System.out.println("Tamanho: " + avlTree.size());
        System.out.println("Altura: " + avlTree.height());
        System.out.println("Rotações: " + avlTree.getRotationCount());
        System.out.println("Busca pelo ID 20: " + avlTree.search(20));

        System.out.println("\nRed-Black");
        System.out.println("Tamanho inicial: " + redBlackTree.size());
        System.out.println("Altura inicial: " + redBlackTree.height());
        System.out.println("Cor da raiz: " + redBlackTree.getRootColor());
        System.out.println("Rotações: " + redBlackTree.getRotationCount());
    }
}