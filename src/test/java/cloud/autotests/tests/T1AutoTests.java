package cloud.autotests.tests;

import cloud.autotests.helpers.DriverUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class T1AutoTests extends TestBase {

  public static final String BASE_URL = "https://www.t1-consulting.ru";

  @Test
  @DisplayName("Проверка названия страницы ")
  void titleTest() {
    step("Открыть url " + BASE_URL, () ->
            open(BASE_URL));

    step("Проверить, что название страницы содержит текст '«Т1 Консалтинг»'", () -> {
      String expectedTitle = "«Т1 Консалтинг»";
      String actualTitle = title();

      assertThat(actualTitle).isEqualTo(expectedTitle);
    });
  }

  @Test
  @DisplayName("Проверка наличия элементов в заголовке страницы")
  void checkAvailabilityOfHeaderElementsTest() {
    step("Открыть url " + BASE_URL, () -> open(BASE_URL));

    step("Проверить, что в залоловке страницы отображаются элементы:", () -> {
      $$(".header").shouldHave(texts("О компании Услуги\n" +
              "Карта решений Продукты\n" +
              "Контакты\n" +
              "+7 495 981-92-92\n" +
              "Обратная связь"));
    });
  }

  @Test
  @DisplayName("Проверка лога консоли страницы на наличие ошибок")
  void consoleShouldNotHaveErrorsTest() {
    step("Открыть url " + BASE_URL, () ->
            open(BASE_URL));

    step("Проверить, что лог консоли не содержит текст: 'SEVERE'", () -> {
      String consoleLogs = DriverUtils.getConsoleLogs();
      String errorText = "SEVERE";

      assertThat(consoleLogs).doesNotContain(errorText);
    });
  }

  @Test
  @DisplayName("Проверка элемента заголовка 'О компании'")
  void checkHeaderElementAboutCompanyTest() {
    step("Открыть url " + BASE_URL, () -> open(BASE_URL));

    step("Нажать на элемент заголовка с названием 'О компании'", () -> {
      $(".header").$(byText("О компании")).click();
      $(".content").shouldHave(text("О компании"))
              .shouldHave(text("Основные направления деятельности"))
              .shouldHave(text("Партнеры"))
              .shouldHave(text("Области автоматизации бизнеса"));
    });
  }

  @Test
  @DisplayName("Проверка элемента заголовка 'Услуги'")
  void checkHeaderElementServicesTest() {
    step("Открыть url " + BASE_URL, () -> open(BASE_URL));

    step("Навести мышь на элемент заголовка с названием 'Услуги'", () -> {
      $(".header").$(byText("Услуги")).hover();
      $$(".panel-menu").shouldHave(texts("Фронт\n" +
              "Миддл\n" +
              "Бэк\n" +
              "Импортозамещение\n" +
              "Цифровые решения\n" +
              "Аналитика\n" +
              "BigData", ""));
    });
  }

  @Test
  @DisplayName("Проверка элемента заголовка 'Карта решений'")
  void checkHeaderElementSolutionsMapTest() {
    step("Открыть url " + BASE_URL, () -> open(BASE_URL));

    step("Нажать на элемент заголовка с названием 'Карта решений'", () -> {
      $(".header").$(byText("Карта решений")).click();
      $(".content h1").shouldHave(text("Карта решений"));
      $$(".sol_table").shouldHave(texts("Услуги Т1 Oracle SAP IBM 1С Террасофт OpenText Prognoz RedHat ELMA"));

    });
  }
  @Tag("t1_tests")
  @Test
  @DisplayName("Проверка элемента заголовка 'Продукты'")
  void checkHeaderElementProductsTest() {
    step("Открыть url " + BASE_URL, () -> open(BASE_URL));

    step("Нажать на элемент заголовка с названием 'Продукты'", () -> {
      $(".header").$(byText("Продукты")).click();
      step("Проверить содержимое страницы 'Продукты'\"", () -> {
        $(".content h1").shouldHave(text("Продукты"));
        $(".content").shouldHave(text("Т1 Watchman"))
                .shouldHave(text("Т1 Collection"))
                .shouldHave(text("T1 TalentForce"))
                .shouldHave(text("Т1 Программа лояльности"))
                .shouldHave(text("T1 EasyTax"))
                .shouldHave(text("Т1 Единый фронт-офис"))
                .shouldHave(text("Т1 Система сводной отчетности"))
                .shouldHave(text("Т1 Кредитный конвейер"))
                .shouldHave(text("Т1 Система управления знаниями"))
                .shouldHave(text("Т1 Финансовая отчетность заемщиков"));
      });
    });
  }
  @Tag("t1_tests")
  @Test
  @DisplayName("Проверка элемента заголовка 'Контакты'")
  void checkHeaderElementContactsTest() {
    step("Открыть url " + BASE_URL, () -> open(BASE_URL));
    step("Нажать на элемент заголовка с названием 'Контакты'", () -> {
      $(".header").$(byText("Контакты")).click();
      $(".content h1").shouldHave(text("Контакты"));
      $$(".info-cont").shouldHave(texts(
              "«Т1 Консалтинг»\n" +
                      "115280, Москва, Ленинская слобода, д. 19 (5 минут пешком от м. Автозаводская)\n" +
                      "    Телефон: +7 495 981-92-92\n" +
                      "    Факс: +7 495 981-92-91\n" +
                      "    E-mail: info@t1-consulting.ru"));

    });
  }
}