package seantcanavan;

import java.util.Scanner;

public class SeanHashTableTest {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		SeanHashTable sht = new SeanHashTable();
		boolean done = false;

		do {
			System.out.println("1. insert ");
			System.out.println("2. remove");
			System.out.println("3. get");
			System.out.println("4. clear");
			System.out.println("5. size");
			System.out.println("6. print");
			System.out.println("7. exit");

			int choice = scan.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter key");
				String insertKey = scan.next();
				System.out.println("Ente value");
				String insertValue = scan.next();
				sht.insert(insertKey, insertValue);
				break;
			case 2:
				System.out.println("Key of value to remove?");
				String removeKey = scan.next();
				sht.remove(removeKey);
				break;
			case 3:
				System.out.println("Key of value to retrieve?");
				String getKey = scan.next();
				System.out.println("Retrieved: " + sht.get(getKey));
				break;
			case 4:
				sht.clear();
				System.out.println("Hash Table cleared");
				break;
			case 5:
				System.out.println("size: " + sht.getSize());
				break;
			case 6:
				System.out.println("Printing...");
				sht.printHashTable();
				break;
			case 7:
				done = true;
				break;
			default:
				System.out.println("Invalid item. Try again.");
				break;
			}
		} while (!done);
		scan.close();
	}
}