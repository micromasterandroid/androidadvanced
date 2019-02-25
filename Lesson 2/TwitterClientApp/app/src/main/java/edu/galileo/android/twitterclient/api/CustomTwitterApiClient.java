package edu.galileo.android.twitterclient.api;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by ykro.
 */
public class CustomTwitterApiClient extends TwitterApiClient {
    public CustomTwitterApiClient(TwitterSession session) {
        super(session);
    }

    public TimelineService getTimelineService() {
        return getService(TimelineService.class);
    }
}
