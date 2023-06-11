import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.files.DownloadActions.click;

public class CardDeliveryTest {

    public String generateDate(int daysToAdd, String pattern) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern(pattern));

    }



    @Test

    void shouldCardDeliveryTest() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Владимир");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        String currentDate = generateDate(8, "dd.MM.yy");
        $("[data-test-id=date] input").setValue(currentDate);
        $("[data-test-id=name] input").setValue("Дмитрий Арабей");
        $("[name='phone']").setValue("+79056173272");
        $("[data-test-id=agreement").click();
        $(byClassName("button")).click();
        $("[data-test-id=notification").shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + currentDate), Duration.ofSeconds(15)).shouldBe(visible);
        
    }
}