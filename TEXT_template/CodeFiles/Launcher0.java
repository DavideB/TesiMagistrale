package it.polimi.deepse.dagsymb.launchers;/*
 * This file was automatically generated by EvoSuite
 * Wed May 16 13:17:45 GMT 2018            */
import it.polimi.deepse.dagsymb.examples.PromoCalls;
import it.polimi.deepse.dagsymb.examples.UserCallDB;
public class Launcher0 {
  /*Test case number: 0
   * 1 covered goal:
   * Goal 1. com.xspark.varyingdag.examples.calls.PromoCalls.run_driver(IJJI)V: path condition EvoSuiteWrapper_0_0 (id = 0)
   */
    public static void main(String[] args) {
      int threshold = 2772;
      long minLocalLongCalls = 2772;
      long minAbroadLongCalls = 1397;
      int pastMonths = 0;
      int last24HLocalCallsLength = 0;
      int last24HLocalCallsSize = 0;
      int last24HAbroadCallsLength = 3361;
      int last24HAbroadCallsSize = 2794; // 1397 * 2
      int MonthCallsLength = 2990;
      int MonthCallsSize = 3000;
      int num_partitions = 500;
      PromoCalls promoCalls0 = new PromoCalls();
      boolean genData = false;
      String appName = "";
      if (args[12] != null && args[12].startsWith("-g")) genData = true;
      if (args[13] != null && !args[13].startsWith("-")) appName = args[12];
      promoCalls0.run(threshold, minLocalLongCalls, minAbroadLongCalls, pastMonths, 
    		  		  last24HLocalCallsLength, last24HLocalCallsSize, 
    		  		  last24HAbroadCallsLength, last24HAbroadCallsSize, 
    		  		  MonthCallsLength, MonthCallsSize, num_partitions, genData, appName);
  }
}