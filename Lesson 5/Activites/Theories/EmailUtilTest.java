@RunWith(Theories.class)
public class EmailUtilTest extends XoomTest {

    @DataPoints("validEmails")
    public static String[] VALID_EMAILS = {
        "edx@yahoo.com", 
        "edx-100@yahoo.com", 
        "edx.100@yahoo.com",
        "edx111@edx.com", 
        "edx-100@edx.net", 
        "edx.100@edx.com.au",
        "edx@1.com", 
        "edx@gmail.com.com",
        "edx+100@gmail.com", 
        "edx-100@yahoo-test.com"
    };

    @DataPoints("invalidEmails")
    public static String[] INVALID_EMAILS = {
        "edx", 
        "edx@.com.my", 
        "edx123@gmail.a", 
        "edx123@.com", 
        ".edx@edx.com", 
        "edx()*@gmail.com", 
        "edx@%*.com", 
        "edx..2002@gmail.com", 
        "edx.@gmail.com", 
        "edx@edx@gmail.com", 
        "edx@gmail.com.1a"
    };

    @Theory
    public void isValidEmail_true(@FromDataPoints("validEmails") String validEmails) {
        assertThat(emailUtil.isValidEmailAddress(validEmails), is(true));
    }

    @Theory
    public void isValidEmail_false(@FromDataPoints("invalidEmails") String invalidEmails) {
        assertThat(emailUtil.isValidEmailAddress(invalidEmails), is(false));
    }

}