package org.thebund1st.hankou.winning.lottery.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.math.RoundingMode.HALF_UP;
import static java.util.stream.Collectors.toList;

public class AlwaysWin implements WinningWindowAssembler {

    @Override
    public List<WinningWindow> assemble(List<LotteryPosition> positions, int bound) {
        BigDecimal unavailableChances = positions.stream()
                .filter(p -> !p.isAvailable())
                .map(p -> BigDecimal.valueOf(p.getChance()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<LotteryPosition> availablePositions = positions.stream()
                .filter(LotteryPosition::isAvailable).collect(toList());

        if (availablePositions.isEmpty()) {
            return Collections.emptyList();
        }

        int share = unavailableChances.multiply(BigDecimal.valueOf(bound))
                .divide(BigDecimal.valueOf(availablePositions.size()), HALF_UP)
                .intValue();


        List<WinningWindow> result = new ArrayList<>();
        for (int i = 0; i < availablePositions.size(); i++) {
            LotteryPosition current = availablePositions.get(i);
            int diff = BigDecimal.valueOf(current.getChance())
                    .multiply(BigDecimal.valueOf(bound)).intValue() + share;
            int start;
            int endOfPreviousElement;
            if (i == 0) {
                start = 1;
                endOfPreviousElement = 0;
                result.add(new WinningWindow(start, endOfPreviousElement + diff, current));
            } else if (i == availablePositions.size() - 1) {
                start = result.get(i - 1).getEnd() + 1;
                result.add(new WinningWindow(start, bound, current));
            } else {
                start = result.get(i - 1).getEnd() + 1;
                endOfPreviousElement = result.get(i - 1).getEnd();
                result.add(new WinningWindow(start, endOfPreviousElement + diff, current));
            }

        }
        return result;
    }

}
