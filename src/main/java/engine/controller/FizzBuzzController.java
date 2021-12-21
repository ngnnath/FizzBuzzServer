package engine.controller;

import engine.errors.FizzbuzzParamException;
import engine.errors.WrongParamRequestException;
import engine.object.CounterRequest;
import engine.services.FizzBuzz;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * FizzBuzzController.
 */
@RestController
public class FizzBuzzController {

    /**
     * Logger.
     */
    private final static Logger LOG = LoggerFactory.getLogger(FizzBuzzController.class);

    /**
     * Tags.
     */
    private static final String INT1_TAG = "int1";
    private static final String INT2_TAG = "int2";
    private static final String LIMIT_TAG = "limit";
    private static final String STR1_TAG = "str1";
    private static final String STR2_TAG = "str2";
    @Autowired
    private MeterRegistry meterRegistry;
    @Autowired
    private FizzBuzz fizzbuzz;

    /**
     * return  list of String the FizzBuzz
     *
     * @param int1
     * 	first number
     * @param int2
     * 	second number
     * @param limit
     * 	limit number
     * @param str1
     * 	First string to replace numbers multiple of int 1
     * @param str2
     * 	Second string to replace numbers multiple of int 2
     * @return
     */
    @Operation(summary = "run fizzbuzz and get the result in a list")
    @Timed(value = "fizzbuzz.request", extraTags = { "name", "fizzbuzzTimer" })
    @GetMapping(value = "/fizzbuzz/{int1}/{int2}/{limit}/{str1}/{str2}")
    public ResponseEntity<List<String>> runFizzbuzz(
            @Parameter(description = "First factor") @PathVariable(value = "int1") String int1,
            @Parameter(description = "Second factor") @PathVariable(value = "int2") String int2,
            @Parameter(description = "Upper limit of range of number") @PathVariable(value = "limit") String limit,
            @Parameter(description = "First string to replace if number is multiple of int1") @PathVariable(value = "str1") String str1,
            @Parameter(description = "Second string to replace if number is multiple of int2")@PathVariable(value = "str2") String str2)
            throws FizzbuzzParamException {
        LOG.info("Request with parameter [int1={},int2={},limit={},str1={},str2={}]", int1, int2, limit, str1, str2);
        Counter requestCounter = Counter.builder("fizzbuzz_requests").description("count of fizzbuzz requests").tags(INT1_TAG, String.valueOf(int1), INT2_TAG,
                String.valueOf(int2), LIMIT_TAG, String.valueOf(limit), STR1_TAG, str1, STR2_TAG, str2).register(meterRegistry);
        requestCounter.increment();
        List<String> result;
        try {
            // Verify
            int number1Int = Integer.parseInt(int1);
            int number2Int = Integer.parseInt(int2);
            int limitInt = Integer.parseInt(limit);
            LOG.info("Call FizzBuzz service...");
            result = fizzbuzz.fizzBuzz(number1Int, number2Int, limitInt, str1, str2);
        } catch (FizzbuzzParamException e) {
            throw new WrongParamRequestException(e.getMessage());
        } catch (NumberFormatException e) {
            LOG.error("Error of format of parameter",e);
            throw new WrongParamRequestException("Problem with input parameter please check the parameters");
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Get the grafana dashboard")
    @GetMapping(value = "/fizzbuzz/grafana")
    public RedirectView getDashBoard() {
        LOG.info("Redirecting to grafana dashboard ...");
        RedirectView view = new RedirectView();
        view.setUrl("http://localhost:3000/d/fizzbuzz");
        return view;
    }


    @Operation(summary = "Get the most used request")
    @GetMapping(value = "/fizzbuzz/RequestMostCalled")
    public ResponseEntity<Collection<CounterRequest>> mostUsedRequest() {
        Collection<Counter> requestCounters = meterRegistry.get("fizzbuzz_requests").counters();
        int maxRequest = 0;
        List<Counter> counters = new ArrayList();
        for (Counter counter : requestCounters) {
            if (counter.count() > maxRequest) {
                maxRequest = (int) counter.count();
                counters.clear();
                counters.add(counter);
            } else if (counter.count() == maxRequest) {
                counters.add(counter);
            }
        }
        //convert result
        List<CounterRequest> result = new ArrayList();
        for (Counter c : counters) {
            result.add(CounterRequest.counterToCounterRequest(c));
        }

        return ResponseEntity.ok(result);
    }

}
