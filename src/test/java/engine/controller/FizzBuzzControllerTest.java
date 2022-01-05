package engine.controller;

import com.google.gson.Gson;
import engine.object.CounterRequest;
import engine.services.FizzBuzz;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FizzBuzzController.class)
public class FizzBuzzControllerTest {

    private static final String INT1_TAG = "int1";
    private static final String INT2_TAG = "int2";
    private static final String LIMIT_TAG = "limit";
    private static final String STR1_TAG = "str1";
    private static final String STR2_TAG = "str2";

    @SpyBean
    FizzBuzz fizzBuzz;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MeterRegistry registry;

    @Test
    public void testcontrollerincrementCounter() throws Exception {
        this.mockMvc.perform(get("/fizzbuzz/int1/2/int2/5/limit/10/str1/fizz/str2/buzz"));
        final Counter requestsCounter = registry.find("fizzbuzz_requests").tags(INT1_TAG, "2", INT2_TAG, "5", LIMIT_TAG, "10", STR1_TAG, "fizz", STR2_TAG, "buzz").counter();
        assertEquals(requestsCounter.count(), 1.0);
    }

    @Test
    public void testcontrollerWithFirstParamTo0() throws Exception {
        MvcResult response = this.mockMvc.perform(get("/fizzbuzz/int1/0/int2/2/limit/10/str1/fizz/str2/buzz")).
                andDo(MockMvcResultHandlers.print()).andExpect(status().isBadRequest()).andReturn();
        assertEquals("first number should be different of 0", response.getResponse().getContentAsString());
    }

    @Test
    public void testcontrollerWithSecondParamTo0() throws Exception {
        MvcResult response = this.mockMvc.perform(get("/fizzbuzz/int1/1/int2/0/limit/10/str1/fizz/str2/buzz")).
                andDo(MockMvcResultHandlers.print()).andExpect(status().isBadRequest()).andReturn();
        assertEquals("second number should different of 0", response.getResponse().getContentAsString());
    }

    @Test
    public void testcontrollerWrongPath() throws Exception {
        MvcResult response = this.mockMvc.perform(get("/fizzbuzz/toto")).
                andDo(MockMvcResultHandlers.print()).andExpect(status().isBadRequest()).andReturn();
        assertEquals("Wrong request, please check the available requests in http://localhost:8081/swagger-ui.html", response.getResponse().getContentAsString());
    }

    @Test
    public void testControllerWithParamString() throws Exception {
        MvcResult response = this.mockMvc.perform(get("/fizzbuzz/int1/str/int2/2/limit/10/str1/fizz/str2/buzz")).
                andDo(MockMvcResultHandlers.print()).andExpect(status().isBadRequest()).andReturn();
        assertEquals("Problem with input parameter please check the parameters", response.getResponse().getContentAsString());
    }

    @Test
    public void testRedirectToGrafanaDashBoard() throws Exception {
        this.mockMvc.perform(get("/fizzbuzz/grafana")).andDo(MockMvcResultHandlers.print()).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost:3000/d/fizzbuzz"));
    }

    @Test
    public void testcontrollerMostRequest() throws Exception {
        for (int i = 0; i <= 4; i++) {
            this.mockMvc.perform(get("/fizzbuzz/int1/3/int2/5/limit/10/str1/fizz/str2/buzz"));
        }
        for (int i = 0; i <= 2; i++) {
            this.mockMvc.perform(get("/fizzbuzz/int1/2/int2/4/limit/10/str1/toto/str2/tata"));
        }
        MvcResult response = this.mockMvc.perform(get("/fizzbuzz/RequestMostCalled")).
                andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andReturn();
        String json = response.getResponse().getContentAsString();
        Gson gson = new Gson();
        CounterRequest[] requests = gson.fromJson(json, CounterRequest[].class);
        CounterRequest request = requests[0];
        assertEquals(5, request.getNumberOfCall());
        assertEquals("3", request.getInt1());
        assertEquals("5", request.getInt2());
        assertEquals("10", request.getLimit());
        assertEquals("fizz", request.getStr1());
        assertEquals("buzz", request.getStr2());


    }

    @TestConfiguration
    static class AdditionalConfig {
        @Bean
        public MeterRegistry registry() {
            return new SimpleMeterRegistry();
        }
    }


}
