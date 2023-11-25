package com.example.L07SpringMVCAnnotationdemo;

import org.gfg.keywordanalyzer.KeywordAnalyzerImpl;
import org.gfg.keywordanalyzer.KeywordAnalyzerInterface;
import org.gfg.keywordanalyzer.UniqueKeywordAnalyzerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProjectConfig {

    @Bean("keywordAnalyzerImpl")
    @Primary
    public KeywordAnalyzerInterface getKeywordAnalyzer(){
        return new KeywordAnalyzerImpl();
    }

    @Bean("uniqueKeywordAnalyzerImpl")
    public KeywordAnalyzerInterface getUniqueKeywordAnalyzerImpl(){
        return new UniqueKeywordAnalyzerImpl();
    }
}
