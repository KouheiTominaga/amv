package dev.aulait.amv.domain.extractor.java.FlowStatementTestResources;

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

  public void forStatement() {
    for (int cnt = 0; cnt < 2; cnt++) {
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

  private String innerRtnMethod() {
    return "flow statement";
  }

  private void innerMethod() {
    System.out.println("flow statement");
  }
}
