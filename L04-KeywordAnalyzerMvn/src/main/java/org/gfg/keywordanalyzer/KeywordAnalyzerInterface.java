package org.gfg.keywordanalyzer;

import java.util.List;

public interface KeywordAnalyzerInterface {
    void recordKeyword(String keyword);

    List<String> getKeywords();

    default List<KeywordFrequency> getKeywordsWithFrequency(){
        return null;
    }
}
