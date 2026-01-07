package dev.aulait.amv.domain.extractor.java;

import static org.junit.jupiter.api.Assertions.*;

import dev.aulait.amv.arch.test.ResourceUtils;
import dev.aulait.amv.domain.extractor.fdo.FlowStatementFdo;
import dev.aulait.amv.domain.extractor.fdo.MethodCallFdo;
import dev.aulait.amv.domain.extractor.fdo.MethodFdo;
import dev.aulait.amv.domain.extractor.fdo.SourceFdo;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class FlowStatementTests {

  MetadataExtractor extractor = ExtractionServiceFactory.buildMetadataExtractor4Test();

  @Test
  void test() {
    Path sourceFile = ResourceUtils.res2path(this, "FlowStatements.java");

    SourceFdo extractedSource = extractor.extract(sourceFile).get();

    MethodFdo method =
        extractedSource.getTypes().get(0).getMethods().stream()
            .filter(m -> "flowStatement".equals(m.getName()))
            .findFirst()
            .orElseThrow();

    List<FlowStatementFdo> flowStatements =
        method.getMethodCalls().stream()
            .map(MethodCallFdo::getFlowStatement)
            .filter(Objects::nonNull)
            .toList();

    FlowStatementFdo ifFs =
        flowStatements.stream()
            .filter(fs -> FlowStatementKind.IF.code().equals(fs.getKind()))
            .findFirst()
            .orElseThrow();

    assertEquals(FlowStatementKind.IF.code(), ifFs.getKind());
    assertEquals("flg", ifFs.getContent());
    assertEquals(7, ifFs.getLineNo());

    FlowStatementFdo elseIfFs =
        flowStatements.stream()
            .filter(fs -> FlowStatementKind.ELSE_IF.code().equals(fs.getKind()))
            .findFirst()
            .orElseThrow();

    assertEquals(FlowStatementKind.ELSE_IF.code(), elseIfFs.getKind());
    assertEquals("flg", elseIfFs.getContent());
    assertEquals(9, elseIfFs.getLineNo());

    FlowStatementFdo elseFs =
        flowStatements.stream()
            .filter(fs -> FlowStatementKind.ELSE.code().equals(fs.getKind()))
            .findFirst()
            .orElseThrow();

    assertEquals(FlowStatementKind.ELSE.code(), elseFs.getKind());
    assertEquals("else", elseFs.getContent());
    assertEquals(11, elseFs.getLineNo());
  }

  @Test
  void for_test() {
    Path sourceFile = ResourceUtils.res2path(this, "FlowStatements.java");

    SourceFdo source = extractor.extract(sourceFile).orElseThrow();

    MethodFdo method =
        source.getTypes().get(0).getMethods().stream()
            .filter(m -> "forStatement".equals(m.getName()))
            .findFirst()
            .orElseThrow();

    FlowStatementFdo flowStatement = method.getMethodCalls().get(0).getFlowStatement();

    assertEquals(FlowStatementKind.FOR.code(), flowStatement.getKind());
    assertEquals("cnt < 2", flowStatement.getContent());
    assertEquals(12, flowStatement.getLineNo());
  }

  @Test
  void tryCatch_test() {
    Path sourceFile = ResourceUtils.res2path(this, "FlowStatements.java");

    SourceFdo source = extractor.extract(sourceFile).orElseThrow();

    MethodFdo method =
        source.getTypes().get(0).getMethods().stream()
            .filter(m -> "trycatchStatement".equals(m.getName()))
            .findFirst()
            .orElseThrow();

    List<FlowStatementFdo> flowStatements =
        method.getMethodCalls().stream()
            .map(MethodCallFdo::getFlowStatement)
            .filter(Objects::nonNull)
            .toList();

    FlowStatementFdo tryFs =
        flowStatements.stream()
            .filter(fs -> FlowStatementKind.TRY.code().equals(fs.getKind()))
            .findFirst()
            .orElseThrow();

    assertEquals("try", tryFs.getContent());
    assertEquals(18, tryFs.getLineNo());

    FlowStatementFdo catchFs =
        flowStatements.stream()
            .filter(fs -> FlowStatementKind.CATCH.code().equals(fs.getKind()))
            .findFirst()
            .orElseThrow();

    assertEquals("catch (Exception e)", catchFs.getContent());
    assertEquals(20, catchFs.getLineNo());
  }

  @Test
  void return_test() {
    Path sourceFile = ResourceUtils.res2path(this, "FlowStatements.java");

    SourceFdo source = extractor.extract(sourceFile).orElseThrow();

    MethodFdo method =
        source.getTypes().get(0).getMethods().stream()
            .filter(m -> "returnStatement".equals(m.getName()))
            .findFirst()
            .orElseThrow();

    FlowStatementFdo flowStatement = method.getMethodCalls().get(0).getFlowStatement();

    assertEquals(FlowStatementKind.RETURN.code(), flowStatement.getKind());
    assertEquals("innerRtnMethod()", flowStatement.getContent());
    assertEquals(31, flowStatement.getLineNo());
  }
}
