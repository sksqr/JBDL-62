package org.gfg.keywordanalyzer;

public class KeywordFrequency {
    private String keyword;

    private Integer frequency;

    public KeywordFrequency(String keyword, Integer frequency) {
        this.keyword = keyword;
        this.frequency = frequency;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void incrementFrequency() {
        this.frequency++;
    }

    @Override
    public String toString() {
        return "KeywordFrequency{" +
                "keyword='" + keyword + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
