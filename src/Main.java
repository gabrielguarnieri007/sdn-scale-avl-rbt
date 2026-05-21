public class Main {
    public static void main(String[] args) {
        PacketRule rule = new PacketRule(1, "192.168.0.10", "10.0.0.5", 100);

        System.out.println("SDN-Scale: AVL vs Red-Black");
        System.out.println("Regra criada: " + rule);
    }
}