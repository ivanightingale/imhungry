package info;

import java.io.Serializable;

//A simple class that holds a String and an Object, good for communication
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

