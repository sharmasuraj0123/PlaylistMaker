import java.util.InputMismatchException;

/**
 * 
 * @author Suraj Sharma 
 * ID # 109606910 
 * Home Work No. 1
 */
public class Playlist {

	public static final int MAX_SONGS = 50;
	SongRecord[] playlist;
	private int songs_currently_in_playlist;
	private String name;

	public Playlist() {
		playlist = new SongRecord[MAX_SONGS];
		songs_currently_in_playlist = 0;
	}
	/**
	 * this method get the song at a specific position.
	 * @param position - the song# one wants
	 * @return a SongRecord object from the list
	 * @throws IllegalArgumentException - if the user enters an incorrect value that is either negative value or value greater than the number of songs
	 * @throws InputMismatchException - If the user tries to enter wrong input
	 */
	public SongRecord getSong(int position) throws IllegalArgumentException,InputMismatchException {

		if (position > this.size() || position <= 0)
			throw new IllegalArgumentException("The position is not valid");

		return playlist[position - 1];
	}
	/**
	 * This method clones the Playlist object and returns it.
	 */
	@Override
	/**
	 * It copies the playlist.
	 */
	public Object clone() throws CloneNotSupportedException {
		Playlist cloned = new Playlist();
		for(int i=0 ;i <= songs_currently_in_playlist;i++){
			cloned.playlist[i] = this.playlist[i];
		}
			
		//System.arraycopy(this.playlist, 0, cloned.playlist, 0,
				//this.songs_currently_in_playlist);
		cloned.songs_currently_in_playlist = this.songs_currently_in_playlist;
		return cloned;
	}
	
	/*public static void main( String[] args) {
		Playlist original = new Playlist();
		SongRecord newSong = new SongRecord();
		try {
			newSong.setMins(12);
			original.addSong(newSong, 1);
			Playlist clonedList = (Playlist) original.clone();
			newSong.setMins(13);
			System.out.println(clonedList.getSong(1).getMins());
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (FullPlaylistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		
	} */
	/**
	 * This method checks whether the 2 Playlists are the same or not. The order songs are arranged in the playlist matters here.
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Playlist && ((Playlist) obj).size()==this.size()) {
			for (int i = 0; i < songs_currently_in_playlist; i++) {
				if (((Playlist) obj).playlist[i].equals(this.playlist[i])) {

				} else
					return false;
			}
			return true;
		} else
			return false;

	}
	/**
	 * 
	 * @return the number of songs in the Playlist.
	 */
	public int size() {
		return songs_currently_in_playlist;
	}
	
	/**
	 * This method is used to add song to the playlist
	 * @param song - the SongRecord object you want to insert
	 * @param position - the position you want to insert the song at.
	 * @throws IllegalArgumentException - if the user enters invalid position.
	 * @throws FullPlaylistException- if the playlist is full that is it has maximum number of elements the arrray can hold
	 */
	public void addSong(SongRecord song, int position)
			throws IllegalArgumentException, FullPlaylistException {

		if (songs_currently_in_playlist == MAX_SONGS)
			throw new FullPlaylistException("The Playlist is full!");

		if (position > this.songs_currently_in_playlist + 1 || position <= 0)
			throw new IllegalArgumentException("The position is not valid");

		for (int i = songs_currently_in_playlist - 1; i >= position - 1; i--) {
			playlist[i + 1] = playlist[i];
		}
		playlist[position - 1] = song;
		songs_currently_in_playlist++;
	}
	/**
	 * This method removes the song
	 * @param position - the position you want to remove the song from
	 * @throws IllegalArgumentException- if the position entered is invalid.
	 */
	public void removeSong(int position) throws IllegalArgumentException {
		if (position > songs_currently_in_playlist || position <= 0)
			throw new IllegalArgumentException("The position is not valid");

		for (int i = position - 1; i <= songs_currently_in_playlist - 2; i++) {
			playlist[i] = playlist[i + 1];
		}
		playlist[songs_currently_in_playlist - 1] = null;
		songs_currently_in_playlist--;
	}
	/**
	 * This method is used to print all songs in the current Playlist.
	 */
	public void printAllSongs() {
		System.out.printf("%-10s%-25s%-27s%-6s", "Song #", "Title" , "Artist" , "Length");
		System.out.println();
		System.out.println("--------------------------------------------------------------------");
		
		
		for (int i = 0; i < songs_currently_in_playlist; i++) {
			System.out.printf("%-11d%-59s",i + 1 , playlist[i].toString());
			System.out.println();
		}
		
	}
	/**
	 * This method is a search method used to search song by artist 
	 * @param originalList - the Playlist you want to search the song in
	 * @param artist - the artist you want
	 * @return all the songs arranged in a playlist by  specific artist
	 */
	public static Playlist getSongsByArtist(Playlist originalList, String artist) {
		int count = 0;
		Playlist newPlaylist = new Playlist();

		for (int i = 0; i < originalList.size(); i++) {
			if (originalList.playlist[i].getArtist().equals(artist)) {
				try {
					newPlaylist.addSong(originalList.playlist[i], count+1);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.getMessage();
				} catch (FullPlaylistException e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}
				count++;
			}
		}
		return newPlaylist;

	}
	/**
	 * Gets the String representation of this Playlist object, which is a neatly formatted table of each SongRecord in the Playlist on its own line with its position number as shown in the sample output.
	 */
	public String toString(){
		String s="Song#\t" + "Title\t" + "Artist\t" + "Length\n"
                + "------------------------------\n";

		for (int i = 0; i < songs_currently_in_playlist; i++){
			s += i + "\t" + playlist[i].getTitle() + "\t"
					                    + playlist[i].getArtist() + "\t"
					                    + playlist[i].getMins() + ":"
					                    + playlist[i].getSecs() + "\n";

		}
		return s;
	}
	/**
	 * This method returns the name of the Playlist
	 * @return name - the variable used to switch between multiple Playlists.
	 */
	public String getName() {
		return name;
	}
	/**
	 * This method sets the name of the current playlist to the desired value
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}


}
