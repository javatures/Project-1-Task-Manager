package TaskManager;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class App {

    public static void main(String[] args) throws LifecycleException {
        final int PORT = 8080;
        Tomcat server = new Tomcat();
        server.getConnector();
        server.setPort(PORT);
        Context context = server.addWebapp("", new File("src/main/webapp").getAbsolutePath());
        WebResourceRoot resource = new StandardRoot(context);
        resource.addPreResources(new DirResourceSet(resource, "/WEB-INF/classes", new File("build/classes/java/main").getAbsolutePath(), "/"));
        context.setResources(resource);
        server.start();
        server.getServer().await();
    }
}
