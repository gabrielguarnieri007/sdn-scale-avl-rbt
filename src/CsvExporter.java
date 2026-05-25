import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvExporter {
    private CsvExporter() {
    }

    public static void exportBenchmarkResults(List<BenchmarkResult> results, String filePath) {
        if (results == null || results.isEmpty()) {
            throw new IllegalArgumentException("A lista de resultados não pode ser vazia.");
        }

        File file = new File(filePath);
        File parentDirectory = file.getParentFile();

        if (parentDirectory != null && !parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("estrutura,entradas,tempo_insercao_ns,tempo_busca_ns,tempo_remocao_20_ns,tamanho_final,rotacoes,altura");

            for (BenchmarkResult result : results) {
                writer.println(
                        result.getTreeName() + "," +
                        result.getAmount() + "," +
                        result.getInsertTimeNs() + "," +
                        result.getSearchTimeNs() + "," +
                        result.getDeleteTimeNs() + "," +
                        result.getFinalSize() + "," +
                        result.getRotationCount() + "," +
                        result.getHeight()
                );
            }
        } catch (IOException exception) {
            throw new RuntimeException("Erro ao exportar CSV: " + filePath, exception);
        }
    }
}