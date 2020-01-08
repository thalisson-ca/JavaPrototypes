package youtube;

public class YoutubeHttp {
	private static String youtubeURLLong = "www.youtube.com/watch?v=";
	private static String youtubeURLShort = "youtu.be/";
	
	public enum ThumbnailQuality{
		DEFAULT("default.jpg"),
		HIGH("hqdefault.jpg"),
		MEDIUM("mqdefault.jpg"),
		STANDARD("sddefault.jpg"),
		MAXIMUM("maxresdefault.jpg");
		
		private String keyword;
		
		ThumbnailQuality(String keyword){
			this.keyword = keyword;
		}
		
		@Override
		public String toString() {
			return this.keyword;
		}
	}
	
	
	private static String getYoutubeThumbnail(String videoID, ThumbnailQuality quality) {
		
		return "https://img.youtube.com/vi/"+videoID+"/"+quality;
	}


	private static String getYoutubeEmbedURL(String videoID) {
		return "https://www.youtube.com/embed/"+videoID;
		
	}


	private static String getVideoIDFromYoutubeURL(String videoURL) {
		String videoID = null;
		videoURL = videoURL.replace("https://", "");
		videoURL = videoURL.replace("http://", "");
		
		if(videoURL.contains(youtubeURLLong)) {
		 	videoID = videoURL.replace("youtubeURLLong", "");
		 	
		}else if(videoURL.contains(youtubeURLShort)) {
			videoID = videoURL.replace(youtubeURLShort, "");
		}
		videoID = videoID.replace("/", "");
		
		return videoID;
	}
}
