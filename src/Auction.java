/**
 * ADITYA POTDAR SBUID - 109803771 HOMEWORK 6 CSE RECITATION 05 SUN LIN
 * 
 * @author ADITYA POTDAR
 */

import java.io.Serializable;

public class Auction implements Serializable {

	private int timeRemaining;

	private double currentBid;

	private String auctionID;
	private String sellerName;
	private String buyerName;
	private String itemInfo;

	/*
	 * creates an empty Auction object
	 */
	public Auction() {

	}

	/*
	 * creates an Auction object with specifies arguments
	 */
	public Auction(int timeRemaining, double currentBid, String auctionID,
			String sellerName, String buyerName, String itemInfo) {
		this.timeRemaining = timeRemaining;
		this.currentBid = currentBid;
		this.auctionID = auctionID;
		this.sellerName = sellerName;
		this.buyerName = buyerName;
		this.itemInfo = itemInfo;
	}

	/*
	 * decrements the time member varible with specified time
	 */
	public void decrementTimeRemaining(int time) {

		if (timeRemaining < time) {
			timeRemaining = 0;
		} else {
			timeRemaining = timeRemaining - time;
		}
	}

	/*
	 * introduces a new bid
	 * 
	 * @param bidderName
	 * 
	 * @param bidAmt
	 * 
	 * @throws ClosedAuctionException
	 */
	public void newBid(String bidderName, double bidAmt)
			{

		if (bidAmt > currentBid) {
			currentBid = bidAmt;
			buyerName = bidderName;
		
	}}

	/*
	 * gives String representation of the object
	 */
        @Override
	 public String toString() {
		String str = "";
		String a = this.auctionID;
		double b = this.currentBid;
		String c = this.sellerName;
		String d = this.buyerName;
		int e = this.timeRemaining;
		String f = this.itemInfo;

		str = str
				+ String.format("%15s|%15s|%20s|%25s|%10s| %25s", a, b, c, d,
						e, f);

		return str;
	}

	/*
	 * returns time remaining
	 */
	public int getTimeRemaining() {
		return timeRemaining;
	}

	/*
	 * returns currentBid
	 */
	public double getCurrentBid() {
		return currentBid;
	}

	/*
	 * returns AuctionID
	 */
	public String getAuctionID() {
		return auctionID;
	}

	/*
	 * returns SellerName
	 */
	public String getSellerName() {
		return sellerName;
	}

	/*
	 * returns buyerName
	 */
	public String getBuyerName() {
		return buyerName;
	}

	/*
	 * returns itemInfo
	 */
	public String getItemInfo() {
		return itemInfo;
	}
}
