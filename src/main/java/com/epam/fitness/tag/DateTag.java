package com.epam.fitness.tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTag extends TagSupport {
    private static final String LANGUAGE_ATTRIBUTE = "language";
    private static final String EN_PATTERN = "MMMM dd, yyyy";
    private static final String RU_PATTERN = "dd MMMM yyyy";

    private LocalDate date;

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        String language = (String) session.getAttribute(LANGUAGE_ATTRIBUTE);
        Locale locale = Locale.forLanguageTag(language);

        String pattern;
        switch (language) {
            case "ru": {
                pattern = RU_PATTERN;
                break;
            }
            case "en": {
                pattern = EN_PATTERN;
                break;
            }
            default: {
                pattern = EN_PATTERN;
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(pattern)
                .withLocale(locale);

        String formattedDate = date.format(formatter);
        JspWriter out = pageContext.getOut();
        try {
            out.write(formattedDate);
        } catch (IOException e) {
            /*LOGGER.error(e)*/;
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
