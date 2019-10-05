package org.thebund1st.hankou.winning.lottery.application.impl

import org.thebund1st.hankou.winning.lottery.domain.LotteryDrawer
import org.thebund1st.hankou.winning.lottery.domain.LotteryDrawerFinder
import org.thebund1st.hankou.winning.lottery.domain.LotteryPosition
import org.thebund1st.hankou.winning.lottery.domain.ParticipatingStore
import org.thebund1st.hankou.winning.lottery.domain.RewardVault
import spock.lang.Specification

import static org.thebund1st.hankou.winning.lottery.application.command.DrawLotteryCommandFixture.aDrawLotteryCommand
import static org.thebund1st.hankou.winning.lottery.domain.LotteryPositionFixture.aLotteryPosition
import static org.thebund1st.hankou.winning.lottery.domain.LotteryWinningFixture.aLotteryWinningOptional
import static org.thebund1st.hankou.winning.lottery.domain.ParticipatingFixture.aParticipanting

class DefaultLotteryDrawCommandHandlerTest extends Specification {

    private DefaultLotteryDrawCommandHandler subject

    private LotteryDrawerFinder lotteryDrawerFinder = Mock()

    private ParticipatingStore participatingStore = Mock()

    private LotteryDrawer lotteryDrawer = Mock()

    private RewardVault rewardVault = Mock()

    def setup() {
        subject = new DefaultLotteryDrawCommandHandler(lotteryDrawerFinder, participatingStore, rewardVault)
    }

    def "it should return winning"() {
        given:
        def command = aDrawLotteryCommand().build()

        and:
        lotteryDrawerFinder.findWith(command.getCampaign()) >> lotteryDrawer

        and:
        def participating = aParticipanting()
                .withCampaign(command.getCampaign())
                .withCustomer(command.getCustomer()).build()
        participatingStore.participate(command.getCampaign(), command.getCustomer()) >> participating

        and:
        def position = aLotteryPosition().build()
        lotteryDrawer.draw(participating) >> position

        and:
        rewardVault.claim(participating, position) >> aLotteryWinningOptional().won().build()

        when:
        def winningOptional = subject.handle(command)

        then:
        assert winningOptional.isPresent()
    }

    def "it should return empty given no luck"() {
        given:
        def command = aDrawLotteryCommand().build()

        and:
        lotteryDrawerFinder.findWith(command.getCampaign()) >> lotteryDrawer

        and:
        def participating = aParticipanting()
                .withCampaign(command.getCampaign())
                .withCustomer(command.getCustomer()).build()
        participatingStore.participate(command.getCampaign(), command.getCustomer()) >> participating

        and:
        def position = aLotteryPosition().build()
        lotteryDrawer.draw(participating) >> position

        and:
        rewardVault.claim(participating, position) >> aLotteryWinningOptional().noLuck().build()

        when:
        def winningOptional = subject.handle(command)

        then:
        assert !winningOptional.isPresent()
    }
}
