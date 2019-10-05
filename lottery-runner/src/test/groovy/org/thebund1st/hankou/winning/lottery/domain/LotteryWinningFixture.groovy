package org.thebund1st.hankou.winning.lottery.domain

class LotteryWinningFixture {
    private boolean won = false
    private Won target = new Won()

    def won() {
        this.won = true
        this
    }

    def noLuck() {
        this.won = false
        this
    }

    def withQuantity(int value) {
        target.setQuantity(value)
        this
    }

    def withItem(String value) {
        target.setItem(value)
        this
    }

    def build() {
        won ? Optional.of(target) : Optional.empty()
    }

    static def aLotteryWinningOptional() {
        new LotteryWinningFixture()
                .noLuck()
    }
}
