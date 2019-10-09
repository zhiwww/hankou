package org.thebund1st.hankou.winning.lottery.domain.exception;

public class RewardRunningOutException extends RuntimeException {
    public RewardRunningOutException(String campaign) {
        super(String.format("The campaign %s is running out of rewards", campaign));
    }
}
