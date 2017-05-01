package edu.galileo.diedx;

import android.content.Context;
import android.widget.Toast;

public class MessageService {

    private Context context;
    private String hi;
    private String bye;
    private String question;

    public MessageService(Context context, String hi, String bye, String question) {
        this.context = context;
        this.hi = hi;
        this.bye = bye;
        this.question = question;
    }

    public void sayHi() {
        Toast.makeText(context, hi, Toast.LENGTH_SHORT).show();
    }

    public void sayBye() {
        Toast.makeText(context, bye, Toast.LENGTH_SHORT).show();
    }

    public void askSomething() {
        Toast.makeText(context, question, Toast.LENGTH_SHORT).show();
    }
}
