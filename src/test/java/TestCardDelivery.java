import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestCardDelivery {

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String planningDate = generateDate(3);

    @Test
    void shouldSuccessfulCardOrder() {
        open("http://localhost:7777");

        Configuration.holdBrowserOpen = true;

        $("[data-test-id='city'] input").setValue("Москва");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Гришин Кирилл");
        $("[data-test-id='phone'] input").setValue("+79152884182");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content").shouldBe(visible).shouldHave(exactText("Встреча успешно забронирована на " + planningDate));


    }

}
