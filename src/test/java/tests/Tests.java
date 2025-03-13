package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("simple")
public class Tests extends TestBase{

    public String url = "https://moizver.com/";

    @Test
    @DisplayName("Проверка работы строки поиска")
    void searchItemTest(){
        step("Открыть сайт", () -> {
            open(url); });
        step("Ввод названия товара строку поиска", () -> {
            $("#title-search-input_fixed").setValue("корм для животных").pressEnter();
             });
        step("Проверить, что поиск дал результат", () -> {
            $$(".top_wrapper")
                    .shouldBe(sizeGreaterThan(0));});
    }

    @Test
    @DisplayName("Добавление товара в корзину с главной страницы")
    void addingItemToCartTest() {
        step("Открыть сайт", () -> {
            open(url); });
        step("Добавить товар в корзину", () -> {
            $("#bx_3966226736_1284937").hover();
            $("[data-value='91']").click();});
        step("Проверить наличие товара", () -> {
            $(".wrap_li").shouldHave(text("1"));
        });

    }
    @Test
    @DisplayName("Открытие окна для заявки на главной странице")
    void applicationFormTest() {
        step("Открыть сайт", () -> {
            open(url); });
        step("Открыть окно заявки", () -> {
            $("#feedback_svg").click(); });
        step("Проверить, что форма для заявки открылась", () -> {
            $("#callibri_request").shouldBe(visible);
        });
    }
    @Test
    @DisplayName("Проверка работы фильтра")
    void filterTest(){
        step("Открыть сайт", () -> {
            open(url); });
        step("Открыть фильтры", () -> {
            $(".dropdown-toggle").hover();
            $(".data9577").click();});
        step("Проверить работу фильтра", () -> {
            $("[data-property_id='124']").$(byText("Royal Canin")).click();
            $("#set_filter").click();
            $$(".ajax_load .block")
                    .shouldBe(sizeGreaterThan(0));
        });
    }

    @Test
    @DisplayName("Добавление товара в избранное с главной страницы")
    void addingItemToFavoritesTest() {
        step("Открыть сайт", () -> {
            open(url);
        });
        step("Добавить товар в избранное", () -> {
            $("#bx_3966226736_98824_pict").hover();
            $("[data-item='23102395']").click();
        });
        step("Проверить наличие товара", () -> {
            $("[item-section='DelDelCanBuy']").shouldHave(text("1"));
        });
    }

}
