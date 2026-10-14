package com.resources.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/** Page object for the product listing shown after a successful login. */
public class ProductsPage {

    private final SelenideElement title = $(".title");
    private final ElementsCollection products = $$(".inventory_item");

    public SelenideElement title() {
        return title;
    }

    public ElementsCollection products() {
        return products;
    }
}
