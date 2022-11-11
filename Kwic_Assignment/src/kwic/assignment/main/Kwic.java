package kwic.assignment.main;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.stream.Collectors;

public class Kwic {

    public static void main(String[] args) {

        //printHeader(args);
        System.out.println("Press Enter to insert new Line.\n Press CTRL+D to exit.");
        String[] ignoreWords={"a", "an", "the", "and", "or", "of", "to", "be", "is", "in", "out", "by", "as", "at", "off"};
        System.out.println("Ignoring words: ["+ Arrays.stream(ignoreWords).collect(Collectors.joining(", "))+"]");
        System.out.println("Input new Line:");
        new Kwic(ignoreWords);
    }

    public Kwic(String... ignoreWords) {
        Pipe<String> s1 = new Pipe<>();
        Pipe<String> s2 = new Pipe<>();
        Pipe<SortedSet<String>> s3 = new Pipe<>();

        DSource DSource = new DSource(s1);
        Filter circShift = new CircShift(s1, s2, ignoreWords);
        Filter alphabet = new Alphabet(s2, s3);
        DSink DSink = new DSink(s3);

        Pipeline pipeline = new Pipeline();
        pipeline.generateFrom(DSource).transformBy(circShift).transformBy(alphabet).outputInto(DSink).run();
    }
}
