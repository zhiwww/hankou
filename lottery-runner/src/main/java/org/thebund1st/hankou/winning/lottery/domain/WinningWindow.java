package org.thebund1st.hankou.winning.lottery.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class WinningWindow {
    private int start;

    private int end;

    private LotteryPosition lotteryPosition;

    public WinningWindow(int start, int end, LotteryPosition position) {
        this.start = start;
        this.end = end;
        this.lotteryPosition = position;
    }

    public boolean contains(int draw) {
        return start <= draw && draw <= end;
    }
}
