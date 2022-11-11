package kwic.assignment.main;

import java.util.SortedSet;


public class DSink implements Runnable{

    private Pipe<SortedSet<String>> inP;

    private SortedSet<String> temp;

    public DSink(Pipe<SortedSet<String>> in){
        this.inP = in;
    }

    public synchronized void write(){

        if(!inP.buffer.isEmpty()) {

            if(temp!=null && temp.equals(inP.peek())){
                inP.pull();
            }else {

                System.out.println("\nList of Circular Shifts");
                temp = inP.pull();
                temp.forEach(System.out::println);
            }

        }

    }

    @Override
    public void run() {
        while(true) {
            write();

        }
    }
}
