package engine.object;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Meter;

/**
 * CounterResult.
 */
public class CounterRequest {

    /**
     * count.
     */
    private int numberOfCall;
    /**
     * int1 : first factor.
     */
    private String int1;
    /**
     * int2 : second factor.
     */
    private String int2;
    /**
     * limit.
     */
    private String limit;
    /**
     * first String that replace the number if its a multiply of int1.
     */
    private String str1;
    /**
     * second String that replace the number if its a multiply of int2.
     */
    private String str2;


    /**
     * Convert Counter from micrometer.io to oboejcy CounterRequest to serialize the result.
     *
     * @param metricCounter
     * @return
     */
    public static CounterRequest counterToCounterRequest(Counter metricCounter) {
        Meter.Id idMetric = metricCounter.getId();
        CounterRequest counter = new CounterRequest();
        counter.setInt1(idMetric.getTag("int1"))
                .setInt2(idMetric.getTag("int2"))
                .setLimit(idMetric.getTag("limit"))
                .setStr1(idMetric.getTag("str1"))
                .setStr2(idMetric.getTag("str2"))
                .setNumberOfCall((int) metricCounter.count());
        return counter;
    }

    public int getNumberOfCall() {
        return numberOfCall;
    }

    public CounterRequest setNumberOfCall(int numberOfCall) {
        this.numberOfCall = numberOfCall;
        return this;
    }

    public String getInt1() {
        return int1;
    }

    public CounterRequest setInt1(String int1) {
        this.int1 = int1;
        return this;
    }

    public String getInt2() {
        return int2;
    }

    public CounterRequest setInt2(String int2) {
        this.int2 = int2;
        return this;
    }

    public String getLimit() {
        return limit;
    }

    public CounterRequest setLimit(String limit) {
        this.limit = limit;
        return this;
    }

    public String getStr1() {
        return str1;
    }

    public CounterRequest setStr1(String str1) {
        this.str1 = str1;
        return this;
    }

    public String getStr2() {
        return str2;
    }

    public CounterRequest setStr2(String str2) {
        this.str2 = str2;
        return this;
    }
}
