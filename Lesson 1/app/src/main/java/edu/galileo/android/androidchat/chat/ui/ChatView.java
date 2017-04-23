package edu.galileo.android.androidchat.chat.ui;

import edu.galileo.android.androidchat.chat.entities.ChatMessage;


public interface ChatView {
    void sendMessage();
    void onMessageReceived(ChatMessage msg);
}
