public class BenchmarkResult {
    private final String treeName;
    private final int amount;
    private final long insertTimeNs;
    private final long searchTimeNs;
    private final long rotationCount;
    private final int height;

    public BenchmarkResult(
            String treeName,
            int amount,
            long insertTimeNs,
            long searchTimeNs,
            long rotationCount,
            int height
    ) {
        this.treeName = treeName;
        this.amount = amount;
        this.insertTimeNs = insertTimeNs;
        this.searchTimeNs = searchTimeNs;
        this.rotationCount = rotationCount;
        this.height = height;
    }

    public String getTreeName() {
        return treeName;
    }

    public int getAmount() {
        return amount;
    }

    public long getInsertTimeNs() {
        return insertTimeNs;
    }

    public long getSearchTimeNs() {
        return searchTimeNs;
    }

    public long getRotationCount() {
        return rotationCount;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return treeName +
                " | entradas=" + amount +
                " | insercao(ns)=" + insertTimeNs +
                " | busca(ns)=" + searchTimeNs +
                " | rotacoes=" + rotationCount +
                " | altura=" + height;
    }
}
