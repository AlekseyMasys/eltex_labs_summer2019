package ru.masis;

public class Task<E extends User>{
    private E owner;
    private String title;

    public Task(E owner, String title) {
        this.owner = owner;
        this.title = title;
    }

    public void setOwner(E owner) {
        this.owner = owner;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public E getOwner() {
        return owner;
    }

    public String getTitle() {
        return title;
    }

}