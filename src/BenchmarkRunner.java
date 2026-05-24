import java.util.ArrayList;
import java.util.List;

public class BenchmarkRunner {
    public List<BenchmarkResult> runInsertionAndSearchBenchmark(List<PacketRule> rules) {
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

        return new BenchmarkResult(
                "AVL",
                rules.size(),
                insertTime,
                searchTime,
                tree.getRotationCount(),
                tree.height()
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

        return new BenchmarkResult(
                "Red-Black",
                rules.size(),
                insertTime,
                searchTime,
                tree.getRotationCount(),
                tree.height()
        );
    }
}