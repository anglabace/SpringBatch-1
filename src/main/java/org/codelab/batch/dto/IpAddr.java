package org.codelab.batch.dto;

public class IpAddr {
	private String ip;
	private boolean filtered = false;

	public IpAddr() {
	}

	public IpAddr(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean isFiltered() {
		return filtered;
	}

	public void setFiltered(boolean filtered) {
		this.filtered = filtered;
	}

	@Override
	public String toString() {
		return ip;
	}
}