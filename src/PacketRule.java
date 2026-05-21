import java.util.Objects;

public class PacketRule implements Comparable<PacketRule> {
    private final int id;
    private final String sourceIp;
    private final String destinationIp;
    private final int priority;

    public PacketRule(int id, String sourceIp, String destinationIp, int priority) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID da regra deve ser positivo.");
        }

        if (!isValidIp(sourceIp)) {
            throw new IllegalArgumentException("IP de origem inválido: " + sourceIp);
        }

        if (!isValidIp(destinationIp)) {
            throw new IllegalArgumentException("IP de destino inválido: " + destinationIp);
        }

        if (priority < 0) {
            throw new IllegalArgumentException("A prioridade não pode ser negativa.");
        }

        this.id = id;
        this.sourceIp = sourceIp;
        this.destinationIp = destinationIp;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public String getDestinationIp() {
        return destinationIp;
    }

    public int getPriority() {
        return priority;
    }

    private static boolean isValidIp(String ip) {
        if (ip == null || ip.isBlank()) {
            return false;
        }

        String[] parts = ip.split("\\.");

        if (parts.length != 4) {
            return false;
        }

        for (String part : parts) {
            try {
                int value = Integer.parseInt(part);

                if (value < 0 || value > 255) {
                    return false;
                }
            } catch (NumberFormatException exception) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int compareTo(PacketRule other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof PacketRule)) {
            return false;
        }

        PacketRule other = (PacketRule) object;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PacketRule{" +
                "id=" + id +
                ", sourceIp='" + sourceIp + '\'' +
                ", destinationIp='" + destinationIp + '\'' +
                ", priority=" + priority +
                '}';
    }
}