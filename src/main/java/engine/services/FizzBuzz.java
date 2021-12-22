package engine.services;

import engine.errors.FizzbuzzParamException;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * FizzBuzz
 */
@Service
public class FizzBuzz {

    /**
     * Logger.
     */
    private final static Logger LOG = LoggerFactory.getLogger(FizzBuzz.class);

    @Autowired
    private final MeterRegistry meterRegistry;

    public FizzBuzz(final MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    /**
     * Call fizzbuzz Service with parameters
     *
     * @param int1  first number
     * @param int2  second number
     * @param limit limit
     * @param str1
     * @param str2
     * @return the result of the FizzBuzz service
     */
    public List<String> fizzBuzz(int int1, int int2, int limit, String str1, String str2) throws FizzbuzzParamException {
        Counter requestInErrorCounter = Counter.builder("fizzbuzz_error_parameter").description("count of fizzbuzz requests").register(meterRegistry);
        String messageError = "";
        // verify limit
        if (limit < 1) {
            messageError = "limit value shouldn't be negative";
        } else if (int1 == 0) {
            messageError = "first number should be different of 0";
        } else if (int2 == 0) {
            messageError = "second number should different of 0";
        } else if (int1 > limit || int1 < 1) {
            messageError = " first number should be between 1 and " + limit;
        } else if (int2 > limit || int2 < 1) {
            messageError = " second number should be between 1 and " + limit;
        }
        if (messageError != "") {
            LOG.error(messageError);
            requestInErrorCounter.increment();
            throw new FizzbuzzParamException(messageError);
        }

        List<String> listResult = new ArrayList<>();

        for (int i = 1; i <= limit; i++) {
            if (i % int1 == 0 && i % int2 == 0) {
                listResult.add(str1 + str2);
            } else if (i % int1 == 0) {
                listResult.add(str1);
            } else if (i % int2 == 0) {
                listResult.add(str2);
            } else {
                listResult.add(String.valueOf(i));
            }
        }
        LOG.info("Return result : {}", listResult);
        return listResult;
    }

    /**
     * Check the value.
     *
     * @param value
     * @return
     */
    private boolean isValidInt(int value) {
        return value >= Integer.MIN_VALUE || value <= Integer.MAX_VALUE;
    }
}
