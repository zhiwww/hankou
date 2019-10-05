package org.thebund1st.hankou.winning.lottery.domain;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class LotteryWinningWindow {

    public List<WinningWindow> assemble(List<LotteryPosition> positions, int bound) {
        List<WinningWindow> result = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            LotteryPosition current = positions.get(i);
            int diff = BigDecimal.valueOf(current.getChance())
                    .multiply(BigDecimal.valueOf(bound)).intValue();
            int start;
            int endOfPreviousElement;
            if (i == 0) {
                start = 1;
                endOfPreviousElement = 0;
            } else {
                start = result.get(i - 1).getEnd() + 1;
                endOfPreviousElement = result.get(i - 1).getEnd();
            }
            result.add(new WinningWindow(start, endOfPreviousElement + diff, current));
        }
        return result;
    }

    public Optional<LotteryPosition> draw(List<WinningWindow> windows, int draw) {
        return windows.stream()
                .filter(w -> w.contains(draw))
                .map(WinningWindow::getLotteryPosition)
                .findFirst();//FIXME what if something goes wrong
    }

    public LotteryPosition aThankYou(List<WinningWindow> windows, int totalPositions, String noLuckRewardItem) {
        IntStream total = IntStream.range(1, totalPositions + 1);
        List<Integer> positions = windows.stream()
                .map(w -> w.getLotteryPosition().getPosition()).collect(toList());

        int[] thankYouGroup = total.filter(item -> !positions.contains(item)).toArray();
        if (thankYouGroup.length == 0) {
            return null;//should never be here
        } else {
            int position = thankYouGroup[new SecureRandom().nextInt(thankYouGroup.length)];
            return new LotteryPosition(position, noLuckRewardItem);
        }
    }
}
