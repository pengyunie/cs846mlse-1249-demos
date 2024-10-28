package ca.uwaterloo.cs846;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.objectweb.asm.ClassReader;

public class StaticAnalyzer {

    CallGraph cg = new CallGraph();

    /**
     * Analyze the classes in the given paths and create a call graph.
     */
    public void analyze(String[] paths) {
        // visit all classes in the given paths
        for (String path : paths) {
            Path p = Paths.get(path);
            if (Files.isRegularFile(p) && p.toString().endsWith(".class")) {
                analyzeClass(p);
            } else if (Files.isDirectory(p)) {
                analyzeDirectory(p);
            } else {
                // skip
            }
        }

        // print the call graph
        System.out.println(cg);
    }

    /**
     * Visit a single class file
     */
    private void analyzeClass(Path p) {
        try (InputStream is = Files.newInputStream(p)) {
            // reading the class file using ClassReader -> CallGraphClassVisitor
            ClassReader cr = new ClassReader(is);
            cr.accept(new CallGraphClassVisitor(cg), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Visit all classes in the given directory
     */
    private void analyzeDirectory(Path p) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(p)) {
            for (Path entry : stream) {
                analyzeClass(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StaticAnalyzer analyzer = new StaticAnalyzer();
        analyzer.analyze(args);
    }
}
