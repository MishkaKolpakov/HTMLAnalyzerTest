package com.agileengine.analyzer.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class HTMLFileSearchEngine implements SearchEngine {
    private static String HTML_EXTENSION = ".html";
    private static String CHARSET_NAME = "utf8";
    private static String DIRECTORY_PATH = "./pages/";

    private File originFile;
    private File changeableFile;

    private String originalFilePath;
    private String changableFilePath;

    public HTMLFileSearchEngine(String originalFileName, String changableFileName){
        originalFilePath = DIRECTORY_PATH + originalFileName + HTML_EXTENSION;
        changableFilePath = DIRECTORY_PATH + changableFileName + HTML_EXTENSION;
        originFile = new File(originalFilePath);
        changeableFile = new File(changableFilePath);
    }

    @Override
    public Optional<Element> findElementById(String elementId) {
        try {
            Document doc = Jsoup.parse(
                    originFile,
                    CHARSET_NAME,
                    originFile.getAbsolutePath());

            return Optional.of(doc.getElementById(elementId));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Elements> findElementByQuery(String cssQuery) {
        try {
            Document doc = Jsoup.parse(
                    changeableFile,
                    CHARSET_NAME,
                    changeableFile.getAbsolutePath());

            return Optional.of(doc.select(cssQuery));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
