package contracts.base

import org.thebund1st.hankou.winning.lottery.adapters.http.AbstractWebMvcTest

import static org.thebund1st.hankou.winning.lottery.application.command.DrawLotteryCommandFixture.aDrawLotteryCommand
import static org.thebund1st.hankou.winning.lottery.domain.LotteryWinningFixture.aLotteryWinningOptional

abstract class LotteryDrawBase extends AbstractWebMvcTest {

    def setup() {
        won()
        noLuck()
    }

    private void won() {
        def command = aDrawLotteryCommand().withCustomer("aba").withCampaign("1").build()
        def won = aLotteryWinningOptional().won().build()
        lotteryDrawCommandHandler.handle(command) >> won
    }

    private void noLuck() {
        def command = aDrawLotteryCommand().withCustomer("abc").withCampaign("1").build()
        def noLuck = aLotteryWinningOptional().noLuck().build()
        lotteryDrawCommandHandler.handle(command) >> noLuck
    }

}
