package org.thebund1st.hankou.winning.lottery.domain

import spock.lang.Specification
import spock.lang.Unroll

import static org.thebund1st.hankou.winning.lottery.domain.LotteryPositionFixture.aLotteryPosition
import static org.thebund1st.hankou.winning.lottery.domain.WinningWindowFixture.aWinningWindow

class WinningWindowDrawerTest extends Specification {
    private WinningWindowDrawer subject = new WinningWindowDrawer()

    @Unroll("it should return #windows with #number and #position")
    def "it should return window matched"(def windows, int number, def position) {
        expect:
        def windowGroup = windows.collect { it ->
            aWinningWindow().withStart(it.get(1)).withEnd(it.get(2))
                    .with(aLotteryPosition().withPosition(it.get(0)).build()).build()
        }
        assert subject.draw(windowGroup, number).map { it.position }.orElse(0) == position

        where:
        windows                   | number | position
        [[1, 1, 15], [2, 16, 35]] | 15     | 1
        [[1, 1, 15], [2, 16, 35]] | 16     | 2
        [[1, 1, 15], [2, 16, 35]] | 36     | 0
    }
}
