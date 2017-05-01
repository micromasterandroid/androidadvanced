package edu.galileo.diedx;

import dagger.Component;

@Component(
    modules = {
        MainModule.class
    }
)
public interface MainComponent {

    void inject(MainActivity mainActivity);
}
