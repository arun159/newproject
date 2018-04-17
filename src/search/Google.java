package search;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Scanner;

//import org.apache.solr.client.solrj.response.QueryResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Google 
{
		public static final String GOOGLE_SEARCH_URL = "https://fulltime.thefa.com/ff";
		public static void main(String[] args) throws IOException
		{
			System.out.println("searching.........");
			 File file = new File("D:\\file\\leagueid.txt");
			  @SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(file));
			  String splitId;
			  	while ((splitId = br.readLine()) != null)
			  	{
			  		String scanner=splitId;
			  		String[] splits = scanner.split(",");
			   		for(String inputLeagueid: splits)
			   		{
				String searchURL = GOOGLE_SEARCH_URL + "/LeagueDetails?leagueid="+inputLeagueid;
				Document doc = Jsoup.connect(searchURL).userAgent("Chrome/65.0.3325.181").get();
				Elements results = doc.select("h1");
				String linkHref = results.attr("class");
						fileWritter(linkHref, inputLeagueid);
			   		}
			  	}
			  	System.out.println("completed");
		}
		private static void fileWritter(String title, String leagueid) throws IOException {
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh");
			String time = dateFormat.format(now);
			File dir = new File("D:\\file\\outputapp_"+time+".txt");
			dir.createNewFile();
			BufferedWriter bw = null;
			FileWriter fw = null;
			try {
			fw = new FileWriter(dir,true);
			bw = new BufferedWriter(fw);
			if(title.equalsIgnoreCase("error-status-definition"))
			{
				bw.write("\r\nNeed to verify on this page breaking "+leagueid);
			}
			else
			{
				bw.write("\r\nverified found "+leagueid);
			}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if (bw != null)
						bw.close();

					if (fw != null)
						fw.close();

				} catch (IOException ex) {
					ex.printStackTrace();
				}
				}
		}
  }
