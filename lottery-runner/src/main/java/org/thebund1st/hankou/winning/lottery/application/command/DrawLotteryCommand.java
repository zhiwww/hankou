package org.thebund1st.hankou.winning.lottery.application.command;

import lombok.Data;

@Data
public class DrawLotteryCommand {

    private String campaign;
    private String customer;
}
