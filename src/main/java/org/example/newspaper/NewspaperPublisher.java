package org.example.newspaper;

import org.example.exception.NoEmptySlotException;
import org.example.utils.ArrayUtils;

public class NewspaperPublisher {
    private final String name;
    private final Newspaper[] publishedNewspapers;
    private final NewspaperReader[] newspaperReaders;

    public NewspaperPublisher(String name, int maxPublishedNewspapers, int maxReaders) {
        this.name = name;
        this.publishedNewspapers = new Newspaper[maxPublishedNewspapers];
        this.newspaperReaders = new NewspaperReader[maxReaders];
    }

    public void publishNewspaper(String text, int day) {
        Newspaper newspaper = new Newspaper(text, day);

        publishNewspaper(newspaper);
    }

    public void publishNewspaper(Newspaper newspaper) {
        int lastEmptySlot = ArrayUtils.getFirstEmptySlot(publishedNewspapers);

        if(lastEmptySlot == -1) {
            throw new NoEmptySlotException();
        }

        System.out.printf("Вышла новая газета! Вот её текст:\n%s\n", newspaper.text());
        this.publishedNewspapers[lastEmptySlot] = newspaper;
    }

    public void addNewReader(NewspaperReader reader) {
        int lastEmptySlot = ArrayUtils.getFirstEmptySlot(newspaperReaders);

        if(lastEmptySlot == -1) {
            throw new NoEmptySlotException();
        }

        this.newspaperReaders[lastEmptySlot] = reader;
    }

    protected void notifyReaders(Newspaper newspaper) {
        for(var reader : newspaperReaders) {
            notifyReader(newspaper, reader);
        }
    }

    protected void notifyReader(Newspaper newspaper, NewspaperReader reader) {
        reader.readNewspaper(newspaper);
    }

    public NewspaperReader[] getReaders() {
        return newspaperReaders;
    }

    public Newspaper[] getPublishedNewspapers() {
        return publishedNewspapers;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Газета %s", getName());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(obj == this) {
            return true;
        }

        if(!(obj instanceof NewspaperPublisher other)) {
            return false;
        }

        if(this.getPublishedNewspapers().length != other.getPublishedNewspapers().length) {
            return false;
        }

        boolean result = this.getName().equals(other.getName());

        result = result && ArrayUtils.isArraysEqual(other.getPublishedNewspapers(), this.getPublishedNewspapers());
        result = result && ArrayUtils.isArraysEqual(other.getReaders(), this.getReaders());

        return result;
    }

    @Override
    public int hashCode() {
        int hashCode = getName().hashCode();

        var newspapers = this.getPublishedNewspapers();
        for (Newspaper newspaper : newspapers) {
            if (newspaper != null) {
                hashCode |= newspaper.hashCode();
            }
        }
        var readers = this.getReaders();

        for (NewspaperReader reader : readers) {
            if (reader != null) {
                hashCode |= reader.hashCode();
            }
        }

        return hashCode;
    }
}
