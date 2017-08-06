package com.n26.challenge.domain.resource;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Statistic {

    private final Double sum;
    private final Double avg;
    private final Double max;
    private final Double min;
    private final Long count;

    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime oldest;

    private Statistic(final Double sum, final Double avg, final Double max,
                     final Double min, final Long count, final LocalDateTime oldest) {
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
        this.oldest = oldest;
    }

    public Double getSum() {
        return sum;
    }

    public Double getAvg() {
        return avg;
    }

    public Double getMax() {
        return max;
    }

    public Double getMin() {
        return min;
    }

    public Long getCount() {
        return count;
    }

    public LocalDateTime getOldest() { return oldest;  }

    public static StatisticBuilder newBuilder() {
        return new StatisticBuilder();
    }

    public static class StatisticBuilder {

        private Double sum;
        private Double avg;
        private Double max;
        private Double min;
        private Long count;
        private LocalDateTime oldest;

        public StatisticBuilder setSum(final Double sum) {
            this.sum = sum;
            return this;
        }

        public StatisticBuilder setAvg(final Double avg) {
            this.avg = avg;
            return this;
        }

        public StatisticBuilder setMax(final Double max) {
            this.max = max;
            return this;
        }

        public StatisticBuilder setMin(final Double min) {
            this.min = min;
            return this;
        }

        public StatisticBuilder setCount(final Long count) {
            this.count = count;
            return this;
        }

        public StatisticBuilder setOldest(LocalDateTime oldest) {
            this.oldest = oldest;
            return this;
        }

        public Statistic build() {
            return new Statistic(sum, avg, max, min, count, oldest);
        }
    }
}
