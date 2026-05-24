public class Main {
    public static void main(String[] args) {
        AVLRouterTree avlTree = new AVLRouterTree();
        RedBlackRouterTree redBlackTree = new RedBlackRouterTree();

        PacketRule[] rules = {
                new PacketRule(30, "192.168.0.30", "10.0.0.30", 100),
                new PacketRule(20, "192.168.0.20", "10.0.0.20", 90),
                new PacketRule(10, "192.168.0.10", "10.0.0.10", 80),
                new PacketRule(40, "192.168.0.40", "10.0.0.40", 70),
                new PacketRule(50, "192.168.0.50", "10.0.0.50", 60),
                new PacketRule(25, "192.168.0.25", "10.0.0.25", 85),
                new PacketRule(35, "192.168.0.35", "10.0.0.35", 75)
        };

        for (PacketRule rule : rules) {
            avlTree.insert(rule);
            redBlackTree.insert(rule);
        }

        avlTree.delete(20);
        redBlackTree.delete(20);

        System.out.println("SDN-Scale: AVL vs Red-Black");

        System.out.println("\nAVL");
        System.out.println("Tamanho: " + avlTree.size());
        System.out.println("Altura: " + avlTree.height());
        System.out.println("Rotações: " + avlTree.getRotationCount());
        System.out.println("Busca pelo ID 20: " + avlTree.search(20));

        System.out.println("\nRed-Black");
        System.out.println("Tamanho: " + redBlackTree.size());
        System.out.println("Altura: " + redBlackTree.height());
        System.out.println("Cor da raiz: " + redBlackTree.getRootColor());
        System.out.println("Rotações: " + redBlackTree.getRotationCount());
        System.out.println("Busca pelo ID 20: " + redBlackTree.search(20));
    }
}