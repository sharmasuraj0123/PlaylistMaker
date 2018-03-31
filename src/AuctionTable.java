/**
 * ADITYA POTDAR SBUID - 109803771 HOMEWORK 6 CSE RECITATION 05 SUN LIN
 * 
 * @author ADITYA POTDAR
 */

import big.data.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.activation.DataSource;

public class AuctionTable implements Serializable {

	HashMap<String, Auction> map = new HashMap<String, Auction>();

	/*
	 * creates an empty AuctionTable object
	 */
	public AuctionTable() {

	}

	/*
	 * creates an empty auction table object with specified arguments
	 */
	public AuctionTable(int[] timeLeft, double[] currentBids, String[] id_num,
			String[] sellerNames, String[] bidder_name, String[] memory,
			String[] hardDrive, String[] cpu) {

		Auction[] list = new Auction[100];
		int i = 0;
		while (i < sellerNames.length) { // sellerNames[i]==null

			String features = "" + memory[i] + " " + hardDrive[i] + " "
					+ cpu[i];
			Auction temp = new Auction(timeLeft[i], currentBids[i], id_num[i],
					sellerNames[i], bidder_name[i], features);
			map.put(temp.getAuctionID(), temp);
			i++;
		}

	}

	/*
	 * creates an AuctionTable object from entered URL
	 */
	public static AuctionTable buildFromURL(String URL)
			throws IllegalArgumentException {
		
		
		DataSource ds = DataSource.connect(URL).load();

		String[] sellerNames = ds
				.fetchStringArray("listing/seller_info/seller_name");
		String[] currentBid = ds
				.fetchStringArray("listing/auction_info/current_bid");

		double[] currentBids = new double[100];
		int i = 0;
		while (i < currentBid.length) {
			double x = (Double) Double.parseDouble(currentBid[i].substring(1)
					.replace(",", ""));
			currentBids[i] = x;
			i++;
		}

		String[] timeLeft = ds
				.fetchStringArray("listing/auction_info/time_left");
		int[] timeLefts = new int[100];
		i = 0;
		while (i < currentBid.length) {
			String str = "";
			int j = 0;
			while (timeLeft[i].charAt(j) != ' ') {
				str = str + timeLeft[i].charAt(j);
				j++;
			}

			int x = Integer.parseInt(str);
			timeLefts[i] = x;
			i++;
		}

		String[] id_num = ((Object) ds).fetchStringArray("listing/auction_info/id_num");
		String[] bidder_name = ds
				.fetchStringArray("listing/auction_info/high_bidder/bidder_name");

		String[] memory = ds.fetchStringArray("listing/item_info/memory");
		String[] hardDrive = ds
				.fetchStringArray("listing/item_info/hard_drive");
		String[] cpu = ds.fetchStringArray("listing/item_info/cpu");

		AuctionTable table = new AuctionTable(timeLefts, currentBids, id_num,
				sellerNames, bidder_name, memory, hardDrive, cpu);

		return table;
	}

	/*
	 * adds an auction to the auctionTable
	 */
	public boolean put(String auctionID, Auction auction)
			throws IllegalArgumentException {

		map.put(auctionID, auction);

		boolean repeated = map.containsKey(auctionID);

		if (!repeated) {
			throw new IllegalArgumentException();
		}

		return true;
	}

	/*
	 * returns auction of entered auctionID
	 */
	public Auction get(String auctionID) {
		return (Auction) map.get(auctionID);
	}

	/*
	 * simulates the passing of time
	 * 
	 * @param numHours
	 */
	public void letTimePass(int numHours) throws IllegalArgumentException {

		for (Map.Entry<String, Auction> entry : map.entrySet()) {
			String key = entry.getKey();
			Auction value = (Auction) entry.getValue();
			value.decrementTimeRemaining(numHours);
			map.put(key, value);
		}
	}

	/*
	 * removes all the expired auctions
	 */
	public void removeExpiredAuctions() {

		Iterator<Map.Entry<String, Auction>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Auction> entry = iter.next();
			if (entry.getValue().getTimeRemaining() <= 0) {
				iter.remove();
			}
		}
	}

	/*
	 * prints current auctionTable in tabular form
	 */
	public void printTable() {
		System.out.format("%15s|%15s|%20s|%25s|%10s| %25s", "Auction ID",
				"Bid", "Seller", "Buyer", "Time", "Item Info");
		System.out.println();
		System.out
				.println("==================================================================================================================");

		for (Auction value : map.values()) {
			System.out.println(value.toString());
		}
	}

}
