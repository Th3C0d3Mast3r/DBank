package BankingBased;

import java.util.stream.StreamSupport;

public class Buffers {
    public void bufferFunction() {
        //this is a basic buffer created by loop which would delay the upcoming function
        for (int i = 0; i < 10000; i++) {
            System.out.print("");
            for (int j = 0; j < 5; j++) {
                System.out.print("");
            }
        }
    }
}
