package cn.xux.thread.other.quartz;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 定义一个任务
 */
public class HelloJob implements Job {

    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);

    public HelloJob() { }

    public void execute(JobExecutionContext context)
        throws JobExecutionException {
        _log.info("---------任务开始-----------" + new Date());
        for(int i=1;i<=10;i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            _log.info("任务进度：" + i);
        }
        _log.info("---------任务结束-----------" + new Date());
    }

}
