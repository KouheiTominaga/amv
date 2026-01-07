package dev.aulait.amv.domain.extractor.java.FlowStatementTestResources;

import java.util.ArrayList;
import java.util.List;

public class FlowStatements {

  public void flowStatement() {
    boolean flg = true;
    if (flg) {
      innerMethod();
    } else if (flg) {
      innerMethod();
    } else {
      innerMethod();
    }
  }

  public void trycatchStatement() {
    try {
      System.out.println("try");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public String returnStatement() {
    return innerRtnMethod();
  }

  public void forEachStatement() {
    List<Integer> list = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      list.add(i);
    }

    for (Integer num : list) {
      System.out.println(num);
    }
  }

  private String innerRtnMethod() {
    return "flow statement";
  }

  private void innerMethod() {
    System.out.println("flow statement");
  }
}
