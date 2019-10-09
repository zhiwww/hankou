package org.thebund1st.hankou.winning.lottery.domain;

import java.util.List;

public interface WinningWindowAssembler {
    List<WinningWindow> assemble(List<LotteryPosition> positions, int bound);
}
