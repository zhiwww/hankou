package org.thebund1st.hankou.winning.lottery.domain

import spock.lang.Specification
import spock.lang.Unroll

import static org.thebund1st.hankou.winning.lottery.domain.LotteryPositionFixture.aLotteryPosition

class ChancesToWinTest extends Specification {
    private ChancesToWin subject = new ChancesToWin()

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
        positions                           | bound | windows
        [[1, 0.15, true], [2, 0.2, true]]   | 100   | [[1, 1, 15], [2, 16, 35]]
        [[1, 0.151, true], [2, 0.2, true]]  | 1000  | [[1, 1, 151], [2, 152, 351]]
        []                                  | 0     | []
        [[1, 0.15, false], [2, 0.2, false]] | 100   | []
        [[1, 0.15, true], [2, 0.2, false]]  | 100   | [[1, 1, 15]]
    }

    @Unroll("it should return random thank you thankYouPositions when no luck")
    def "it should return random thank you thankYouPositions when no luck"(def positions, int number, def thankYouPositions) {
        expect:
        def positionGroup = positions.collect { it ->
            aLotteryPosition().withPosition(it.get(0)).withChance(it.get(1)).withAvailable(it.get(2)).build()
        }
        def thankYou = subject.aThankYou(positionGroup, number, "1")
        if (thankYou == null) {
            assert thankYou == thankYouPositions
        } else {
            assert thankYouPositions.toList().contains(thankYou.position)
        }

        where:
        positions                                            | number | thankYouPositions
        [[1, 0.15, true], [2, 0.2, true]]                    | 2      | null
        [[1, 0.15, true], [2, 0.2, true]]                    | 3      | [3]
        [[1, 0.15, true], [2, 0.2, false]]                   | 3      | [3]
        [[1, 0.15, true], [2, 0.2, true]]                    | 9      | [3, 4, 5, 6, 7, 8, 9]
        [[1, 0.15, true], [2, 0.2, true], [5, 0.2, true]]    | 9      | [3, 4, 6, 7, 8, 9]
        [[1, 0.15, false], [2, 0.2, false], [5, 0.2, false]] | 9      | [3, 4, 6, 7, 8, 9]
    }
}
