public class BenchmarkResult {
    private final String treeName;
    private final int amount;
    private final long insertTimeNs;
    private final long searchTimeNs;
    private final long deleteTimeNs;
    private final long rotationCount;
    private final int height;
    private final int finalSize;

    public BenchmarkResult(
            String treeName,
            int amount,
            long insertTimeNs,
            long searchTimeNs,
            long deleteTimeNs,
            long rotationCount,
            int height,
            int finalSize
    ) {
        this.treeName = treeName;
        this.amount = amount;
        this.insertTimeNs = insertTimeNs;
        this.searchTimeNs = searchTimeNs;
        this.deleteTimeNs = deleteTimeNs;
        this.rotationCount = rotationCount;
        this.height = height;
        this.finalSize = finalSize;
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

    public long getDeleteTimeNs() {
        return deleteTimeNs;
    }

    public long getRotationCount() {
        return rotationCount;
    }

    public int getHeight() {
        return height;
    }

    public int getFinalSize() {
        return finalSize;
    }

    @Override
    public String toString() {
        return treeName +
                " | entradas=" + amount +
                " | insercao(ns)=" + insertTimeNs +
                " | busca(ns)=" + searchTimeNs +
                " | remocao20(ns)=" + deleteTimeNs +
                " | tamanhoFinal=" + finalSize +
                " | rotacoes=" + rotationCount +
                " | altura=" + height;
    }
}