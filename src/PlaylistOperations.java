import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

/**
 * 
 * @author Suraj Sharma ID # 109606910 
 * Home Work No. 1 
 * Source for MP3 Class :
 *         http://introcs.cs.princeton.edu/java/faq/mp3/MP3.java.html
 */
public class PlaylistOperations {
	public static int SIZE = 50;
	Playlist[] listOfPLaylists;
	private int number_of_current_playlists;

	public PlaylistOperations() {
		listOfPLaylists = new Playlist[SIZE];
		number_of_current_playlists = 0;
	}

	public static void main(String[] args) throws InputMismatchException {

		/*
		 * Store the mp3 as filename.mp3 to make the song to play
		 */
		String filename = "filename.mp3";
		MP3 mp3 = new MP3(filename);

		Scanner s = new Scanner(System.in);

		PlaylistOperations list = new PlaylistOperations();
		Playlist user = new Playlist();

		String input = "";
		char menuOption = '.';

		System.out.print("Please Name your First Playlist: ");

		list.listOfPLaylists[list.number_of_current_playlists] = new Playlist();
		user = list.listOfPLaylists[list.number_of_current_playlists];
		list.number_of_current_playlists++;
		input = s.next();
		user.setName(input);

		String play = "PLAY";
		int count = 0;

		while (menuOption != 'q' || menuOption != 'Q') {

			try {
				System.out.println();
				System.out.println("Current Playlist: " + user.getName());
				System.out.println();
				System.out.println("Menu Options: ");
				System.out.println("Select from the options below: ");
				System.out.println();
				System.out.println("N to Create a new Playlist");
				System.out.println("V to Change the current Playlist");
				System.out.println("C to Copy " + user.getName()
						+ "'s songs to a new playlist");
				System.out.println("E to Compare two Playlists");
				System.out.println("D to View all your Playlists");
				System.out.println();

				System.out.println("A to Add a song");
				System.out.println("B to Print Songs by Artist");
				System.out.println("G to Search a Song");
				System.out.println("R to Remove a Song ");
				System.out.println("P to Print All Songs in " + user.getName());
				System.out.println("S to know the number of songs in "
						+ user.getName());
				System.out.println("F to " + play + " the song");
				System.out.println("Q to Quit the program");
				System.out.println();
				System.out.print("Your Choice: ");
				menuOption = s.next().charAt(0);
				System.out.println();

				if (menuOption == 'n' || menuOption == 'N') {
					System.out
							.print("Please Enter the name of your new Playlist: ");
					list.listOfPLaylists[list.number_of_current_playlists] = new Playlist();
					user = list.listOfPLaylists[list.number_of_current_playlists];
					list.number_of_current_playlists++;
					input = s.next();
					user.setName(input);

				} else if (menuOption == 'f' || menuOption == 'F') {

					if (count == 0) {
						mp3.play();
						count = 1;
						play = "PAUSE";
					} else if (count == 1) {
						mp3.close();
						count = 0;
						play = "PLAY";
					}

				} else if (menuOption == 'v' || menuOption == 'V') {
					System.out.print("Please the name of the playlist: ");
					input = s.next();
					int flag = 0;
					for (int i = 0; i < list.number_of_current_playlists; i++) {
						if (list.listOfPLaylists[i].getName().equals(input)) {

							user = list.listOfPLaylists[i];
							flag++;
						}
					}
					if (flag == 1)
						System.out
								.println("Your current Playlist is changed to "
										+ user.getName());
					else
						System.out.println("The Playlist is not found");

				} else if (menuOption == 'c' || menuOption == 'C') {
					System.out
							.print("Please Enter the name of your new Playlist: ");

					try {
						list.listOfPLaylists[list.number_of_current_playlists] = (Playlist) user
								.clone();
						user = list.listOfPLaylists[list.number_of_current_playlists];
						user.setName(s.next());
						list.number_of_current_playlists++;
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (menuOption == 'e' || menuOption == 'E') {
					System.out
							.print("Please Enter the Playlist you want to compare "
									+ user.getName() + " with: ");
					input = s.next();
					Playlist compare = new Playlist();
					for (int i = 0; i < list.number_of_current_playlists; i++)
						if (list.listOfPLaylists[i].getName().equals(input))
							compare = list.listOfPLaylists[i];
					if (user.equals(compare))
						System.out
								.println("The two playlists have same songs in same order");
					else {
						System.out
								.println("The two Playlists are not the same");
					}

				} else if (menuOption == 'd' || menuOption == 'D') {
					System.out.println("The list of Playlist:");
					System.out
							.println("--------------------------------------------------------------------");
					for (int i = 0; i < list.number_of_current_playlists; i++)
						System.out.println(i + 1 + " "
								+ list.listOfPLaylists[i].getName());

				}

				else if (menuOption == 'a' || menuOption == 'A') {

					SongRecord newSong = new SongRecord();
					System.out
							.println("Please the following details about your song");

					System.out.print("Enter the title: ");
					input = s.next();
					newSong.setTitle(input);

					System.out.print("Enter the artist: ");
					input = s.next();
					newSong.setArtist(input);
					System.out.print("Enter the length (in minutes): ");
					int temp = s.nextInt();
					newSong.setMins(temp);
					System.out.print("Enter the length (in seconds): ");
					temp = s.nextInt();
					newSong.setSecs(temp);
					System.out.print("Enter the position: ");
					int position = s.nextInt();
					user.addSong(newSong, position);
					System.out.println("You added " + newSong.getTitle()
							+ " to " + user.getName());

				} else if (menuOption == 'b' || menuOption == 'B') {
					Playlist searchResult = new Playlist();
					System.out.print("Please Enter the Artist's Name: ");
					String search = s.next();
					searchResult = Playlist.getSongsByArtist(user, search);

					System.out.println("Displaying all the songs by " + search);
					System.out.println();

					if (searchResult.size() == 0)
						System.out.println("You have no songs by " + search
								+ " in " + user.getName());

					searchResult.printAllSongs();

				} else if (menuOption == 'g' || menuOption == 'G') {
					System.out.print("Enter the position: ");
					int search = s.nextInt();
					System.out.println(user.getSong(search).toString());

				} else if (menuOption == 'r' || menuOption == 'R') {

					System.out.print("Enter the position: ");
					int position = s.nextInt();
					user.removeSong(position);
					System.out.println("The Song #" + position
							+ " has been removed from " + user.getName());

				} else if (menuOption == 'p' || menuOption == 'P') {

					if (user.size() == 0)
						System.out.println("There is no song to diplay!!");
					else
						user.printAllSongs();

				} else if (menuOption == 's' || menuOption == 'S') {

					System.out.println("You Currently have " + user.size()
							+ " songs in " + user.getName());

				} else {
					System.out
							.println("Invalid Input!! Please Enter a valid option!");
				}
				System.out.println();
				System.out
						.println("--------------------------------------------------------------------");
				System.out.println();

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (FullPlaylistException e) {
				// TODO Auto-generated catch block
				System.out
						.println("The Playlist is full!, Please select another playlist");
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input, Please try again!");

			} catch (NullPointerException e) {
				System.out.println("The quantity you're comparing is null");
			}

		}
		System.out.println("Thank you!!");
	}

	/**
	 * 
	 * @return the number of playlists present.
	 */
	public int getNumberOfCurrentPlaylists() {
		return number_of_current_playlists;
	}

}

/**
 * 
 * This class is used to play the Mp3 file. The source for this class is
 * specified above.
 */
class MP3 {
	private String filename;
	private Player player;

	// constructor that takes the name of an MP3 file
	public MP3(String filename) {
		this.filename = filename;
	}

	public void close() {
		if (player != null)
			player.close();
	}

	// play the MP3 file to the sound card
	public void play() {
		try {
			FileInputStream fis = new FileInputStream(filename);
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println("Problem playing file " + filename);
			System.out.println(e);
		}

		// run in new thread to play in background
		new Thread() {
			public void run() {
				try {
					player.play();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}.start();
	}

}
