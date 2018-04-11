package it.milano.project;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import it.milano.project.util.FileHandler;
import it.milano.project.util.FileWriter;
import it.milano.project.util.Mail;


public class Execution {
	public static void main(String[] args) {

		FileHandler f = new FileHandler();
		StringBuilder sb = new StringBuilder();
		String emailRecipient = "serena.nicolazzo@sisal.it";


		//costruisco il filename del log relativo a ieri
		LocalDate yesterday = LocalDate.now().minusDays(1);
		String month= ((yesterday.getMonthValue()<10)?"0":"")+yesterday.getMonthValue();
		String day= ((yesterday.getDayOfMonth()<10)?"0":"")+yesterday.getDayOfMonth();

		String filename;

		filename = new File("").getAbsolutePath()+"/out/report_error_api_2018"+month+day+"_0200.csv";

		f.setFileHandler(filename, null);

		Double contErrorTot = 0.0;
		//conto gli errori (codice diverso da 200 e 201) del file
		String s=f.readFile();
		while((s=f.readFile())!=null) {
			String[] spl = s.split(";");
			if(!spl[2].equals("200")&&!spl[2].equals("201")) {
				contErrorTot+=Double.parseDouble(spl[3]);

			}
		}
		sb.append(yesterday+";"+contErrorTot.intValue()+";\n");

		f.closeFile();
		//creo un nuovo file e scrivo il count degli errori (il nome per me è File_march_2018)
		filename= "File_"+LocalDate.now().minusMonths(1).getMonth().toString().toLowerCase()+"_2018.csv";
		FileWriter.writeCSV(sb, filename, "date;totErrori");
		//invio mail con file (il mittente e il cc sono impostati dentro sendMail
		Mail.sendMail(filename, "Tot Errori", sb.toString(), emailRecipient);

		System.out.println("Done");

	}
}
