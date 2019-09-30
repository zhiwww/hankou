package org.thebund1st.hankou.winning.lottery.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@ComponentScan(basePackages = {
        "org.thebund1st.hankou.winning.lottery.adapters.http.controller"
})
@Import({

})
@Configuration
public class LotteryRunnerAutoConfiguration {


}
