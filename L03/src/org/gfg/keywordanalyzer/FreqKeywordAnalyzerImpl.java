package org.gfg.keywordanalyzer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FreqKeywordAnalyzerImpl implements KeywordAnalyzerInterface{

    private Map<String,KeywordFrequency> dataStore;

    public FreqKeywordAnalyzerImpl() {
//        this.dataStore = new HashMap<>();
        this.dataStore = new LinkedHashMap<>();
    }

    @Override
    public void recordKeyword(String keyword) {
        if(dataStore.containsKey(keyword)){
            dataStore.get(keyword).incrementFrequency();
        }
        else {
            dataStore.put(keyword, new KeywordFrequency(keyword,1));
        }
    }

    @Override
    public List<String> getKeywords() {
        return dataStore.keySet().stream().toList();
    }

    @Override
    public List<KeywordFrequency> getKeywordsWithFrequency() {
        return dataStore.values().stream().toList();
    }
}
