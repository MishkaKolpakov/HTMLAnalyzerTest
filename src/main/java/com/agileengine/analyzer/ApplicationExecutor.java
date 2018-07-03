package com.agileengine.analyzer;

import com.agileengine.analyzer.service.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Optional;

public class ApplicationExecutor {
    private static final String ORIGINAL_ELEMENT_ID = "make-everything-ok-button";
    private static final String ORIGINAL_FILE_NAME = "sample-0-origin";

    public static void main(String[] args) {
        if(args.length != 2) {
            throw new IllegalArgumentException("You should enter exactly two arguments.");
        }

        if(!args[0].equals(ORIGINAL_FILE_NAME)){
            throw new IllegalArgumentException("First argument name should be " + ORIGINAL_FILE_NAME );
        }

        execute(args);
    }

    private static void execute(String[] args) {

        SearchEngine searchEngine = new HTMLFileSearchEngine(args[0], args[1]);
        Formatter attributeFormatter = new AttributeFormatter();
        Formatter pathFormatter = new PathFormatter();

        Optional<Element> originalButtonOptional = searchEngine.findElementById(ORIGINAL_ELEMENT_ID);
        Element originalButton;
        String cssQuery = "";

        if(originalButtonOptional.isPresent()){
            originalButton = originalButtonOptional.get();
            cssQuery = attributeFormatter.format(originalButton);
        } else {
            throw new UnsupportedOperationException("Check if original html file is correct.");
        }

        Optional<Elements> elementsOpt = searchEngine.findElementByQuery(cssQuery);

        if (elementsOpt.isPresent()) {
            Elements elements = elementsOpt.get();
            for (Element element : elements) {
                System.out.println(pathFormatter.format(element));
            }
        } else {
            throw new UnsupportedOperationException("Search by attributes was failed.");
        }
    }
}