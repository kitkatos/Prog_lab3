package org.example.item;

public class Item {
    private final String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Предмет: " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if(this == obj) {
            return true;
        }

        if(!(obj instanceof Item other)) {
            return false;
        }

        return name.equals(other.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
