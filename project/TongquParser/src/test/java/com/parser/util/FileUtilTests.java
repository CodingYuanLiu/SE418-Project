package com.parser.util;


import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class FileUtilTests {
    @Test
    public void readFileTest() {
        System.out.println(Paths.get(new File("test.txt").getAbsolutePath()));
        String res = FileUtil.ReadFile("test.txt");
        assertEquals("This is a test.", res);
    }
}
