package org.thebund1st.hankou.winning.lottery.boot.application;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thebund1st.hankou.winning.lottery.application.LotteryDrawCommandHandler;

import java.util.Optional;

@RequiredArgsConstructor
@Configuration
public class ApplicationConfiguration {

    @ConditionalOnMissingBean(LotteryDrawCommandHandler.class)
    @Bean
    public LotteryDrawCommandHandler defaultLotteryDrawCommandHandler() {
        return command -> Optional.empty();
    }

}
