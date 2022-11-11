package kwic.assignment.main;

abstract public class Filter<S, T> implements Runnable {

    protected Pipe<S> inP;
    protected Pipe<T> outP;

    public Filter(Pipe<S> inP, Pipe<T> outP) {
        this.inP = inP;
        this.outP = outP;
    }

    abstract void filter();

    @Override
    public void run() {
        while(true) {
            filter();
        }

    }
}
