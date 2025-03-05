import java.util.ArrayList;
import java.util.Scanner;

class Card {
    private String symbol;
    private String rank;

    public Card(String symbol, String rank) {
        this.symbol = symbol;
        this.rank = rank;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "Card: " + rank + " of " + symbol;
    }
}

public class CardCollection {
    private static ArrayList<Card> cards = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Card");
            System.out.println("2. Search by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addCard();
                    break;
                case 2:
                    searchBySymbol();
                    break;
                case 3:
                    displayCards();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addCard() {
        System.out.print("Enter Symbol (Hearts, Diamonds, etc.): ");
        String symbol = scanner.nextLine();
        System.out.print("Enter Rank (Ace, 2, King, etc.): ");
        String rank = scanner.nextLine();
        cards.add(new Card(symbol, rank));
        System.out.println("Card added successfully!");
    }

    private static void searchBySymbol() {
        System.out.print("Enter Symbol to search: ");
        String symbol = scanner.nextLine();
        boolean found = false;
        for (Card card : cards) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                System.out.println(card);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cards found with symbol " + symbol);
        }
    }

    private static void displayCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards stored.");
        } else {
            for (Card card : cards) {
                System.out.println(card);
            }
        }
    }
}
