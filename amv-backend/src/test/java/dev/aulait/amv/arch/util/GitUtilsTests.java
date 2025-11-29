package dev.aulait.amv.arch.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.aulait.amv.arch.test.FileUtils4Test;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class GitUtilsTests {

  @Test
  void testCloneAndGetRemoteUrl() {
    String repoUrl = "https://github.com/project-au-lait/project-au-lait.github.io.git";

    Path clonedDir = GitUtils.gitClone(Path.of("target"), repoUrl, true);

    FileUtils4Test.deleteOnExit(clonedDir);

    assertTrue(Files.exists(clonedDir));

    String remoteUrl = GitUtils.getRemoteUrl(clonedDir);
    assertEquals(repoUrl, remoteUrl);
  }

  @Test
  void testGitCloneWithToken() {

    String repoUrl = "https://github.com/ykuwahara/amv-test-repository.git";
    String token =
        String.join(
            "_",
            "github",
            "pat",
            "11ADEYTGY0WRN4jA7GRNn8_YuE4ElbBSxAefHSHPomlpm9sRfsmBcUDWNPpS1FL0mvIATDZHIOuW1IfBjJ");
    Path repoDir = GitUtils.gitClone(Path.of("target"), repoUrl, token, "ID_TEST");

    FileUtils4Test.deleteOnExit(repoDir);

    assertTrue(Files.exists(repoDir));
  }
}
