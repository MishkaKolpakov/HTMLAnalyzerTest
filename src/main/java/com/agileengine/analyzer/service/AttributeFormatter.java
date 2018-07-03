package com.agileengine.analyzer.service;

import org.jsoup.nodes.Element;

import java.util.List;
import java.util.stream.Collectors;

public class AttributeFormatter implements Formatter {

    @Override
    public String format(Element element) {
        List<String> attributeList = getAttributeListByElementWithoutId(element);
        String nodeName = element.nodeName();
        String attributes = buildAttributesInQueryFormat(nodeName, attributeList);
        return attributes;
    }

    private List<String> getAttributeListByElementWithoutId(Element element) {
        return element.attributes().asList().stream()
                .filter(attribute -> !attribute.getKey().equals("id"))
                .map(attribute -> "[" + attribute.getKey() + "]")
                .collect(Collectors.toList());
    }

    private String buildAttributesInQueryFormat(String nodeName, List<String> attributeList) {
        StringBuilder attributeStringBuilder = new StringBuilder();
        attributeStringBuilder.append(nodeName);
        for (String attribute : attributeList) {
            attributeStringBuilder.append(attribute);
        }

        return attributeStringBuilder.toString();
    }
}
