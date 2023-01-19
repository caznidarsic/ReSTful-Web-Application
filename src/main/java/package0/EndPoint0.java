package package0;

//import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.FormParam;

@Path("getquote")
public class EndPoint0 {
	private final String preString = "<!DOCTYPE html>\n"
			+ "<html lang=\"en\">\n"
			+ "<head>\n"
			+ "<meta charset=\"UTF-8\">\n"
			+ "<title>Hike Quote Generator</title>\n"
			+ "\n"
			+ "<script src=\"ValidateHikers.js\">  </script>\n"
			+ "</head>\n"
			+ "\n"
			+ "\n"
			+ "<body>";
	private final String postString = "</body>\n"
			+ "</html>";
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getText(@FormParam("Hike") String hike, @FormParam("Date") String date, @FormParam("Duration") String duration, @FormParam("Hikers") String hikers) {
		
		if (hike != null && date != null && duration != null && hikers != null) {
    		if (!hike.matches("^[a-zA-Z\\s-]+$")) {
    			return preString + "The field \"Hike\" is formatted incorrectly. The Hike name can only contain letters, spaces and \"-\" characters." + postString;
    		}
    		else if (!date.matches("^\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d$")) {
				return preString + "The date is formatted incorrectly. Proper format is: \"YYYY-MM-DD\"" + postString;
    		}
			else if (Integer.parseInt(date.substring(0, 4)) > 2100) {
				return preString + "The year is out of valid range." + postString;
			}
			else if (Integer.parseInt(date.substring(5, 7)) > 12) {
				return preString + "The month is out of valid range." + postString;
			}
			else if (Integer.parseInt(date.substring(8, 10)) > 31) {
				return preString + "The day is out of valid range." + postString;
			}
    		else if (!duration.matches("^\\d{1,2}$")) {
    			return preString + "The field \"Duration\" is formatted incorrectly. Proper format is: \"##\" or \"#\"" + postString;
    		}
    		else if (!hikers.matches("^\\d+$")) {
    			return preString + "The field \"Hikers\" is formatted incorrectly. The field \"Hikers\" can only contain numbers." + postString;
    		}
    		else {
    			return preString + GetQuote.getQuote(hike, date, Integer.valueOf(duration), Integer.valueOf(hikers)) + postString;
    		}
		}
		else {
			return preString + "Some inputs were missing/formatted incorrectly." + postString;
		}

	}
	
}
