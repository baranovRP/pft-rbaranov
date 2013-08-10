package com.baranov.pft.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.baranov.pft.fw.ApplicationManager;

public class TestBase {
    public static ApplicationManager app;

    @BeforeTest
    public void setUp() throws Exception {
	app = new ApplicationManager();
    }

    @AfterTest
    public void tearDown() throws Exception {
	app.stop();
    }
}
