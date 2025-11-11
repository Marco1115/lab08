package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private static final int NEGATIVE_NUMBER = -1;
    private static final String HUMAN_NAME_1 = "Marco";
    private static final String HUMAN_NAME_2 = "Giorgio";
    private static final String DEATH_CAUSE = "karting accident";
    private static final String DETAILS = "ran for too long";
    private static final long SLEEP_TIME_CAUSE = 100;
    private static final long SLEEP_TIME_DETAILS = 6100;

    private DeathNote deathNote;

    @BeforeEach
    void initialiseDeathNote() {
        deathNote = new DeathNoteImpl();
    }

    @Test
    void testIllegalRuleNumber() {
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
        for (int i = 1; i <= DeathNote.RULES.size(); i++) {
            assertNotNull(deathNote.getRule(i));
            assertFalse(deathNote.getRule(i).isBlank());
        }
    }

    @Test
    void testWrite() {
        assertFalse(deathNote.isNameWritten(HUMAN_NAME_1));
        deathNote.writeName(HUMAN_NAME_1);
        assertTrue(deathNote.isNameWritten(HUMAN_NAME_1));
        assertFalse(deathNote.isNameWritten("Mario"));
        assertFalse(deathNote.isNameWritten(""));
    }

    @Test
    void testDeathCause() throws InterruptedException {
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
        Thread.sleep(SLEEP_TIME_CAUSE);
        deathNote.writeDeathCause("heart attack");
        assertEquals(DEATH_CAUSE, deathNote.getDeathCause(HUMAN_NAME_2));
    }

    @Test
    void testDetails() throws InterruptedException {
        try {
            deathNote.writeDetails(DETAILS);
            fail("Writing details without any name in the death note was possible, but should have thrown an exception");
        } catch (final IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message
        }
        deathNote.writeName(HUMAN_NAME_1);
        assertTrue(deathNote.getDeathDetails(HUMAN_NAME_1).isBlank());
        assertTrue(deathNote.writeDetails(DETAILS));
        assertEquals(DETAILS, deathNote.getDeathDetails(HUMAN_NAME_1));
        deathNote.writeName(HUMAN_NAME_2);
        Thread.sleep(SLEEP_TIME_DETAILS);
        assertFalse(deathNote.writeDetails("Dummy death details"));
        assertEquals(DETAILS, deathNote.getDeathDetails(HUMAN_NAME_1));
    }
}
