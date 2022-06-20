package com.example.firstSpringBoot.model;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class DeTarget {

	private String image1;
	private String user;
	private Product product;
	private String classes;

	public DeTarget(String image1,  String user,String classes) {
		this.image1=image1;
		this.user=user;
		this.product= Product.DeTarget;
		this.classes=classes;
	}

	public String getPre(){
		try(Socket connection=new Socket("localhost",9292)) {
			BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(),"UTF-8"));
			BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));

			writer.write(image1+"&"+user+"&"+product.name()+":"+classes);
			writer.flush();

			String line= reader.readLine();
			System.out.println(line);
			connection.close();
			return line;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "failed";
		} catch (IOException e) {
			Thread thread=new Thread(new RuntimePython());
			thread.start();
			e.printStackTrace();
			return "failed";
		}
	}
}
