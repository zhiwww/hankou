package org.thebund1st.hankou.winning.lottery.domain


import static org.thebund1st.hankou.winning.lottery.domain.TestingCampaigns.aCampaignId
import static org.thebund1st.hankou.winning.lottery.domain.TestingCustomers.aCustomerId

class ParticipatingFixture {
    private Participating target = new Participating()

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

    static def aParticipanting() {
        new ParticipatingFixture()
                .withCampaign(aCampaignId())
                .withCustomer(aCustomerId())
    }
}
