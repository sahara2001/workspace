package server;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class testOne {

    @Test
    public void test() throws IOException {
        System.out.println("main");
        String[] args = null;
        final InputStream original = System.in;
        final FileInputStream fips = new FileInputStream(
                new File("C:\\Users\\santee\\Documents\\test1.txt"));
        System.setIn(fips);
        ABCDGuesser2.main(args);
        System.setIn(original);
    }

}
