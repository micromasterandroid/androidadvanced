package edu.galileo.android.androidchat.contactlist.ui;

import edu.galileo.android.androidchat.contactlist.entities.User;


public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
