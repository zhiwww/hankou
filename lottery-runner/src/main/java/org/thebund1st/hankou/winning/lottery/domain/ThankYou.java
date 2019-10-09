package org.thebund1st.hankou.winning.lottery.domain;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.thebund1st.hankou.winning.lottery.domain.exception.RewardRunningOutException;

import java.util.List;

@RequiredArgsConstructor
public class ThankYou implements LotteryDrawer {

    private final List<LotteryPosition> lotteryPositions;

    private final LotteryRandom lotteryRandom;

    private final ChancesToWin chancesToWin;

    private final WinningWindowDrawer winningWindowDrawer;
    @Setter
    private int totalPositions = 9;

    @Setter
    private String noLuckRewardItem = "-1";

    @Override
    public LotteryPosition draw(Participating participating) {
        int bound = lotteryRandom.bound(lotteryPositions);
        List<WinningWindow> windows = chancesToWin.assemble(lotteryPositions, bound);
        if (windows.isEmpty()) {
            throw new RewardRunningOutException(participating.getCampaign());
        }
        int draw = lotteryRandom.nextInt(bound);
        return winningWindowDrawer.draw(windows, draw)
                .orElse(chancesToWin.aThankYou(lotteryPositions, totalPositions, noLuckRewardItem));
    }

}
