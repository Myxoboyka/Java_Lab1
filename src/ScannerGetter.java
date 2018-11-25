import java.util.Scanner;

public class ScannerGetter {
    private static ScannerGetter ourInstance = new ScannerGetter();
    public Scanner input;

    public static ScannerGetter getInstance() {

        return ourInstance;
    }

    private ScannerGetter() {
        input = new Scanner(System.in);
    }
}

