package org.thebund1st.hankou.winning.lottery.domain;

import lombok.Data;

@Data
public class Won {
    private int position;
    private String item;
    private int quantity;
}
