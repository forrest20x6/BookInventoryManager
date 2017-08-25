package com.jetbrains;

import java.text.DecimalFormat;

/**
 * A class that contains a collection of Book objects within a linked list structure.
 */
public class BookList {

    /**
     * An inner class that is similar to the struct Node in a C linked list.
     */
    private class BookNode {
        Book data;
        BookNode link;

        BookNode(Book book) {
            data = book;
            link = null;
        }
    }

    private BookNode bookListHead;

    /**
     * Constructor for objects of class BookList
     */
    public BookList() {
        bookListHead = null;
    }

    public BookNode getBookListHead() {
        return bookListHead;
    }

    /**
     * Adds a new Book entry into this BookList object.
     *
     * @param newBook
     *          The Book object to be added to the list.
     * @return  True if newBook was added to the list; false if not.
     */
    public boolean addBook(Book newBook) {
        if (newBook == null || newBook.getPrice() <= 0.0 || newBook.getQuantity() < 0) {
            return false;
        }

        BookNode nodePtr = null;
        for (nodePtr = bookListHead; nodePtr != null; nodePtr = nodePtr.link) {
            if (nodePtr.data.isEqual(newBook)) {
                return false;
            }
        }

        BookNode newBookNode = new BookNode(newBook);
        if (bookListHead == null) {
            bookListHead = newBookNode;
        } else {
            nodePtr = bookListHead;
            while (nodePtr.link != null) {
                nodePtr = nodePtr.link;
            }
            nodePtr.link = newBookNode;
        }
        return true;
    }

    /**
     * Removes a book from the list of books.
     *
     * @param title
     *          The title of the book to be removed.
     * @param publisher
     *          The publisher of the book to be removed.
     * @return  True if the book was removed; false if it wasn't.
     */
    public boolean removeBook(String title, String publisher) {
        Book book = new Book(title, 0, 0.0, publisher);
        BookNode nodePtr = bookListHead;
        BookNode previous = null;

        while (nodePtr != null) {
            if (nodePtr.data.isEqual(book)) {
                if (nodePtr == bookListHead) {
                    bookListHead = nodePtr.link;
                } else {
                    previous.link = nodePtr.link;
                    nodePtr.link = null;
                }
                nodePtr.link = null;
                return true;
            }
            previous = nodePtr;
            nodePtr = nodePtr.link;
        }
        return false;
    }

    /**
     * Displays all the books in the list with their publishers, prices, and quantities.
     */
    public void showBooks() {
        if (bookListHead == null) {
            System.out.println("\nNo books in the list so far.\n");
            return;
        }

        int count = 1;
        System.out.println("========================================================================================");
        System.out.println("|                                         BOOKS                                        |");
        System.out.println("========================================================================================");
        System.out.println("| #   Title                     Publisher                 Price         Quantity       |");
        System.out.println("========================================================================================");
        BookNode nodePtr = bookListHead;
        while (nodePtr != null) {
            System.out.print("| ");
            System.out.format("%-3d %-25s %-25s %-13s %-15d", count, nodePtr.data.getTitle(), nodePtr.data.getPublisher(),
                              formatPrice(nodePtr.data.getPrice()), nodePtr.data.getQuantity());
            System.out.println("|");
            nodePtr = nodePtr.link;
            count++;
        }
        System.out.println("========================================================================================");
    }

    /**
     * Gets the total quantity of books in the book inventory list.
     *
     * @param listHead
     *          The head of the list to gather quantities from.
     * @return  The total number of books in the book inventory.
     */
    public int getTotalQuantity(BookNode listHead) {
        if (listHead == null) {
            return 0;
        }

        return listHead.data.getQuantity() + getTotalQuantity(listHead.link);
    }

    /**
     * Sorts the book inventory list.
     */
    public void sortBooks() {
        BookNode newBookList = null;
        BookNode tail = null;

        //repeat until the original list is empty
        while (bookListHead != null) {
            Book minBook = findMinimum();

            //make a new node using the minimum product
            BookNode newBookNode = new BookNode(minBook);

            if (newBookList == null) {
                newBookList = newBookNode;
            } else {
                tail.link = newBookNode;
            }

            //update tail to point to the new last node
            tail = newBookNode;

            //remove the current minimum book from the old list
            removeBook(minBook.getTitle(), minBook.getPublisher());
        }
        bookListHead = newBookList;
    }

    /**
     * Counts the number of nodes in a list of <code>BookNode</code> objects.
     *
     * @param listHead
     *          The head of a list of <code>BookNode</code> objects
     * @return  The count of nodes in the <code>listHead</code>.
     */
    public int countBookNodes(BookNode listHead) {
        if (listHead == null) {
            return 0;
        }

        return 1 + countBookNodes(listHead.link);
    }

    /**
     * Finds the minimum book in the list.
     *
     * @return  The smallest Book in the list (or the default book if the list is empty).
     */
    private Book findMinimum() {
        if (bookListHead == null) {
            return new Book();
        }

        Book minBook = bookListHead.data.createCopy();
        BookNode nodePtr = bookListHead;
        while (nodePtr.link != null) {
            if (minBook.isGreaterThan(nodePtr.link.data)) {
                minBook = nodePtr.link.data.createCopy();
            }
            nodePtr = nodePtr.link;
        }
        return minBook;
    }

    /**
     * Formats a double so that it appears in the dollar monetary form.
     *
     * @param value
     *          The double to be formatted.
     * @return  A string representing the double value in dollar monetary form.
     */
    private String formatPrice(double value) {
        String pattern = "$##,###.00";
        DecimalFormat decimalFormatter = new DecimalFormat(pattern);
        return decimalFormatter.format(value);
    }
}
