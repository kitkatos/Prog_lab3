package org.example.stock;

public class Stock {
    private final String name;
    private int count;

    public Stock(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void addStocks(int count) {
        this.count += count;
    }

    public void removeStocks(int count) {
        if(count > this.count) {
            throw new IllegalArgumentException("Количество удаляемых акций не может превышать количество имеющихся акций");
        }

        this.count -= count;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() | this.count;
    }

    @Override
    public String toString() {
        return String.format("Акция '%s', количество: %d", this.name, this.count);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(obj == this) {
            return true;
        }

        if(!(obj instanceof Stock other)) {
            return false;
        }

        return this.name.equals(other.getName()) && this.count == other.getCount();
    }
}
