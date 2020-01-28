package youtube;

public class YoutubeHttp {
	private static final String youtubeURLLong = "www.youtube.com/watch?v=";
	private static final String youtubeURLShort = "youtu.be/";
	
	/**
	 * This enum is for Thumbnail Quality and its reference for thumbnail retrival. 
	 * Note that STANDARD and MAXIMUM may not be available for the video and may be get the daefautl empty thumbnail 
	 * @author Thalisson Christiano de Almeida
	 *
	 */
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
	
	/**
	 * This enum is for Thumbnail Selection and its reference for thumbnail retrival.
	 * Note that PLAYER_BACKGROUND must have DEFAULT quality to be retrieved  
	 * @author Thalisson Christiano de Almeida
	 *
	 */
	public enum ThumbnailOption{
		DEFAULT("default.jpg"),
		START("1.jpg"),
		MIDDLE("2.jpg"),
		END("3.jpg"),
		PLAYER_BACKGROUND("0.jpg");
		
		private String keyword;
		
		ThumbnailOption(String keyword){
			this.keyword = keyword;
		}
		
		@Override
		public String toString() {
			return this.keyword;
		}
	}
	
	
	private static String getYoutubeThumbnail(String videoID, ThumbnailQuality quality, ThumbnailOption option) {
		
		return "https://img.youtube.com/vi/"+videoID+"/"+quality+option;
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
