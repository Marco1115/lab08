package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import it.unibo.deathnote.api.DeathNote;

/**
 * Implementation of the {@link DeathNote} interface.
 */
public class DeathNoteImpl implements DeathNote {

    private String lastName;

    private final Map<String, DeathInfo> deathMap = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNumber >= 1 && ruleNumber <= RULES.size()) {
            return RULES.get(ruleNumber - 1);
        } else {
            throw new IllegalArgumentException("Tried to get a rule with an illegal rule number");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeName(final String name) {
        this.deathMap.put(Objects.requireNonNull(name), new DeathInfo());
        this.lastName = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDeathCause(final String cause) {
        if (this.lastName == null || cause == null) {
            throw new IllegalStateException("Inconsistent state: either the there is no name written or the cause is null");
        }
        return this.deathMap.get(lastName).writeInfoCause(cause);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDetails(final String details) {
        if (this.lastName == null || details == null) {
            throw new IllegalStateException("Inconsistent state: either the there is no name written or the details is null");
        }
        return this.deathMap.get(lastName).writeInfoDetails(details);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathCause(final String name) {
        return this.deathMap.get(name).deathCause;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathDetails(final String name) {
        return this.deathMap.get(name).deathDetails;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNameWritten(final String name) {
        return this.deathMap.containsKey(name);
    }

    private static final class DeathInfo {

        private static final String DEFAULT_CAUSE = "heart attack";
        private static final long CAUSE_MAX_TIME = 40L;
        private static final long DETAILS_MAX_TIME = 6040L;

        private String deathCause;
        private String deathDetails;
        private final Long deathTime;

        private DeathInfo() {
            this.deathCause = DEFAULT_CAUSE;
            this.deathDetails = "";
            this.deathTime = System.currentTimeMillis();
        }

        private boolean writeInfoCause(final String cause) {
            final long deltaTime = System.currentTimeMillis() - this.deathTime;
            if (deltaTime <= CAUSE_MAX_TIME) {
                this.deathCause = cause;
                return true;
            } else {
                return false;
            }
        }

        private boolean writeInfoDetails(final String details) {
            final long deltaTime = System.currentTimeMillis() - this.deathTime;
            if (deltaTime <= DETAILS_MAX_TIME) {
                this.deathDetails = details;
                return true;
            } else {
                return false;
            }
        }
    }
}
