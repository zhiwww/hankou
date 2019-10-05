package org.thebund1st.hankou.winning.lottery.domain;

public interface LotteryDrawer {
    LotteryPosition draw(Participating participating);
}
