package com.jetbrains;

public class Main {

    public static void main(String[] args) {
	    BookList bookList = new BookList();

	    Book book1 = new Book("Moby-Dick", 5, 15.95, "Penguin");
	    Book book2 = new Book("Redwall", 3, 8.99, "Philomel");
	    Book book3 = new Book("Charlotte's Web", 8, 5.99, "Harper Collins");
	    Book book4 = new Book("Beren and Luthien", 6, 29.99, "Houghton Mifflin Harcourt");
	    Book book5 = new Book("Lord Brocktree", 2, 8.99, "Philomel");

	    boolean result1 = bookList.addBook(book1);
        boolean result2 = bookList.addBook(book2);
        boolean result3 = bookList.addBook(book3);
        boolean result4 = bookList.addBook(book4);
        boolean result5 = bookList.addBook(book5);
        System.out.println("Adding books . . .");
        if (result1 && result2 && result3 && result4 && result5) {
            bookList.showBooks();
            System.out.println("Total quantity of books: " + bookList.getTotalQuantity(bookList.getBookListHead()));
            System.out.println("Total nodes in list: " + bookList.countBookNodes(bookList.getBookListHead()));
        } else {
            System.out.println("Failed to add book(s).\n");
        }

        System.out.println("\n\nSorting books . . .");
        bookList.sortBooks();
        bookList.showBooks();
        System.out.println("Total quantity of books: " + bookList.getTotalQuantity(bookList.getBookListHead()));
        System.out.println("Total nodes in list: " + bookList.countBookNodes(bookList.getBookListHead()));
        System.out.println();

        String bookTitle = "Lord Brocktree", bookPublisher = "Philomel";
        System.out.println("\nRemoving \"" + bookTitle + "\" published by " + bookPublisher + " . . .");
        boolean delResult = bookList.removeBook(bookTitle, bookPublisher);
        if (delResult) {
            bookList.showBooks();
            System.out.println("Total quantity of books after removal: " + bookList.getTotalQuantity(bookList.getBookListHead()));
            System.out.println("Total nodes in list: " + bookList.countBookNodes(bookList.getBookListHead()));
        } else {
            System.out.println("Failed to remove \"" + bookTitle + "\" published by " + bookPublisher + "\n");
        }

    }
}
