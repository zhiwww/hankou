package org.thebund1st.hankou.winning.lottery.domain;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
public class ThankYou implements LotteryDrawer {

    private final List<LotteryPosition> lotteryPositions;

    private final LotteryRandom lotteryRandom;

    private final LotteryWinningWindow lotteryWinningWindow;
    @Setter
    private int totalPositions = 9;

    @Setter
    private String noLuckRewardItem = "-1";

    @Override
    public LotteryPosition draw(Participating participating) {
        int bound = lotteryRandom.bound(lotteryPositions);
        List<WinningWindow> windows = lotteryWinningWindow.assemble(lotteryPositions, bound);
        int draw = lotteryRandom.nextInt(bound);
        return lotteryWinningWindow.draw(windows, draw)
                .orElse(lotteryWinningWindow.aThankYou(windows, totalPositions, noLuckRewardItem));
    }

}
