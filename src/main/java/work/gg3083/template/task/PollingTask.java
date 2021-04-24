package work.gg3083.template.task;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Gimi
 * @date 2020-11-14 14:29
 * @return 5秒执行一次定时任务
 */
@Component
@Slf4j
public class PollingTask {


    @Scheduled(cron = "0/5 * * * * ?")
    public void task() throws InterruptedException {
        log.info("request polling execute task date : {}", LocalDateTime.now());

    }

}
