package day022;

public class MusicDTO {
	private int rank;
	private String title;
	private String singer;
	private String album;

	@Override
	public String toString() {
		return "MusicDTO [rank=" + rank + ", title=" + title + ", singer=" + singer + ", album=" + album + "]";
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

}
