package com.managment.pricing.integration;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceApiIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void validateRevenueYieldRoute() throws Exception {
        String expectedContent = "[{\"oilId\":\"AAC\",\"revenueYield\":0.008130081300813009}," +
                "{\"oilId\":\"REW\",\"revenueYield\":0.056910569105691054}," +
                "{\"oilId\":\"BWO\",\"revenueYield\":0.056910569105691054}," +
                "{\"oilId\":\"TIM\",\"revenueYield\":0.06317073170731707}," +
                "{\"oilId\":\"TIM\",\"revenueYield\":0.17886178861788618}]";

        this.mockMvc.perform(MockMvcRequestBuilders.get("/price/revenue-yield/123")
                .accept("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
          .andExpect(MockMvcResultMatchers.content().string(expectedContent));

    }

    @Test
    public void validateEarningRatioPriceRoute() throws Exception {
        String expectedContent = "[{\"oilId\":\"AAC\",\"priceEarningRatio\":15128.999999999998}," +
                "{\"oilId\":\"REW\",\"priceEarningRatio\":2161.285714285714}," +
                "{\"oilId\":\"BWO\",\"priceEarningRatio\":2161.285714285714}," +
                "{\"oilId\":\"TIM\",\"priceEarningRatio\":1947.1042471042472}," +
                "{\"oilId\":\"TIM\",\"priceEarningRatio\":687.6818181818181}]";

        this.mockMvc.perform(MockMvcRequestBuilders.get("/price/earning-ratio/123")
                .accept("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));

    }
}
