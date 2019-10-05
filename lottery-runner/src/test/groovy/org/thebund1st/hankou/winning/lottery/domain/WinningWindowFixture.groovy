package org.thebund1st.hankou.winning.lottery.domain

import static org.thebund1st.hankou.winning.lottery.domain.LotteryPositionFixture.aLotteryPosition

class WinningWindowFixture {
    private WinningWindow target = new WinningWindow()

    def withStart(int value) {
        this.target.setStart(value)
        this
    }

    def withEnd(int value) {
        this.target.setEnd(value)
        this
    }

    def with(LotteryPosition value) {
        this.target.setLotteryPosition(value)
        this
    }

    def build() {
        target
    }

    static def aWinningWindow() {
        new WinningWindowFixture()
                .withStart(1)
                .withEnd(10)
                .with(aLotteryPosition().withChance(0.1).build())
    }
}
