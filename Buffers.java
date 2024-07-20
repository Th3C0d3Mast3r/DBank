package BankingBased;

import java.util.stream.StreamSupport;

public class Buffers {
    public void bufferFunction() {
        for (int i = 0; i < 10000; i++) {
            System.out.print("");
            for (int j = 0; j < 5; j++) {
                System.out.print("");
            }
        }
    }
}
