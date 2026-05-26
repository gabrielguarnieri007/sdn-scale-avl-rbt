import java.util.ArrayList;
import java.util.List;

public class BenchmarkRunner {
    private static final double DELETE_PERCENTAGE = 0.20;

    public List<BenchmarkResult> runBenchmark(List<PacketRule> rules) {
        if (rules == null || rules.isEmpty()) {
            throw new IllegalArgumentException("A lista de regras não pode ser vazia.");
        }

        List<BenchmarkResult> results = new ArrayList<>();

        results.add(runAvlBenchmark(rules));
        results.add(runRedBlackBenchmark(rules));

        return results;
    }

    private BenchmarkResult runAvlBenchmark(List<PacketRule> rules) {
        AVLRouterTree tree = new AVLRouterTree();

        long startInsert = System.nanoTime();

        for (PacketRule rule : rules) {
            tree.insert(rule);
        }

        long insertTime = System.nanoTime() - startInsert;

        long startSearch = System.nanoTime();

        for (PacketRule rule : rules) {
            PacketRule found = tree.search(rule.getId());

            if (found == null) {
                throw new IllegalStateException("Regra não encontrada na AVL: " + rule.getId());
            }
        }

        long searchTime = System.nanoTime() - startSearch;

        long startDelete = System.nanoTime();

        for (PacketRule rule : getRulesToDelete(rules)) {
            boolean removed = tree.delete(rule.getId());

            if (!removed) {
                throw new IllegalStateException("Falha ao remover regra da AVL: " + rule.getId());
            }
        }

        long deleteTime = System.nanoTime() - startDelete;

        validateDeletedRules("AVL", tree, getRulesToDelete(rules));

        return new BenchmarkResult(
                "AVL",
                rules.size(),
                insertTime,
                searchTime,
                deleteTime,
                tree.getRotationCount(),
                tree.height(),
                tree.size()
        );
    }

    private BenchmarkResult runRedBlackBenchmark(List<PacketRule> rules) {
        RedBlackRouterTree tree = new RedBlackRouterTree();

        long startInsert = System.nanoTime();

        for (PacketRule rule : rules) {
            tree.insert(rule);
        }

        long insertTime = System.nanoTime() - startInsert;

        long startSearch = System.nanoTime();

        for (PacketRule rule : rules) {
            PacketRule found = tree.search(rule.getId());

            if (found == null) {
                throw new IllegalStateException("Regra não encontrada na Red-Black: " + rule.getId());
            }
        }

        long searchTime = System.nanoTime() - startSearch;

        long startDelete = System.nanoTime();

        for (PacketRule rule : getRulesToDelete(rules)) {
            boolean removed = tree.delete(rule.getId());

            if (!removed) {
                throw new IllegalStateException("Falha ao remover regra da Red-Black: " + rule.getId());
            }
        }

        long deleteTime = System.nanoTime() - startDelete;

        validateDeletedRules("Red-Black", tree, getRulesToDelete(rules));

        return new BenchmarkResult(
                "Red-Black",
                rules.size(),
                insertTime,
                searchTime,
                deleteTime,
                tree.getRotationCount(),
                tree.height(),
                tree.size()
        );
    }

    private List<PacketRule> getRulesToDelete(List<PacketRule> rules) {
        int amountToDelete = (int) Math.round(rules.size() * DELETE_PERCENTAGE);
        return rules.subList(0, amountToDelete);
    }

    private void validateDeletedRules(String treeName, AVLRouterTree tree, List<PacketRule> deletedRules) {
        for (PacketRule rule : deletedRules) {
            if (tree.search(rule.getId()) != null) {
                throw new IllegalStateException(treeName + " manteve regra removida: " + rule.getId());
            }
        }
    }

    private void validateDeletedRules(String treeName, RedBlackRouterTree tree, List<PacketRule> deletedRules) {
        for (PacketRule rule : deletedRules) {
            if (tree.search(rule.getId()) != null) {
                throw new IllegalStateException(treeName + " manteve regra removida: " + rule.getId());
            }
        }
    }
}