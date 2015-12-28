// Copyright (c) 2014 Mark A. Gallagher Jr (mag0038@auburn.edu).

import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class ExtractorTest {

    @Test
    public void testGetLinesBrute1() throws IOException, InterruptedException {
        Extractor e = new Extractor("input6.txt");
        e.getLinesBrute();
        e.drawPoints();
        e.drawLines();
        Thread.sleep(5_000);
        StdDraw.clear();
    }

    @Test
    public void testGetLinesBrute2() throws IOException, InterruptedException {
        Extractor e = new Extractor("input9.txt");
        e.getLinesBrute();
        e.drawPoints();
        e.drawLines();
        Thread.sleep(5_000);
        StdDraw.clear();
    }

    @Test
    public void testGetLinesBrute3() throws IOException, InterruptedException {
        Extractor e = new Extractor("input40.txt");
        e.getLinesBrute();
        e.drawPoints();
        e.drawLines();
        Thread.sleep(5_000);
        StdDraw.clear();
    }

    @Test
    public void testGetLinesBrute4() throws IOException, InterruptedException {
        Extractor e4 = new Extractor("input56.txt");
        e4.getLinesBrute();
        e4.drawPoints();
        e4.drawLines();
        Thread.sleep(5_000);
        StdDraw.clear();
    }

    @Test
    public void testGetLinesBrute5() throws IOException, InterruptedException {
        Extractor e = new Extractor("input200.txt");
        e.getLinesBrute();
        e.drawPoints();
        e.drawLines();
        Thread.sleep(5_000);
        StdDraw.clear();
    }

    @Test
    public void testGetLinesBrute6() throws IOException, InterruptedException {
        Extractor e = new Extractor("inarow.txt");
        e.getLinesBrute();
        e.drawPoints();
        e.drawLines();
        Thread.sleep(5_000);
        StdDraw.clear();
    }


    @Test
    public void testGetLinesFast1() throws IOException, InterruptedException {
        Extractor e = new Extractor("input6.txt");
        e.getLinesFast();
        e.drawPoints();
        e.drawLines();
        Thread.sleep(5_000);
        StdDraw.clear();
    }

    @Test
    public void testGetLinesFast2() throws IOException, InterruptedException {
        Extractor e = new Extractor("input40.txt");
        e.getLinesFast();
        e.drawPoints();
        e.drawLines();
        Thread.sleep(5_000);
        StdDraw.clear();
    }

    @Test
    public void testGetLinesFast3() throws IOException, InterruptedException {
        Extractor e = new Extractor("input1000.txt");
        e.getLinesFast();
        e.drawPoints();
        e.drawLines();
        Thread.sleep(5_000);
        StdDraw.clear();
    }

    @Test
    public void testGetLinesFast4() throws IOException, InterruptedException {
        Extractor e = new Extractor("rs1423.txt");
        e.getLinesFast();
        e.drawPoints();
        e.drawLines();
        Thread.sleep(5_000);
        StdDraw.clear();
    }

    @Test
    public void testGetLinesFast5() throws IOException, InterruptedException {
        Extractor e = new Extractor("mystery10089.txt");
        e.getLinesFast();
        e.drawPoints();
        e.drawLines();
        Thread.sleep(5_000);
        StdDraw.clear();
    }

}