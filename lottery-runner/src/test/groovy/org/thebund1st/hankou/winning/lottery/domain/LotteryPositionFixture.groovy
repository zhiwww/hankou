package org.thebund1st.hankou.winning.lottery.domain

class LotteryPositionFixture {
    private LotteryPosition target = new LotteryPosition()

    def withPosition(int value) {
        this.target.setPosition(value)
        this
    }

    def withChance(double value) {
        this.target.setChance(value)
        this
    }

    def withRewardItem(String value) {
        this.target.setRewardItem(value)
        this
    }

    def build() {
        target
    }

    static def aLotteryPosition() {
        new LotteryPositionFixture()
                .withPosition(1)
                .withChance(0.01)
                .withRewardItem("1")
    }
}
