package threads.virtualthread;

public class Sample {
    public static void main(String args[]) {
        Thread virtualThread = Thread.ofVirtual().start(() -> {
            System.out.println("hi, hi, hi, hi, yes");
        });
    }
}