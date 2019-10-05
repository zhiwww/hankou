package org.thebund1st.hankou.winning.lottery.domain;

import java.security.SecureRandom;
import java.util.List;

public class LotteryRandom {

    private final SecureRandom rand = new SecureRandom();

    public int bound(List<LotteryPosition> positions) {
        return positions.stream()
                .map(p -> Double.toString(Math.abs(p.getChance())))
                .map(s -> s.split("\\.")[1])
                .map(String::length)
                .max(Integer::compareTo)
                .map(m -> (int) Math.pow(10, m))
                .orElse(0);
    }

    public int nextInt(int bound) {
        return rand.nextInt(bound);
    }
}
