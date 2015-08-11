package com.zhaijiong.stock.indicators;

import com.zhaijiong.stock.Context;
import com.zhaijiong.stock.Stock;
import com.zhaijiong.stock.StockSlice;
import com.zhaijiong.stock.dao.StockDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class IndicatorsTest {
    Indicators indicators;
    Context context;
    StockDB stockDB;


    @Before
    public void setUp() throws Exception {
        indicators = new Indicators();
        context =new Context();
        stockDB = new StockDB(context);
    }

    @After
    public void tearDown() throws Exception {
        context.close();
    }

    @Test
    public void testSma() throws Exception {
        String startDate = "20150201";
        String stopDate = "20150811";
        String symbol = "601886";

//        StockSlice stockSlice = stockDB.getStockSliceDaily(symbol, startDate, stopDate);
        StockSlice stockSlice = stockDB.getStockSlice5Min(symbol, startDate, stopDate);
        double[] closes = stockSlice.getValues("close");
        indicators.sma(closes,5);

        double[] ma5 = indicators.sma(closes,5 );
        double[] ma10 = indicators.sma(closes,10);
        double[] ma20 = indicators.sma(closes,20);
        double[] ma30 = indicators.sma(closes,30);
        double[] ma60 = indicators.sma(closes,60);
        double[] ma120 = indicators.sma(closes,120);

        System.out.println("ma5:"+ma5[closes.length-1]);
        System.out.println("ma10:"+ma10[closes.length-1]);
        System.out.println("ma20:"+ma20[closes.length-1]);
        System.out.println("ma30:"+ma30[closes.length-1]);
        System.out.println("ma60:"+ma60[closes.length-1]);
        System.out.println("ma120:"+ma120[closes.length-1]);
    }

    @Test
    public void testVolumeMA() throws IOException {
        String startDate = "20150201";
        String stopDate = "20150808";
        String symbol = "601886";

        StockSlice stockSlice = stockDB.getStockSliceDaily(symbol, startDate, stopDate);
        double[] volumes = stockSlice.getValues("volume");
        indicators.sma(volumes,5);

        double[] ma5 = indicators.sma(volumes,5 );
        double[] ma10 = indicators.sma(volumes,10);
        double[] ma20 = indicators.sma(volumes,20);
        double[] ma30 = indicators.sma(volumes,30);
        double[] ma60 = indicators.sma(volumes,60);
        double[] ma120 = indicators.sma(volumes,120);

        System.out.println("ma5:"+ma5[volumes.length-1]);
        System.out.println("ma10:"+ma10[volumes.length-1]);
        System.out.println("ma20:"+ma20[volumes.length-1]);
        System.out.println("ma30:"+ma30[volumes.length-1]);
        System.out.println("ma60:"+ma60[volumes.length-1]);
        System.out.println("ma120:"+ma120[volumes.length-1]);
    }

    @Test
    public void testMacd() throws Exception {
        String startDate = "20150201";
        String stopDate = "20150808";
        String symbol = "601886";
        StockSlice stockSlice = stockDB.getStockSliceDaily(symbol,startDate,stopDate);
        double[] closes = stockSlice.getValues("close");
        System.out.println("closes:"+closes.length);
        double[][] macd = indicators.macd(closes);
        System.out.println("macd.length:"+macd.length);
        System.out.println("macd[0].length:"+macd[0].length);

        double dif = macd[0][closes.length - 1];
        double dea = macd[1][closes.length - 1];
        double macdRtn = (dif - dea) * 2;
        System.out.println("DIF=" + dif);
        System.out.println("DEA=" + dea);
        System.out.println("MACD=" + macdRtn);
    }

    @Test
    public void testBbands() throws Exception {

    }
}