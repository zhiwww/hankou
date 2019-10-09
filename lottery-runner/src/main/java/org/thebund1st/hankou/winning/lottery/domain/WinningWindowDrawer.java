package org.thebund1st.hankou.winning.lottery.domain;

import java.util.List;
import java.util.Optional;

public class WinningWindowDrawer {

    public Optional<LotteryPosition> draw(List<WinningWindow> windows, int draw) {
        return windows.stream()
                .filter(w -> w.contains(draw))
                .map(WinningWindow::getLotteryPosition)
                .findFirst();//FIXME what if something goes wrong
    }


}
