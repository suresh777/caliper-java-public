package org.imsglobal.caliper;

import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.reading.EpubSubChapter;
import org.imsglobal.caliper.entities.reading.EpubVolume;
import org.imsglobal.caliper.events.NavigationEvent;
import org.imsglobal.caliper.actions.Action;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SensorSendEventsTest {

    private LearningContext learningContext;
    private EpubVolume epub;
    private DigitalResource fromResource;
    private EpubSubChapter target;
    private NavigationEvent event;

    @Test
    public void test() {

        Sensor<String> sensor = new Sensor<>();
        sensor.registerClient("default", new Client(TestUtils.getTestingOptions()));

        // Build the Learning Context
        learningContext = TestUtils.buildReadiumStudentLearningContext();

        // Build epub
        epub = TestUtils.buildEpubVolume43();

        // Build previous location
        fromResource = TestUtils.buildAmRev101LandingPage();

        // Build target
        target = TestUtils.buildEpubSubChap431();

        // Fire event test - Send 50 events
        for (int i = 0 ; i < 50 ; i++) {
            sensor.send(TestUtils.buildEpubNavigationEvent(learningContext, epub, Action.NAVIGATED_TO, fromResource, target));
        }

        // There should be two caliperEvents queued
        assertEquals("Expect fifty Caliper events to be sent", 50,
                sensor.getStatistics().get("default").getMeasures().getCount());

        // TODO - Describes test - Send five describes

        // There should be four describes queued assertEquals("Expect five describes to be sent", 5,
        // Caliper.getStatistics().getDescribes().getCount());

        // There should be two message successfully sent
        int successes = sensor.getStatistics().get("default").getSuccessful().getCount();
        assertEquals("Expect fifty messages to be sent successfully", 50, successes);

        // There should be zero failures
        int failures = sensor.getStatistics().get("default").getFailed().getCount();
        assertEquals("Expect zero message failures to be sent", 0, failures);
    }
}