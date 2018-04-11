package it.milano.project.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
	private static BufferedReader reader;
	private static FileWriter fstream;
	private static BufferedWriter out;
	
	public static void setFileHandler(String filein, String fileout)
	{
		try {
			if(null!=filein)
				reader = new BufferedReader(new FileReader(filein) );
			if(null!=fileout){
				fstream = new FileWriter(fileout);
				out = new BufferedWriter(fstream);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean exist(String file)
	{
		File f = new File (file);         
		return f.exists();
	}
	
	public static String readFile()
	{
		String s=new String();
		try {
			
			s=reader.readLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	public static void writeFile(String o)
	{
		
			try {
				out.write(o);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void writeFileLine(String o)
	{
		
			try {
				out.write(o);
				out.newLine();
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void closeFile()
	{
		try {
			if(out!=null)
				out.close();
			if(reader!=null)
				reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

