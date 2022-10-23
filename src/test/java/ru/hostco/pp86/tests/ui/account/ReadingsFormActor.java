package ru.hostco.pp86.tests.ui.account;

import ru.hostco.pp86.helpers.DateRandomizer;
import ru.hostco.pp86.helpers.Dates;
import ru.hostco.pp86.pages.account.components.ReadingsFormComponent;

import java.util.Random;

public class ReadingsFormActor {

    static ReadingsFormComponent readingsForm = new ReadingsFormComponent();

    static void fillAllData() {
        Dates randomDate = DateRandomizer.randomDate(1, -1);
        int randomHour = new Random().nextInt(25);
        int randomMinute = new Random().nextInt(61);

//        readingsForm.component.shouldBe(visible);
//        readingsForm.clickByCalendarLink().calendar.component.shouldBe(visible).shouldBe(interactable);
//        readingsForm.selectDate(randomDate);
//        sleep(500);
//        readingsForm.clickByCalendarLink().calendar.component.shouldBe(visible).shouldBe(interactable);
//        readingsForm.calendar.setTime(randomHour, randomMinute);
        readingsForm
                .setTemperature(36.6)
                .setWeight(50)
                .setPressure(135, 80)
                .setSugarLevel(120.5)
                .setPulse(120)
                .setMood("good1")
                .setAlcohol(0.01)
                .setAmbivalence(50)
                .setHealthStatus("good2")
                .setSkinCondition("good3")
                .clickBySubmit();
    }

    public static void checkAllData() {

    }
}
