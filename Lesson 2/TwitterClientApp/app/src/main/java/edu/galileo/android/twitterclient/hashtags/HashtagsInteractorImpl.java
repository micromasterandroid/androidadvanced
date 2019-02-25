package edu.galileo.android.twitterclient.hashtags;

/**
 * Created by ykro.
 */
public class HashtagsInteractorImpl implements HashtagsInteractor {
    private HashtagsRepository repository;

    public HashtagsInteractorImpl(HashtagsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getHashtags();
    }
}
