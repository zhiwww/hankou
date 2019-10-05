package org.thebund1st.hankou.winning.lottery.application.impl;

import lombok.RequiredArgsConstructor;
import org.thebund1st.hankou.winning.lottery.application.LotteryDrawCommandHandler;
import org.thebund1st.hankou.winning.lottery.application.command.DrawLotteryCommand;
import org.thebund1st.hankou.winning.lottery.domain.LotteryDrawer;
import org.thebund1st.hankou.winning.lottery.domain.LotteryDrawerFinder;
import org.thebund1st.hankou.winning.lottery.domain.LotteryPosition;
import org.thebund1st.hankou.winning.lottery.domain.Participating;
import org.thebund1st.hankou.winning.lottery.domain.ParticipatingStore;
import org.thebund1st.hankou.winning.lottery.domain.RewardVault;
import org.thebund1st.hankou.winning.lottery.domain.Won;

import java.util.Optional;

@RequiredArgsConstructor
public class DefaultLotteryDrawCommandHandler implements LotteryDrawCommandHandler {

    private final LotteryDrawerFinder lotteryDrawerFinder;
    private final ParticipatingStore participatingStore;
    private final RewardVault rewardVault;

    @Override
    public Optional<Won> handle(DrawLotteryCommand command) {
        LotteryDrawer drawer = lotteryDrawerFinder.findWith(command.getCampaign());
        Participating participating = participatingStore.participate(command.getCampaign(), command.getCustomer());
        LotteryPosition position = drawer.draw(participating);
        return rewardVault.claim(participating, position);
    }
}
