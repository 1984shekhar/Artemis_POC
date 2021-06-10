package com.example.demo.dto;

import java.io.Serializable;
import java.util.Objects;

public class Pojo implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	private String				key;

	private String				value;

	public Pojo()
	{
		super();
	}

	public Pojo(String key, String value)
	{
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(key, value);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pojo other = (Pojo) obj;
		return Objects.equals(key, other.key) && Objects.equals(value, other.value);
	}
	 @Override
	    public String toString() {
	        return "Pojo{" +
	                "key='" + key + '\'' +
	                ", value='" + value + '\'' +
	                '}';
	    }

}
