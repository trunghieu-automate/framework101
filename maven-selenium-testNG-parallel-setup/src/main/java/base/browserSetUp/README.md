## DriverManger
This class is for driver object management, designed by ThreadLocal base setup.

It will be helpful for running parallel tests later.

## BaseDriver
Should contain common method for setup, shutdown browser.

Can be extended to run specific browser for each testcase then.

## DriverOption
This class is for setting chromium browser type globally right after the browser is initialized

Have a method to read properties file, that you are managing the browser arguments.

## ChromeDriverRun and EdgeDriverRun
Those 2 classes are extending BaseDriver to execute specific browsers.

We can implement @After @Before in these classes.

And TestClass will be extend one of these to run on specific browser, as the testcase mentioned.