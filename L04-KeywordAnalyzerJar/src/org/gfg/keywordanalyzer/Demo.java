package org.gfg.keywordanalyzer;

import java.util.List;

public class Demo {

    public static void main(String[] args) {
        keywordAnalyzerDemo(new KeywordAnalyzerImpl());

        keywordAnalyzerDemo(new UniqueKeywordAnalyzerImpl());

        keywordAnalyzerDemo(new FreqKeywordAnalyzerImpl());
    }

    private static void keywordAnalyzerDemo(KeywordAnalyzerInterface keywordAnalyzer){
        keywordAnalyzer.recordKeyword("Mobile");
        keywordAnalyzer.recordKeyword("Pen Drive");
        keywordAnalyzer.recordKeyword("Laptop");
        keywordAnalyzer.recordKeyword("Mobile");
        keywordAnalyzer.recordKeyword("Laptop");
        List<String> keywords = keywordAnalyzer.getKeywords();
        System.out.println(keywords);

        List<KeywordFrequency> outPut = keywordAnalyzer.getKeywordsWithFrequency();
        System.out.println(outPut);
    }
}
