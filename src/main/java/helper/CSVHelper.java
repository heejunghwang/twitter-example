package helper;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import twitter4j.Status;
import model.TweetPost;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CSVHelper {
    /**
     * CSV 파일로 쓰기
     * @param tweetPostList
     * @return
     * @throws Exception
     */
    public boolean write(List<Status> tweetPostList) throws Exception{
        TwitterHelper twitterHelper = new TwitterHelper();

        //객체 변환
        List<TweetPost> postList = twitterHelper.convertTweetPost(tweetPostList);

        //저장
        return this.save(postList);
    }

    /**
     * 트위터 객체 CSV 파일로 저장
     * @param tweetPostList
     * @return
     * @throws Exception
     */
    private boolean save(List<TweetPost> tweetPostList) throws Exception{
        String fileName = "src/main/resources/tweet.csv";
        Path csvPath = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(csvPath, StandardCharsets.UTF_8)) {

            StatefulBeanToCsv<TweetPost> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(tweetPostList);

        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException |
                IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
