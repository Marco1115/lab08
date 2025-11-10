package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private static final int NEGATIVE_NUMBER = -1;

    @Test
    void testIllegalRuleNumber() {
        final DeathNoteImpl deathNote = new DeathNoteImpl();
        try {
            deathNote.getRule(0);
            deathNote.getRule(NEGATIVE_NUMBER);
            fail("Getting an negative or 0 rule was possible, but should have thrown an exception");
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message
        }
    }
}
