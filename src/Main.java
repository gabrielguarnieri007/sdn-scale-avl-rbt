import java.util.List;

public class Main {
    private static final int[] DATASET_SIZES = {
            1000,
            10000,
            50000,
            100000
    };

    public static void main(String[] args) {
        BenchmarkRunner benchmarkRunner = new BenchmarkRunner();

        System.out.println("SDN-Scale: AVL vs Red-Black");
        System.out.println("Benchmark de inserção e busca");
        System.out.println("Seed utilizada: " + RuleGenerator.DEFAULT_SEED);
        System.out.println();

        for (int amount : DATASET_SIZES) {
            List<PacketRule> rules = RuleGenerator.generateOrderedRules(amount, RuleGenerator.DEFAULT_SEED);
            List<BenchmarkResult> results = benchmarkRunner.runInsertionAndSearchBenchmark(rules);

            System.out.println("Volume de dados: " + amount);

            for (BenchmarkResult result : results) {
                System.out.println(result);
            }

            System.out.println();
        }
    }
}
