package org.thebund1st.hankou.winning.lottery.domain

import spock.lang.Specification
import spock.lang.Unroll

import static org.thebund1st.hankou.winning.lottery.domain.LotteryPositionFixture.aLotteryPosition

class AlwaysWinTest extends Specification {
    private AlwaysWin subject = new AlwaysWin()

    @Unroll("it should return #windows with #positions")
    def "it should return winning window"(def positions, int bound, def windows) {
        expect:
        def positionGroup = positions.collect { it ->
            aLotteryPosition().withPosition(it.get(0)).withChance(it.get(1)).withAvailable(it.get(2)).build()
        }
        def actual = subject.assemble(positionGroup, bound).collect {
            [it.lotteryPosition.position, it.start, it.end]
        }.toArray()
        assert actual == windows

        where:
        positions                                                           | bound | windows
        [[1, 0.15, true], [2, 0.85, true]]                                  | 100   | [[1, 1, 15], [2, 16, 100]]
        [[1, 0.151, true], [2, 0.849, true]]                                | 1000  | [[1, 1, 151], [2, 152, 1000]]
        []                                                                  | 0     | []
        [[1, 0.2, true], [2, 0.1, false], [3, 0.2, true], [4, 0.5, true]]   | 10    | [[1, 1, 2], [3, 3, 4], [4, 5, 10]]
        [[1, 0.21, true], [2, 0.1, false], [3, 0.19, true], [4, 0.5, true]] | 100   | [[1, 1, 24], [3, 25, 46], [4, 47, 100]]
    }

}
