package edu.galileo.android.photofeed.domain;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

import edu.galileo.android.photofeed.entities.Photo;

 public class FirebaseAPI {
    private Firebase firebase;
    private ChildEventListener photosEventListener;

    public FirebaseAPI(Firebase firebase) {
        this.firebase = firebase;
    }

    public void checkForData(final FirebaseActionListenerCallback listener){
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getChildrenCount() > 0) {
                    listener.onSuccess();
                } else {
                    listener.onError(null);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                listener.onError(firebaseError);
            }
        });
    }

    public void subscribe(final FirebaseEventListenerCallback listener) {
        if (photosEventListener == null) {
            photosEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    listener.onChildAdded(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    listener.onChildRemoved(dataSnapshot);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    listener.onCancelled(firebaseError);
                }
            };
            firebase.addChildEventListener(photosEventListener);
        }
    }

    public void unsubscribe() {
        firebase.removeEventListener(photosEventListener);
    }

    public String create() {
        return firebase.push().getKey();
    }

    public void update(Photo photo) {
        Firebase reference = this.firebase.child(photo.getId());
        reference.setValue(photo);
    }

    public void remove(Photo photo, FirebaseActionListenerCallback listener) {
        firebase.child(photo.getId()).removeValue();
        listener.onSuccess();
    }

    public String getAuthEmail(){
        String email = null;
        if (firebase.getAuth() != null) {
            Map<String, Object> providerData = firebase.getAuth().getProviderData();
            email = providerData.get("email").toString();
        }
        return email;
    }

    public void signUp(String email, String password, final FirebaseActionListenerCallback listener){
        firebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                listener.onSuccess();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                listener.onError(firebaseError);
            }
        });
    }

    public void login(String email, String password, final FirebaseActionListenerCallback listener){
        firebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                listener.onSuccess();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                listener.onError(firebaseError);
            }
        });
    }

    public void checkForSession(FirebaseActionListenerCallback listener) {
        if (firebase.getAuth() != null) {
            listener.onSuccess();
        } else {
            listener.onError(null);
        }
    }

    public void logout() {
        firebase.unauth();
    }
}

