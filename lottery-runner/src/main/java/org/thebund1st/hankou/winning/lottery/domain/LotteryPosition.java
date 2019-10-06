package org.thebund1st.hankou.winning.lottery.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class LotteryPosition {

    private int position = 1;
    /**
     * chance in percentage, starts from 0 to 1
     */
    private double chance;

    private boolean available;

    private String rewardItem;

    public LotteryPosition(int position, String rewardItem) {
        this.position = position;
        this.rewardItem = rewardItem;
    }
}
