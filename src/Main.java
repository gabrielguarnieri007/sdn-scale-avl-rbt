import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<PacketRule> rules = RuleGenerator.generateOrderedRules(5, RuleGenerator.DEFAULT_SEED);

        AVLRouterTree avlTree = new AVLRouterTree();
        RedBlackRouterTree redBlackTree = new RedBlackRouterTree();

        for (PacketRule rule : rules) {
            avlTree.insert(rule);
            redBlackTree.insert(rule);
        }

        System.out.println("SDN-Scale: AVL vs Red-Black");
        System.out.println("Regras geradas com seed fixa: " + rules.size());
        System.out.println("Primeira regra: " + rules.get(0));
        System.out.println("Ultima regra: " + rules.get(rules.size() - 1));

        System.out.println("\nAVL");
        System.out.println("Tamanho: " + avlTree.size());
        System.out.println("Altura: " + avlTree.height());
        System.out.println("Rotações: " + avlTree.getRotationCount());

        System.out.println("\nRed-Black");
        System.out.println("Tamanho: " + redBlackTree.size());
        System.out.println("Altura: " + redBlackTree.height());
        System.out.println("Cor da raiz: " + redBlackTree.getRootColor());
        System.out.println("Rotações: " + redBlackTree.getRotationCount());
    }
}