package org.thebund1st.hankou.winning.lottery.application.command

import static org.thebund1st.hankou.winning.lottery.domain.TestingCampaigns.aCampaignId
import static org.thebund1st.hankou.winning.lottery.domain.TestingCustomers.aCustomerId

class DrawLotteryCommandFixture {
    private DrawLotteryCommand target = new DrawLotteryCommand()

    def withCustomer(String value) {
        target.setCustomer(value)
        this
    }

    def withCampaign(String value) {
        target.setCampaign(value)
        this
    }

    def build() {
        target
    }

    static def aDrawLotteryCommand() {
        new DrawLotteryCommandFixture()
                .withCampaign(aCampaignId())
                .withCustomer(aCustomerId())
    }
}
