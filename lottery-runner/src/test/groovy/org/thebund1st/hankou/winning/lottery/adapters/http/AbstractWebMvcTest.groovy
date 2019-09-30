package org.thebund1st.hankou.winning.lottery.adapters.http

import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.thebund1st.hankou.winning.lottery.application.LotteryDrawCommandHandler
import spock.lang.Specification

@WebMvcTest
@Import([MockMvcBuilderCustomizers])
abstract class AbstractWebMvcTest extends Specification {

    @Autowired
    protected MockMvc mockMvc

    @SpringBean
    protected LotteryDrawCommandHandler lotteryDrawCommandHandler = Mock()


    def setup() {
        RestAssuredMockMvc.mockMvc(mockMvc)
    }
}
