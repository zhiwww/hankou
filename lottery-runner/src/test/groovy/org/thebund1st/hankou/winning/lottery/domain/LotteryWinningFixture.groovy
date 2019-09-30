package org.thebund1st.hankou.winning.lottery.domain

class LotteryWinningFixture {
    private boolean won = false

    def won() {
        this.won = true
        this
    }

    def noLuck() {
        this.won = false
        this
    }

    def build() {
        won ? Optional.of(new LotteryWinning()) : Optional.empty()
    }

    static def aLotteryWinningOptional() {
        new LotteryWinningFixture()
                .noLuck()
    }
}
