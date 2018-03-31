/**
 * ADITYA POTDAR SBUID - 109803771 HOMEWORK 6 CSE RECITATION 05 SUN LIN
 * 
 * @author ADITYA POTDAR
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

/*
 * simulates an online auction
 */
public class AuctionSystem implements Serializable {

	public static void main(String[] args) throws IOException {

		Scanner s = new Scanner(System.in);

		FileInputStream file;
		FileOutputStream file1;

		ObjectOutputStream outStream = null;
		System.out.println("Starting..");

		AuctionTable auctions = new AuctionTable();

		try {

			file = new FileInputStream("auction.obj");
			ObjectInputStream inStream = new ObjectInputStream(file);

			
			auctions = (AuctionTable) inStream.readObject();
		    System.out.println("Auction table found");

		     }catch (IOException e) {
			System.out.println("No previous auction table detected. \nCreating new table...");

			file1 = new FileOutputStream("auction.obj");
			outStream = new ObjectOutputStream(file1);
			auctions = new AuctionTable();
			
		}  catch (ClassNotFoundException e) {
			
		}

		System.out.print("Please select a username: ");
		String name = s.next();
		String input;

		do {

			System.out
					.println("Menu:\n    (D) - Import Data from URL\n    (A) - Create a New Auction\n    (B) - Bid on an Item\n    (I) - Get Info on Auction"
							+ "\n    (P) - Print All Auctions\n    (R) - Remove Expired Auctions\n    (T) - Let Time Pass\n    (Q) - Quit");
			System.out.print("Please select an option: ");
			input = s.next();
			if (input.equals("D")) {

				System.out.println("Please enter a URL:");
				String url = s.next();
				try{
				auctions = AuctionTable.buildFromURL(url);
				System.out.println("Loading...\n Auction data loaded successfully!");
				}catch(Exception e){
					System.out.println("The entered URL is incorect. Please try again");
				}

			}

			if (input.equals("A")) {
				System.out.println("Creating new Auction as " + name);
				System.out.print("Please enter an Auction ID: ");
				String id = s.next();
				System.out.print("Please enter an Auction time (hours): ");
				int time = s.nextInt();
				
				s.nextLine();
				System.out.print("Please enter some Item Info: ");
				String info = s.nextLine();
				
				if(time>0){
				Auction newAuction = new Auction(time, 0, id, name, "", info);
				auctions.put(id, newAuction);
				System.out.println("Auction " + id + " inserted into table.");
				}else{
					System.out.println("Auction could not be created because the time entered is negative.");
				}
			}

			if (input.equals("B")) {
				System.out.println("Please enter an Auction ID: ");
				String id = s.next();

				if (auctions.get(id).getTimeRemaining() == 0) {
					System.out.println("Auction " + id + "is CLOSED");
					System.out.println("Current Bid: "
							+ auctions.get(id).getCurrentBid()
							+ "\n\nYou can no longer bid on this item.");
				} else {
					System.out.println("Auction " + id + "is OPEN");
					System.out.println("Current Bid: "
							+ auctions.get(id).getCurrentBid());

					System.out.println("What would you like to bid?: ");
					double bid = s.nextDouble();

				
						auctions.get(id).newBid(name, bid);
						System.out.println("Bid accepted.");
						
					

					;
				}

			}

			if (input.equals("I")) {
				System.out.println("Please enter an Auction ID: ");
				String id = s.next();
				System.out.println("Auction " + id + ":");
				try{System.out.println("Seller: "
						+ auctions.get(id).getSellerName());
				System.out.println("Time: "
						+ auctions.get(id).getTimeRemaining());
				System.out.println("Info: " + auctions.get(id).getItemInfo());
				}catch(NullPointerException e){
					System.out.println("Entered auction ID is incorrect.");
				}
			}

			if (input.equals("P")) {
				auctions.printTable();
			}

			if (input.equals("R")) {
				auctions.removeExpiredAuctions();
			}

			if (input.equals("T")) {
				System.out.println("How many hours should pass: ");
				int hours = s.nextInt();
				if(hours>0){
				auctions.letTimePass(hours);
				System.out.println("Time passing... \nAuction times updated.");
				}else{
					System.out.println("Time cannot be negative.");
				}
			}

		} while (!input.equals("Q"));

		outStream.writeObject(auctions);
		System.out.println("Good bye");
	}
}
