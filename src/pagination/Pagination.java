package pagination;

public class Pagination {
	
	private static int calculateFrom(int currentPage, int maxPerPage) {
		return maxPerPage*currentPage - (maxPerPage-1);
	}
	
	private static int calculateTo(int currentPage, int maxPerPage) {
		return maxPerPage*currentPage;
	}
}
