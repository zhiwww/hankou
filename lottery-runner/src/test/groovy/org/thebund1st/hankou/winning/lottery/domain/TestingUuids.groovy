package org.thebund1st.hankou.winning.lottery.domain

class TestingUuids {

    static String next() {
        UUID.randomUUID().toString().replace("-", "")
    }

}
