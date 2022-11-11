package kwic.assignment.main;

public class CircShift extends Filter<String, String> {

    public final String[] STOP_WORDS;

    public CircShift(Pipe<String> in, Pipe<String> out, String... stopWords) {
        super(in, out);
        this.STOP_WORDS = stopWords;
    }

    @Override
    public synchronized void filter() {
        if (!inP.buffer.isEmpty()) {

            String line = inP.pull();

            String[] words = pullWords(line);
            for (int i = 0; i < words.length; i++) {
                String firstWord = words[0];
                if (!isStopWord(firstWord)) {
                    String shiftedSentence = String.join(" ", words);
                    outP.push(shiftedSentence);
                }

                String[] shiftedWords = leftTransfer(words);
                System.arraycopy(shiftedWords, 0, words, 0, shiftedWords.length);
            }

        }

    }

    private String[] leftTransfer(String[] s) {

        String[] transferedWords = new String[s.length];

        if (s.length > 1) {
            System.arraycopy(s, 1, transferedWords, 0, s.length - 1);
            System.arraycopy(s, 0, transferedWords, transferedWords.length - 1, 1);
        }

        return transferedWords;
    }

    private String[] pullWords(String s) {
        return s.split("\\s+");
    }

    private boolean isStopWord(String s) {
        for (String stopWord : STOP_WORDS) {

            if (s.equalsIgnoreCase(stopWord)) {
                return true;
            }
        }

        return false;
    }
}
