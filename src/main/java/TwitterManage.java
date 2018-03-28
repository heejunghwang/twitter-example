import helper.CSVHelper;
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

            //조회한 객체 출력
            twitterHelper.printOriginTweet(result);

            //CSV로 Export
            CSVHelper csvHelper = new CSVHelper();
            boolean finishExport = csvHelper.write(result);
            if(finishExport == true){
                System.out.println("========== SUCCESS TO EXPORT TWITTER =============");
            }

        }catch (Exception e){
            e.printStackTrace();
        }



    }


}
