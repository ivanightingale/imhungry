package info;

import java.io.Serializable;

public class Message implements Serializable
{
	public String header;
	public Object body;

	public Message(String header, Object body)
	{
		this.header = header;
		this.body = body;
	}

	public Message(String header)
	{
		this.header = header;
		body = null;
	}
}

