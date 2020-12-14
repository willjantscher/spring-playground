package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("wordcounter")
public class MyConfig {
    private boolean caseSensitive;
    private String[] skip;

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }



    public String[] getSkip() {
        return skip;
    }

    public void setSkip(String[] skip) {
        this.skip = skip;
    }
}
