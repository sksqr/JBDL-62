package org.gfg.keywordanalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KeywordAnalyzerImpl implements KeywordAnalyzerInterface{

    List<String> dataStore;

    public KeywordAnalyzerImpl() {
        this.dataStore = new ArrayList<>();
    }

    @Override
    public void recordKeyword(String keyword) {
        dataStore.add(keyword);
    }

    @Override
    public List<String> getKeywords() {
        return dataStore;
    }
}
