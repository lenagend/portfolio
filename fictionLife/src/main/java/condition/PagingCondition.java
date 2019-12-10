package condition;

public class PagingCondition {
	private Integer startRow = 0;
	private Integer endRow = 0;
	private Integer pageNo;
	private Integer pageCnt = 0;
	private Integer currentPage = 0;
	private Integer cnt;
	
	//잡다한
	private Integer id;
	private String email;
	private String type;
	private String search;
	
	public void paging(Integer cnt, Integer pageNo, Integer rows) {

		if(cnt==null) this.cnt=0;
		else this.cnt= cnt;
		
		
		this.pageNo = pageNo;
		
		if(this.pageNo==null) this.currentPage =1;
		else this.currentPage = this.pageNo;
		
		if(this.cnt>0) {
			this.pageCnt = this.cnt/rows;
			if(this.cnt % rows>0) this.pageCnt++;
			this.startRow = (this.currentPage-1)*rows;
			this.endRow = this.currentPage * rows - 1;
			
			if(this.endRow > this.cnt) this.endRow = this.cnt;
			
		}
		
	}
	
	
	public String getSearch() {
		return search;
	}
	
	public void setSearch(String search) {
		this.search = search;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public Integer getEndRow() {
		return endRow;
	}
	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(Integer pageCnt) {
		this.pageCnt = pageCnt;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
    public Integer getCnt() {
		return cnt;
	}
    public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
}
