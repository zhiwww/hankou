package org.thebund1st.hankou.winning.lottery.domain

import spock.lang.Specification
import spock.lang.Unroll

import static org.thebund1st.hankou.winning.lottery.domain.LotteryPositionFixture.aLotteryPosition

class LotteryRandomTest extends Specification {
    private LotteryRandom subject = new LotteryRandom()

    @Unroll("it should return #bound with #chances")
    def "it should return max num of decimal digits as random bound"(List<Double> chances,
                                                                     int bound) {
        expect:
        def positions = chances.collect { it ->
            aLotteryPosition().withChance(it).build()
        }
        assert subject.bound(positions) == bound

        where:
        chances              | bound
        [0.1, 0.2, 0.3]      | 10
        [0.15, 0.2, 0.3]     | 100
        [0.151, 0.2, 0.3]    | 1000
        [0.151, 0.2423, 0.3] | 10000
        []                   | 0
    }
}
