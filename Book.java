package com.jetbrains;

/**
 * A class that represents a Book within a book store.
 */
public class Book {

    private String title;
    private int quantity;
    private double price;
    private String publisher;

    /**
     * Constructor for objects of class Book
     */
    public Book() {
        title = "";
        quantity = 0;
        price = 0.0;
        publisher = "";
    }

    /**
     *  Constructor with parameters for objects of class Book
     */
    public Book(String title, int quantity, double price, String publisher) {
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.publisher = publisher;
    }

    /**
     * Gets the title of this Book object.
     *
     * @return The title of this Book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the quantity of this Book object.
     *
     * @return The quantity of this Book.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the price of this Book object.
     *
     * @return The price of this Book.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the publisher of this Book object.
     *
     * @return The publisher of this Book.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Compares this Book to another Book to see if they are the same.
     *
     * @param other
     *          The Book object being compared to this Book object.
     * @return
     */
    public boolean isEqual(Book other) {
        return title.equals(other.title) && publisher.equals(other.publisher);
    }

    /**
     * Compares this Book to another Book object to see if this Product
     *   is lexographically greater than the other one.
     *
     * @param other
     *          The Book object being compared to this Book object.
     * @return True if this Book is greater than the other; false otherwise.
     */
    public boolean isGreaterThan(Book other) {
        if (title.equals(other.title)) {
            int value = publisher.compareTo(other.publisher);
            return value > 0;
        }

        int value = title.compareTo(other.title);
        return value > 0;
    }

    @Override
    public int hashCode() {
        return title.hashCode() + publisher.hashCode() + quantity * 347 + (int) price * 193;
    }

    /**
     * Creates a deep copy of this Book object. Apparently, Cloneable and clone() are pretty
     * much broken as expressed here by Josh Bloch, author of Effective Java: http://www.artima.com/intv/bloch13.html.
     *
     * @return A deep copy of this Book object.
     */
    public Book createCopy() {
        return new Book(title, quantity, price, publisher);
    }
}
