package ru.javawebinar.basejava.model;

public class ComplexSection extends AbstractSection<ComplexElement> {

    @Override
    public void print() {
        for (ComplexElement element : elements) {
            System.out.println("\t - " + element.toString());
        }
    }
}