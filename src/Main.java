import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int[] DATASET_SIZES = {
            1000,
            10000,
            50000,
            100000
    };

    private static final String OUTPUT_FILE = "results/benchmark_results.csv";

    public static void main(String[] args) {
        BenchmarkRunner benchmarkRunner = new BenchmarkRunner();
        List<BenchmarkResult> allResults = new ArrayList<>();

        System.out.println("SDN-Scale: AVL vs Red-Black");
        System.out.println("Benchmark de inserção, busca e remoção de 20%");
        System.out.println("Seed utilizada: " + RuleGenerator.DEFAULT_SEED);
        System.out.println();

        for (int amount : DATASET_SIZES) {
            List<PacketRule> rules = RuleGenerator.generateOrderedRules(amount, RuleGenerator.DEFAULT_SEED);
            List<BenchmarkResult> results = benchmarkRunner.runBenchmark(rules);

            allResults.addAll(results);

            System.out.println("Volume de dados: " + amount);

            for (BenchmarkResult result : results) {
                System.out.println(result);
            }

            System.out.println();
        }

        CsvExporter.exportBenchmarkResults(allResults, OUTPUT_FILE);

        System.out.println("Arquivo CSV gerado em: " + OUTPUT_FILE);
    }
}