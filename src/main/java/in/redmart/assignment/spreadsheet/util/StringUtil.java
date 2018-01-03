package in.redmart.assignment.spreadsheet.util;
/**
 * String Utility Method
 * @author Raj
 *
 */
public class StringUtil {

	public static boolean isEmpty(String text) {
		boolean empty = true;
		if ((text != null) && (text.trim().length() > 0)) {
			empty = false;
		}
		return empty;
	}
	
	 /**
     * This method makes sure that all characters are numeric. Spaces are not
     * allowed.
     *
     * @param text
     * @return
     */
    public static boolean isNumeric(String text)
    {
        boolean isNumeric = true;
        int i = 0;
        if (!isEmpty(text))
        {
            while ((i<text.length()) && (isNumeric))
            {
                // Break out of the loop at the first character that is not numeric.
                isNumeric = Character.isDigit(text.charAt(i));
                i++;
            }
        }
        else {
            isNumeric = false;
        }
        return isNumeric;
    }
}
