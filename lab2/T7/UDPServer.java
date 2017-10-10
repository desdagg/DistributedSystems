import java.net.*;
import java.io.*;

public class UDPServer{
    public static void main(String args[]){
    	DatagramSocket aSocket = null;

        int randomNumber = (int)(Math.random() * 100);
        System.out.println("rnd: " + randomNumber);
		try{
	    	aSocket = new DatagramSocket(6789);
 			while(true){
                byte[] buffer = new byte[1000];
 				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                
  				aSocket.receive(request);
                System.out.println("request: " + new String(request.getData()));

                String guessString = new String(request.getData()).trim();
                int guess = 0;
                guess = Integer.parseInt(guessString);
                byte[] responseArr = new byte[40];
                String responseString = "";
                int buffLength = 0;

                if(guess > randomNumber)
                {
                    responseString = "LOWER";
                }
                else if(guess == randomNumber)
                {
                    responseString = "CORRECT";
                }
                else if(guess < randomNumber)
                {
                    responseString = "HIGHER";
                }

                responseArr = responseString.getBytes();
                buffLength = responseString.length();

    			DatagramPacket reply = new DatagramPacket(responseArr, buffLength,
    				request.getAddress(), request.getPort());
    			aSocket.send(reply);
                if (responseString == "CORRECT")
                    aSocket.close();
    		}
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e) {System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
    }
}
