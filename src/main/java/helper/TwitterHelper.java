package helper;

import config.TwitterConfiguration;
import twitter4j.*;
import model.TweetPost;

import java.util.ArrayList;
import java.util.List;

public class TwitterHelper {

    private TwitterConfiguration twitterConfiguration;
    private Twitter twitter;

    public TwitterHelper() {
        twitterConfiguration = new TwitterConfiguration();
        twitter = twitterConfiguration.getInstance();
    }

    /**
     * 트위터 리스트를 조회한다.
     * @param query
     * @param totalCount
     * @return
     * @throws Exception
     */
    public List<Status> getTweetList(Query query, int totalCount) throws Exception{
        List<Status> tweetList = null;
        if(totalCount < 100){
            query.setCount(totalCount);
            tweetList = this.getTweetBlock(query);
        }else{
            tweetList = this.getTweetBulk(query, totalCount);
        }
        return tweetList;
    }


    /**
     * 트위터를 조회한다.
     * @param query
     * @return
     */
    private List<Status> getTweetBlock(Query query){
        if(query.getMaxId() != 0){
            query.setMaxId(query.getMaxId());
        }
        List<Status> result = null;
        QueryResult queryResult = null;
        try{
            queryResult = twitter.search(query);
        }catch (TwitterException e){
            e.printStackTrace();
        }

        if(queryResult != null){
            result = new ArrayList<>();
            for (Status status : queryResult.getTweets()) {
                result.add(status);
            }
        }
        return result;
    }

    /**
     * 100개 이상의 bulk 트위터를 조회한다.
     * @param query
     * @param totalCount
     * @return
     * @throws Exception
     */
    private List<Status> getTweetBulk(Query query, int totalCount) throws Exception{
        List<Status> result = null;
        query.setCount(100);    //트위터 API 에서 한번에 가져올 수 있는 양이 100개
        boolean finished = false;
        int processCount = 0;
        while (!finished) {
            if(result == null){
                result = new ArrayList<>();
            }
            List<Status> statuses = this.getTweetBlock(query);
            long lowestStatusId = Long.MAX_VALUE;
            for (Status status : statuses) {
                lowestStatusId = Math.min(status.getId(), lowestStatusId);
                result.add(status);
            }
            query.setMaxId(lowestStatusId - 1);
            processCount = processCount+statuses.size();

            if((totalCount-processCount) < 100){
                query.setCount(totalCount-processCount);
            }

            if(statuses == null || (totalCount-processCount) <= 0){
                finished = true;
            }
        }
        return result;
    }


    /**
     * TweetPost.java 객체로 변환한다.
     * @param tweetList
     * @return
     */
    public List<TweetPost> convertTweetPost(List<Status> tweetList){
        List<TweetPost> result = null;

        if(tweetList != null){
            result = new ArrayList<>();
            for(Status status : tweetList){
                TweetPost tw = new TweetPost();
                tw.setId(status.getId());

                String text = status.getText().replaceAll("(\r\n|\n)", " ")
                                .replaceAll("\"","\'")
                                .replaceAll(",","");

                tw.setText(text);
                tw.setLang(status.getLang());
                tw.setCreatedAt(status.getCreatedAt());
                result.add(tw);
            }
        }
        return result;

    }

    /**
     * 트위터 원본 출력
     * @param tweetList
     */
    public void printOriginTweet(List<Status> tweetList){
        if(tweetList != null){
            System.out.println("전체개수 ===> " + tweetList.size());
            for(Status status : tweetList) {
                System.out.println("[twitter] :" + status.getText());
            }

        }
    }

}
