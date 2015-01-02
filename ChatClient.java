package com.za.tutorial.rmi.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import com.za.tutorial.rmi.server.ChatServerIF;

public class ChatClient extends UnicastRemoteObject implements ChatClientIF, Runnable{
	
	private static final long serialVersionUID = 1L;
	private ChatServerIF chatServer;
	private String name=null;

	protected ChatClient(String name, ChatServerIF chatServer) throws RemoteException {
		this.name= name;
		this.chatServer=chatServer;		
		chatServer.registerChatClient(this);
	}
	public void retrieveMessage(String message) throws RemoteException {
		System.out.println(message);		
	}
	public void run() {
		Scanner scanner= new Scanner(System.in);
		String message;
		while (true){
			message=scanner.nextLine();
			try {
				chatServer.broadcastMessage(name + " : " + message);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}		
	}
}
/*cd C:\Users\houssem\workspace\RMIPrj02\bin
 * set path=C:\Program Files\Java\jdk1.8.0_25\bin
 * rmic com.za.tutorial.rmi.server.ChatServer
 * rmic com.za.tutorial.rmi.client.ChatClient
 * rmiregistry
 * 
 * java com.za.tutorial.rmi.server.ChatServerDriver
 * 
 * java com.za.tutorial.rmi.client.ChatClientDriver houssem
 */
