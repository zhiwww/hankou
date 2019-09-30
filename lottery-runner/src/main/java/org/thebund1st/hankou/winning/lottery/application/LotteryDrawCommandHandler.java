package org.thebund1st.hankou.winning.lottery.application;

import org.thebund1st.hankou.winning.lottery.application.command.DrawLotteryCommand;
import org.thebund1st.hankou.winning.lottery.domain.LotteryWinning;

import java.util.Optional;

public interface LotteryDrawCommandHandler {
    Optional<LotteryWinning> handle(DrawLotteryCommand command);
}
