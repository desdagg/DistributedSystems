import java.io.*;
import java.net.*;
import java.util.*;

public class TCPEchoServer
{
   private static ServerSocket servSock;
   private static final int PORT = 1234;

   public static void main(String[] args)
   {
      System.out.println("Opening port...\n");
      try
      {
         servSock = new ServerSocket(PORT);      //Step 1.
      }
      catch(IOException ioEx)
      {
         System.out.println("Unable to attach to port!");
         System.exit(1);
      }
      do
      {
         handleClient();
      }while (true);
   }

   private static void handleClient()
   {
      Socket link = null;                        //Step 2.

      try
      {
		  
          link = servSock.accept();               //Step 2.
		 
		 ObjectInputStream input = new ObjectInputStream(link.getInputStream());
		 
					
		 try{
			Personnel person = (Personnel)input.readObject();	
			System.out.println(
				"\nname   " + person.getName());
			System.out.println(
					"age    " + person.getAge());
			System.out.println(
					"address   : " + person.getAddress());					
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			} 
			catch(IOException ioEx)
			{
				ioEx.printStackTrace();
			}
			
		 }
		 catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
	}	
}

class Personnel implements Serializable
{
	private String personName;
	private int personAge;
	private String personAddress;

	public Personnel(String name,int age, String address)
	{
		personName = name;
		personAge = age;
		personAddress = address;
	}

	public String getName()
	{
		return personName;
	}

	public int getAge()
	{
		return personAge;
	}

	public String getAddress()
	{
		return personAddress;
	}

}