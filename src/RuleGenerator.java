import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RuleGenerator {
    public static final long DEFAULT_SEED = 20240520L;

    private RuleGenerator() {
    }

    public static List<PacketRule> generateOrderedRules(int amount, long seed) {
        if (amount <= 0) {
            throw new IllegalArgumentException("A quantidade de regras deve ser positiva.");
        }

        Random random = new Random(seed);
        List<PacketRule> rules = new ArrayList<>();

        for (int id = 1; id <= amount; id++) {
            String sourceIp = generateIp(random);
            String destinationIp = generateIp(random);
            int priority = 1 + random.nextInt(1000);

            rules.add(new PacketRule(id, sourceIp, destinationIp, priority));
        }

        return rules;
    }

    private static String generateIp(Random random) {
        int first = 10 + random.nextInt(180);
        int second = random.nextInt(256);
        int third = random.nextInt(256);
        int fourth = random.nextInt(256);

        return first + "." + second + "." + third + "." + fourth;
    }
}
