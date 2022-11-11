package kwic.assignment.main;

import java.util.Scanner;
public class DSource implements Runnable {

    private Scanner scan;

    protected Pipe<String> outP;

    public DSource(Pipe<String> out) {
        this.outP = out;
        this.scan = new Scanner(System.in);
    }

    protected synchronized void read() {
        outP.push(scan.nextLine());
    }

    @Override
    public void run() {
        while(scan.hasNext()){
            read();
        }

        System.out.println("Exit from the program");

        System.exit(0);
    }
}
