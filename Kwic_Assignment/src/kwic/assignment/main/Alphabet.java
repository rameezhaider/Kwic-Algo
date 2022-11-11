package kwic.assignment.main;

import java.util.SortedSet;
import java.util.TreeSet;


public class Alphabet extends Filter<String, SortedSet<String>> {

    private SortedSet<String> sortedSet = new TreeSet<>();

    public Alphabet(Pipe<String> in, Pipe<SortedSet<String>> out) {
        super(in, out);
    }

    @Override
    public synchronized void filter() {
        if(!inP.buffer.isEmpty()){
            String s = inP.pull();
            sortedSet.add(s);

            outP.push(new TreeSet<>(sortedSet));
        }

    }

}
