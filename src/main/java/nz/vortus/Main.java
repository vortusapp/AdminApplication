package nz.vortus;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException, ServletException {
        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();
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
        tomcat.getServer().await();
    }


}

