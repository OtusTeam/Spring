package ru.otus.testingdemo.service;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class DavidBlaineExtension implements BeforeAllCallback, BeforeEachCallback, TestWatcher {
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        System.out.println("Вот эти парни!");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.out.println("Не-не-не Дэвид Блейн!");
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("В рот мне ноги! Он мне кепочку скукожил!");
    }

}
