import helper.TwitterHelper;
import twitter4j.*;

import java.util.List;

public class TwitterManage {

    public static void main(String args[]){
        TwitterHelper twitterHelper = new TwitterHelper();

        Query query = new Query();
        query.setLang("ko");
        query.setQuery("미세먼지");
        query.setSince("2018-03-28");

        try {
            List<Status> result = twitterHelper.getTweetList(query,150);
            System.out.println("전체개수===>"+result.size());
            for(Status status : result){
                System.out.println("[twitter] :" + status.getText());
                System.out.println("");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
