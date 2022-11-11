package kwic.assignment.main;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Pipeline {
    private kwic.assignment.main.DSource DSource;
    private List<Filter> LFilter;
    private kwic.assignment.main.DSink DSink;
    private ExecutorService executorService;
    public Pipeline(){
        this.LFilter = new ArrayList<>();
    }
    public Pipeline generateFrom(kwic.assignment.main.DSource DSource){
        this.DSource = DSource;
        return this;
    }
    public Pipeline transformBy(Filter LFilter){
        this.LFilter.add(LFilter);
        return this;
    }
    public Pipeline outputInto(kwic.assignment.main.DSink DSink){
        this.DSink = DSink;
        return this;
    }
    public void run(){
        executorService = Executors.newCachedThreadPool();
        executorService.execute(DSource);
        LFilter.forEach(executorService::execute);
        executorService.execute(DSink);
    }
}
