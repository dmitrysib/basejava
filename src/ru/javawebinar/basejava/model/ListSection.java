package ru.javawebinar.basejava.model;

public class ListSection extends AbstractSection<String> {

    @Override
    public void print() {
        for (String element : elements) {
            System.out.println("\t- " + element);
        }
    }
}