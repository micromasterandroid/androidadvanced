package edu.galileo.diedx;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

public class MessageService {

    @Inject Context context;
    @Inject @Named("hi") String hi;
    @Inject @Named("bye") String bye;
    @Inject @Named("question") String question;

    @Inject
    MessageService() {
        // no-op
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
