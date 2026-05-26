import java.util.List;

public class TreeValidationTests {
    public static void main(String[] args) {
        testAvlInvariantsAfterInsertions();
        testAvlInvariantsAfterDeletingTwentyPercent();
        testRedBlackInvariantsAfterInsertions();
        testRedBlackInvariantsAfterDeletingTwentyPercent();

        System.out.println("Testes de validação da AVL e da Red-Black executados com sucesso.");
    }

    private static void testAvlInvariantsAfterInsertions() {
        List<PacketRule> rules = RuleGenerator.generateOrderedRules(1000, RuleGenerator.DEFAULT_SEED);
        AVLRouterTree tree = new AVLRouterTree();

        for (PacketRule rule : rules) {
            tree.insert(rule);
        }

        assertTrue(tree.size() == 1000, "A AVL deveria conter 1000 regras.");
        assertTrue(tree.validateInvariants(), "As invariantes da AVL falharam após inserção.");
    }

    private static void testAvlInvariantsAfterDeletingTwentyPercent() {
        List<PacketRule> rules = RuleGenerator.generateOrderedRules(1000, RuleGenerator.DEFAULT_SEED);
        AVLRouterTree tree = new AVLRouterTree();

        for (PacketRule rule : rules) {
            tree.insert(rule);
        }

        int amountToDelete = rules.size() / 5;

        for (int index = 0; index < amountToDelete; index++) {
            PacketRule rule = rules.get(index);
            boolean removed = tree.delete(rule.getId());

            assertTrue(removed, "A regra deveria ter sido removida da AVL: " + rule.getId());
        }

        assertTrue(tree.size() == 800, "A AVL deveria conter 800 regras após remover 20%.");
        assertTrue(tree.validateInvariants(), "As invariantes da AVL falharam após remoção.");

        for (int index = 0; index < amountToDelete; index++) {
            PacketRule rule = rules.get(index);
            assertTrue(tree.search(rule.getId()) == null, "Regra removida ainda foi encontrada na AVL: " + rule.getId());
        }
    }

    private static void testRedBlackInvariantsAfterInsertions() {
        List<PacketRule> rules = RuleGenerator.generateOrderedRules(1000, RuleGenerator.DEFAULT_SEED);
        RedBlackRouterTree tree = new RedBlackRouterTree();

        for (PacketRule rule : rules) {
            tree.insert(rule);
        }

        assertTrue(tree.size() == 1000, "A Red-Black deveria conter 1000 regras.");
        assertTrue("BLACK".equals(tree.getRootColor()), "A raiz da Red-Black deve ser preta.");
        assertTrue(tree.validateInvariants(), "As propriedades da Red-Black falharam após inserção.");
    }

    private static void testRedBlackInvariantsAfterDeletingTwentyPercent() {
        List<PacketRule> rules = RuleGenerator.generateOrderedRules(1000, RuleGenerator.DEFAULT_SEED);
        RedBlackRouterTree tree = new RedBlackRouterTree();

        for (PacketRule rule : rules) {
            tree.insert(rule);
        }

        int amountToDelete = rules.size() / 5;

        for (int index = 0; index < amountToDelete; index++) {
            PacketRule rule = rules.get(index);
            boolean removed = tree.delete(rule.getId());

            assertTrue(removed, "A regra deveria ter sido removida da Red-Black: " + rule.getId());
        }

        assertTrue(tree.size() == 800, "A Red-Black deveria conter 800 regras após remover 20%.");
        assertTrue("BLACK".equals(tree.getRootColor()), "A raiz da Red-Black deve continuar preta.");
        assertTrue(tree.validateInvariants(), "As propriedades da Red-Black falharam após remoção.");

        for (int index = 0; index < amountToDelete; index++) {
            PacketRule rule = rules.get(index);
            assertTrue(tree.search(rule.getId()) == null, "Regra removida ainda foi encontrada na Red-Black: " + rule.getId());
        }
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }
}