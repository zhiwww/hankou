package org.thebund1st.hankou.winning.lottery.adapters.http.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thebund1st.hankou.winning.lottery.adapters.http.resource.LotteryDrawResource;
import org.thebund1st.hankou.winning.lottery.application.LotteryDrawCommandHandler;
import org.thebund1st.hankou.winning.lottery.application.command.DrawLotteryCommand;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LotteryDrawRestController {

    private final LotteryDrawCommandHandler lotteryDrawCommandHandler;

    @PostMapping("/winning/lottery/draw")
    public LotteryDrawResource handle(@Valid @RequestBody DrawLotteryCommand command) {
        return lotteryDrawCommandHandler.handle(command)
                .map(LotteryDrawResource::from)
                .orElse(LotteryDrawResource.noLuck());
    }
}
