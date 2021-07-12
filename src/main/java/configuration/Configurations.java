/**
 * Copyright (c) 2021 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 *
 * @author Edson Anawaya Rios
 */
package configuration;

/**
 * This enum defines possible Configuration Endpoints.
 */
public enum Configurations {

    WIDTH("1368"),
    HEIGHT("966"),
    QUICK_TEXT_NAME("Name from Selenium"),
    QUICK_TEXT_MESSAGE("Text from Selenium");

    private String config;

    Configurations(final String config) {
        this.config = config;
    }
    /**
     * Gets the api end point command according to the Sales force configuration.
     *
     * @return the end point.
     */
    public String toEndpoint() {
        return config;
    }
}
