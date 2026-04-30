package ru.demo.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

public class MicrometerMetricsManager implements MetricsManager {
    private final MeterRegistry meterRegistry;
    private final Map<String, AtomicLong> gauges = new ConcurrentHashMap<>();
    private final Map<String, Counter> counters = new ConcurrentHashMap<>();

    public MicrometerMetricsManager(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public void putValue(Meter meterName, long value) {
        var gaugeName = makeMeterName(meterName);
        var gauge = gauges.computeIfAbsent(gaugeName, key -> {
            var newGauge = new AtomicLong();
            registerGauge(meterName, newGauge::get);
            return newGauge;
        });
        gauge.set(value);
    }

    @Override
    public void registerGauge(Meter gaugeName, Supplier<Number> gaugeGetter) {
        var builder = Gauge.builder(gaugeName.getMeterName(), gaugeGetter);
        builder.register(meterRegistry);
    }

    @Override
    public void incrementValue(Meter meterName) {
        var counterName = makeMeterName(meterName);
        var counter = counters.computeIfAbsent(counterName, key -> makeCounter(meterName));
        counter.increment();
    }

    private Counter makeCounter(Meter meterName) {
        var builder = Counter.builder(meterName.getMeterName());
        return builder.register(meterRegistry);
    }

    private String makeMeterName(Meter meterName) {
        return String.format("%s--", meterName.getMeterName());
    }
}
