package com.example.notelist.model;

public class Item {

    private String text;
    private boolean isDone = false;

    public Item(String text) {
        this.text = text;
    }

    public Item(String text, boolean isDone) {
        this.text = text;
        this.isDone = isDone;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void toggle() {
        this.isDone = !this.isDone;
    }
}
