package com.agileengine.analyzer.service;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PathFormatter implements Formatter {

    @Override
    public String format(Element element) {
        Elements parents = element.parents();
        List<String> pathList = findPath(parents);

        orderPathList(pathList);
        String path = buildPath(pathList);

        return path;
    }

    private List<String> findPath(Elements parents) {
        List<String> unorderedPathList = new ArrayList<>();
        for (Element parent : parents) {
            String formattedPathElement = defineOutputFormat(parent);
            unorderedPathList.add(formattedPathElement);
        }
        return unorderedPathList;
    }

    private String defineOutputFormat(Element parent) {
        String parentNodeName = parent.nodeName();
        String parentId = parent.id();
        String parentClass = parent.className();
        String result = parentNodeName;

        if (!parentId.isEmpty()) {
            result += "#" + parentId;
        }

        if (!parentClass.isEmpty()) {
            result += "." + parentClass;
        }

        return result;
    }

    private void orderPathList(List<String> unorderedPathList) {
        Collections.reverse(unorderedPathList);
    }

    private String buildPath(List<String> orderedPathList) {
        StringBuilder pathStringBuilder = new StringBuilder();
        for (Iterator<String> iterator = orderedPathList.iterator(); iterator.hasNext(); ) {
            String pathElement = iterator.next();
            if (iterator.hasNext()) {
                pathStringBuilder.append(pathElement).append(" > ");
            } else {
                pathStringBuilder.append(pathElement);
            }
        }

        return pathStringBuilder.toString();
    }
}
