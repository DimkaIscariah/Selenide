import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {

    String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }



    @Test
    void shouldCardDelivery() {
        open ("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Владимир");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String currentDate = generateDate(5, "dd.MM.yy");
        $("[data-test-id=date] input").sendKeys(currentDate);
        $("[data-test-id=name] input").setValue("Дмитрий Арабей");
        $("[data-test-id=phone] input").setValue("+79056173272");
        $("[data-test-id=agreement]").click();
        $$("[type='button']").find(Condition.exactText("Забронировать")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Успешно! Встреча забронирована на " + currentDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}