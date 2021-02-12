package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.Experience;

public final class HtmlUtil {

    public static String buildHtmlLink(Experience.Link homePage) {
        return homePage.getUrl() == null
                ? homePage.getTitle()
                : "<a href=\"" + homePage.getUrl() + "\" target=\"_blank\">" + homePage.getTitle() + "</a>";
    }
}
