package edu.galileo.diedx;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

@Module
public class MainModule {

    private Context context;

    public MainModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Named("hi")
    @Provides
    String provideHi() {
        return "Hello!";
    }

    @Named("bye")
    @Provides
    String provideDye() {
        return "Good bye!!";
    }

    @Named("question")
    @Provides
    String provideQuestion() {
        return "What's your name???";
    }
}
