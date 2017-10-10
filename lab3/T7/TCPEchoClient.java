import java.io.*;
import java.net.*;
import java.util.*;

public class TCPEchoClient
{
	private static InetAddress host;
	private static final int PORT = 1234;

	public static void main(String[] args)
	{
		///scanner chap
		Scanner input = new Scanner(System.in);
		String inHost;
		System.out.print("\n\nEnter host name: ");
		inHost = input.next();

		try
		{
			host = InetAddress.getByName(inHost);
			System.out.println("IP address: " + host.toString());
			//host = InetAddress.getLocalHost();
		}
		catch(UnknownHostException uhEx)
		{
			System.out.println("Host ID not found!");
			System.exit(1);
		}

		//System.out.println("host ip is: " + host.toString());

		accessServer();
	}

	private static void accessServer()
	{
		Socket link = null;						//Step 1.
		System.out.println("socket");
		try
		{
			link = new Socket(host,PORT);		//Step 1.
			System.out.println("link ");
			Scanner input = new Scanner(
								link.getInputStream());//Step 2.

			//PrintWriter output =
				//new PrintWriter(
					//link.getOutputStream(),true);//Step 2.

			ObjectOutputStream output = new ObjectOutputStream(link.getOutputStream());
			//Set up stream for keyboard entry...
			Scanner userEntry = new Scanner(System.in);

			//Personnel person = new Personnel()
			String name, address, strAge;
			int age = 0;
			String message, response;
			do
			{
				System.out.print("Enter name: ");
				name = userEntry.nextLine();
				System.out.print("Enter age: ");
				strAge = userEntry.nextLine().trim();
				age = Integer.parseInt(strAge);
				System.out.print("Enter address ");
				address  = userEntry.nextLine();
				Personnel person = new Personnel(name, age, address);
				message = "hello";
				
				output.writeObject(person);
				output.close();
				//message =  userEntry.nextLine();
				
				//ObjectOutputStream outStream =
				//	new ObjectOutputStream();
				
				//outStream.writeObject(person);
				
				//output.println(person); 		//Step 3.
				//response = input.nextLine();	//Step 3.
				System.out.println("\nSERVER> " /*+ response*/);
			}while (!message.equals("***CLOSE***"));
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}

		finally
		{
			try
			{
				System.out.println(
							"\n* Closing connection... *");
				link.close();					//Step 4.
			}
			catch(IOException ioEx)
			{
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}
