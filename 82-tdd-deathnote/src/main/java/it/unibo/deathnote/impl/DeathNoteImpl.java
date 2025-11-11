package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

/**
 * Implementation of the {@link DeathNote} interface.
 */
public class DeathNoteImpl implements DeathNote {

    private String lastName;
    private Long lastWriteTime;

    private final Map<String, DeathInfo> deathCauseMap = new HashMap<>();

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeName'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDeathCause(final String cause) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDetails(final String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathCause(final String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathDetails(final String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNameWritten(final String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }

    private static final class DeathInfo {

        private static final String DEFAULT_CAUSE = "heart attack";

        private String deathCause;
        private String deathDetails;

        private DeathInfo() {
            this.deathCause = DEFAULT_CAUSE;
            this.deathDetails = "";
        }
    }
}
