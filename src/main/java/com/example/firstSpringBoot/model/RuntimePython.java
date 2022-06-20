package com.example.firstSpringBoot.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RuntimePython implements Runnable{

	public void run() {
		Process proc;
		try {
			String root=System.getProperty("user.dir");
			proc = Runtime.getRuntime().exec("python3 src/main/resources/static/python/product_thread.py "+root);
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}
}
