package org.thebund1st.hankou.winning.lottery.application;

import org.thebund1st.hankou.winning.lottery.application.command.DrawLotteryCommand;
import org.thebund1st.hankou.winning.lottery.domain.Won;

import java.util.Optional;

public interface LotteryDrawCommandHandler {
    Optional<Won> handle(DrawLotteryCommand command);
}
