package org.thebund1st.hankou.winning.lottery.domain;

import java.util.Optional;

public interface RewardVault {
    Optional<Won> claim(Participating participating, LotteryPosition lotteryPosition);
}
