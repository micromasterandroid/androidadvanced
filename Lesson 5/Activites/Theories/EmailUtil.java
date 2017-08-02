public class EmailUtil {

    private Pattern formatPattern = Pattern.compile(".+@.+\\..+");
    private Pattern invalidCharsPattern = Pattern.compile("[\"'\\s()<>]+");

    EmailUtil() {
        // no-op
    }

    public boolean isValidEmailAddress(CharSequence emailAddress) {
        return !invalidCharsPattern.matcher(emailAddress).find() && formatPattern.matcher(emailAddress).matches();
    }

}