package edu.galileo.android.androidchat.contactlist;


public interface ContactListInteractor {
    void subscribeForContactEvents();
    void unSubscribeForContactEvents();
    void destroyContactListListener();
    void removeContact(String email);
}
