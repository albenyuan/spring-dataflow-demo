package com.shouqianba.spring.task;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

/**
 * @Author Alben Yuan
 * @Date 2019-01-10 22:00
 */
@ConfigurationProperties("localFilePath")
public class TaskProperties {

    private String pattern;

    private String format;

    public TaskProperties() {
        this.format = "yyyy-MM-dd HH:mm:ss.SSS";
        this.pattern = "[file:/]/file/to/path";
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }


    public String getFormat() {
        Assert.hasText(this.format, "format must not be empty nor null");
        return this.format;
    }

    public void setFormat(final String format) {
        this.format = format;
    }
}
