import java.util.InputMismatchException;

/**
 * 
 * @author Suraj Sharma 
 * ID # 109606910 
 * Home Work No. 1
 */
public class SongRecord {
private String title;
private String artist;
private int mins;
private int secs;

/**
 * 
 */
SongRecord(){}
/**
 * this is used to get the song
 * @returns seconds
 */
public int getSecs() {
	return secs;
}
/**
 * This method throws exception if you try to put invalid value of seconds
 * @param secs- the value you want to set the seconds to
 * @throws IllegalArgumentException
 * @throws InputMismatchException
 */
public void setSecs(int secs) throws IllegalArgumentException, InputMismatchException {
	if (secs >= 60 || secs<0)
		throw new IllegalArgumentException("Invalid seconds. Please make sure that the seconds are between 0 and 60");
	else this.secs = secs;
}
/**
 * this method returns minutes
 * @return minutes
 */
public int getMins(){
	return mins;
}
/**
 * This method sets the value of the minutes
 * @param mins
 * @throws InputMismatchException
 */
public void setMins(int mins) throws InputMismatchException {
	this.mins = mins;
}
/**
 * This method is used to return the artist for a specific song record
 * @return Artist for the song record
 */
public String getArtist() {
	return artist;
}
/**
 * This method sets the name of the artist
 * @param artist- the given argument you want to set your artist name to.
 */
public void setArtist(String artist) {
	this.artist = artist;
}
/**
 * This method is used to return the title of the song
 * @return title
 */
public String getTitle() {
	return title;
}
/**
 * This method is used to set the title of the song to a specific value
 * @param title- the given argument you want to set your title name to.
 */
public void setTitle(String title) {
	this.title = title;
}
/**
 * It returns the entire object in form of a string.
 */
@Override
public String toString(){
	return String.format("%-25s%-25s%3d%1s%02d", title , artist , mins , ":",secs);

}
/**
 * Creates a clone of the songRecord object
 */
@Override
public Object clone() throws CloneNotSupportedException {
    return super.clone();
}

/**
 * 
 */
@Override
public boolean equals (Object obj){
	if(obj instanceof SongRecord)
			return ((SongRecord) obj).getMins()== this.mins 
			&&((SongRecord) obj).getSecs()== this.secs && ((SongRecord) obj).getArtist().equals(this.artist)
			&& ((SongRecord) obj).getTitle().equals(this.title);
	else return false;
}


}
