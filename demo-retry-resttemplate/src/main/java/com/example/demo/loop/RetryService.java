package com.example.demo.loop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;

@Service
public class RetryService {

    @Autowired
    public RestTemplate restTemplate;

    @Value("${retry.times.values}")
    public String retryString;//1,2,3

    @Value("${retry.times.fails}")
    public int consecutiveFailsCnt;//10

    @Value("${retry.times.multiplier}")
    public long multiplier;

    @Value("${retry.rest.url}")
    public String restUrl;//"http://localhost:8080/parties/"

        public void doretry(){

            int[] retries = getWaitTimes(retryString);//"1,2,3,5"
            int consecutiveFails = 0;

            for (int order = 0; order <10; order ++) {
                for (int retry : retries) {
                    try{
                        String response = restTemplate
                                .getForObject(restUrl+order,String.class);

                        System.out.println(response);
                        consecutiveFails = 0;
                        break;

                    }catch (Exception exp) {
                        System.err.println(new Date() +" : "+exp.getMessage());
                        consecutiveFails++;

                        if (consecutiveFails >= consecutiveFailsCnt) {
                            break;
                        }
                        try{
                            Thread.sleep(multiplier*retry);
                        }catch (InterruptedException expn){}
                    }

                }
                if (consecutiveFails >= consecutiveFailsCnt) {
                    break;
                }
            }
        }

    public int[] getWaitTimes(String values) {//[1,2,3,5,8]

        String[] valuesArray  = values.split(",");
        int[] waitTimes = new int[valuesArray.length];//[0,0,0,0,]

        for (int i = 0; i<waitTimes.length; i ++) {
            waitTimes[i] = Integer.parseInt(valuesArray[i]);
        }
        return waitTimes;
    }

}
