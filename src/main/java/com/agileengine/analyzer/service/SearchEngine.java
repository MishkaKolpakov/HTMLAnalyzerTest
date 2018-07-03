package com.agileengine.analyzer.service;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Optional;

public interface SearchEngine {
    Optional<Element> findElementById(String elementId);
    Optional<Elements> findElementByQuery(String cssQuery);
}
