package common.model;

import common.exceptions.InvalidTimePeriodException;

import java.time.Instant;

public class Period {

    private Instant startingInstant;
    private Instant endingInstant;

    public Period(Instant startingInstant, Instant endingInstant) {

        if (startingInstant.isAfter(endingInstant)) {
            throw new InvalidTimePeriodException();
        }
        this.startingInstant = startingInstant;
        this.endingInstant = endingInstant;
    }

    public Instant getStartingInstant() {
        return this.startingInstant;
    }

    public Instant getEndingInstant() {
        return this.endingInstant;
    }
}
