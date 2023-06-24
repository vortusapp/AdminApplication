package nz.vortus;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private static Tomcat tomcat;

    @BeforeAll
    public static void setUp() throws Exception {
        String webappDirLocation = "src/main/webapp/";
        tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(3000);

        StandardContext context = (StandardContext) tomcat.addWebapp("",
                new File(webappDirLocation).getAbsolutePath());

        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        context.setResources(resources);

        tomcat.start();
    }

    @AfterAll
    public static void tearDown() throws LifecycleException {
        tomcat.stop();
        tomcat.destroy();
    }


    @Test
    public void testTomcatPort() {
        assertEquals(3000, tomcat.getConnector().getPort(), "Tomcat port should be set");
    }



    @Test
    public void testStandardContext() {
        assertNotNull(tomcat.getHost().findChild(""), "StandardContext should be added to Tomcat");
    }

    @Test
    public void testWebResourceRoot() {
        assertNotNull(getStandardContext().getResources(), "WebResourceRoot should be set");
    }

    @Test
    public void testDirResourceSet() {
        assertNotNull(getStandardContext().getResources().getPreResources()[0], "DirResourceSet should be added to WebResourceRoot");
    }


    @Test
    public void testTomcatStop() throws LifecycleException {
        tomcat.stop();
        assertFalse(tomcat.getServer().getState().isAvailable(), "Tomcat server should be stopped");
    }


    private StandardContext getStandardContext() {
        return (StandardContext) tomcat.getHost().findChild("");
    }
}
