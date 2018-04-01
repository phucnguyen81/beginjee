package lou.beginjee.batch;

import java.util.logging.Logger;

import javax.batch.api.Batchlet;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Dependent
@Named("MyBatchlet")
public class MyBatchlet implements Batchlet {

    @Inject
    private JobContext jobCtx;

    @Inject
    private Logger log;

    @Override
    public String process() throws Exception {
        String name = jobCtx.getJobName();
        log.info("Processing job " + name);
        return BatchStatus.COMPLETED.toString();
    }

    @Override
    public void stop() throws Exception {
        String name = jobCtx.getJobName();
        log.info("Stopping job " + name);
    }

}