package org.snapdeal;

import org.gfg.keywordanalyzer.KeywordAnalyzerInterface;
import org.gfg.keywordanalyzer.UniqueKeywordAnalyzerImpl;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        KeywordAnalyzerInterface keywordAnalyzerInterface = new UniqueKeywordAnalyzerImpl();
    }
}