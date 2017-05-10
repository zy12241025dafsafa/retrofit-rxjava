package com.meiya.cunnar.elecsignner;

import java.util.List;

public class IllegalReportResult {
	
	private boolean success;
	
	private DataResult data ;
	
	private String jsessionid ;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public DataResult getData() {
		return data;
	}

	public void setData(DataResult data) {
		this.data = data;
	}

	public String getJsessionid() {
		return jsessionid;
	}

	public void setJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
	}

	public static class DataResult {
		
		private int pageNo ;
		private int pageSize;
		private boolean overCount;
		private int maxPageNo;
		private List<IllegalReportBean> results ;
		private int totalNum ;
		
		public int getPageNo() {
			return pageNo;
		}
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public boolean isOverCount() {
			return overCount;
		}
		public void setOverCount(boolean overCount) {
			this.overCount = overCount;
		}
		public int getMaxPageNo() {
			return maxPageNo;
		}
		public void setMaxPageNo(int maxPageNo) {
			this.maxPageNo = maxPageNo;
		}
		public List<IllegalReportBean> getResults() {
			return results;
		}
		public void setResults(List<IllegalReportBean> results) {
			this.results = results;
		}
		public int getTotalNum() {
			return totalNum;
		}
		public void setTotalNum(int totalNum) {
			this.totalNum = totalNum;
		}
		
	}
}
