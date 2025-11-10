package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private static final int NEGATIVE_NUMBER = -1;
    private static final String HUMAN_NAME_1 = "Marco";
    private static final String HUMAN_NAME_2 = "Giorgio";
    private static final String DEATH_CAUSE = "karting accident";
    private static final long SLEEP_TIME = 100;

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

    @Test
    void testNoEmptyOrNullRules() {
        for (final String rule: DeathNote.RULES) {
            assertNotNull(rule);
            assertFalse(rule.isBlank());
        }
    }

    @Test
    void testWrite() {
        final DeathNoteImpl deathNote = new DeathNoteImpl();
        assertFalse(deathNote.isNameWritten(HUMAN_NAME_1));
        deathNote.writeName(HUMAN_NAME_1);
        assertTrue(deathNote.isNameWritten(HUMAN_NAME_1));
        assertFalse(deathNote.isNameWritten("Mario"));
        assertFalse(deathNote.isNameWritten(""));
    }

    @Test
    void testDeathCause() throws InterruptedException {
        final DeathNoteImpl deathNote = new DeathNoteImpl();
        try {
            deathNote.writeDeathCause(HUMAN_NAME_1);
            fail("Writing a death cause without any name in the death note was possible, but should have thrown an exception");
        } catch (final IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message
        }
        deathNote.writeName(HUMAN_NAME_1);
        assertEquals("heart attack", deathNote.getDeathCause(HUMAN_NAME_1));
        deathNote.writeName(HUMAN_NAME_2);
        assertTrue(deathNote.writeDeathCause(DEATH_CAUSE));
        assertEquals(DEATH_CAUSE, deathNote.getDeathCause(HUMAN_NAME_2));
        Thread.sleep(SLEEP_TIME);
        deathNote.writeDeathCause("heart attack");
        assertEquals(DEATH_CAUSE, deathNote.getDeathCause(HUMAN_NAME_2));
    }
}
