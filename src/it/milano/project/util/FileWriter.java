package it.milano.project.util;

	
public class FileWriter {

	@SuppressWarnings("static-access")
	public static void writeCSV(StringBuilder sb,String name,String header) {
		FileHandler f = new FileHandler();
		f.setFileHandler(null,name);
		f.writeFileLine(header);
		f.writeFileLine(sb.toString());
		
		f.closeFile();
	}

}
