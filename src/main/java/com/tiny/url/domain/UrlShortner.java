package com.tiny.url.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This Pojo is created for UrlShorner entity 
 * @author Krishna Sure
 * @Version 1.0
 */
public class UrlShortner implements Serializable{
	
	/**
	 * added generated serialVersionUID
	 */
	private static final long serialVersionUID = 4679332650609283059L;
	
	private String longUrl;
	private String shortUrl;
	private LocalDateTime createdTime;
	private LocalDateTime expiredTime;
	
	public UrlShortner() {}
	
	/**
	 * 
	 * @param longUrl It is long url 
	 * @param shortUrl It is short url 
	 * @param createdTime It is created date time
	 * @param expiredTime It is expired date time
	 */
	public UrlShortner(String longUrl, String shortUrl, LocalDateTime createdTime, LocalDateTime expiredTime) {
		super();
		this.longUrl = longUrl;
		this.shortUrl = shortUrl;
		this.createdTime = createdTime;
		this.expiredTime = expiredTime;
	}


	//getters and setters
	public String getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}
	public LocalDateTime getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(LocalDateTime expiredTime) {		
		this.expiredTime = expiredTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((expiredTime == null) ? 0 : expiredTime.hashCode());
		result = prime * result + ((longUrl == null) ? 0 : longUrl.hashCode());
		result = prime * result + ((shortUrl == null) ? 0 : shortUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UrlShortner other = (UrlShortner) obj;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (expiredTime == null) {
			if (other.expiredTime != null)
				return false;
		} else if (!expiredTime.equals(other.expiredTime))
			return false;
		if (longUrl == null) {
			if (other.longUrl != null)
				return false;
		} else if (!longUrl.equals(other.longUrl))
			return false;
		if (shortUrl == null) {
			if (other.shortUrl != null)
				return false;
		} else if (!shortUrl.equals(other.shortUrl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UrlShortner [longUrl=" + longUrl + ", shortUrl=" + shortUrl + ", createdTime=" + createdTime
				+ ", expiredTime=" + expiredTime + "]";
	}

}
