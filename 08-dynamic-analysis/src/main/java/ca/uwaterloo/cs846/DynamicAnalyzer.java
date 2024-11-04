package ca.uwaterloo.cs846;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Optional;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class DynamicAnalyzer {
    private static CallGraph cg = new CallGraph();
    private static Optional<String> curCaller = Optional.empty();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println(cg);
        }));
    }

    public static void logCallee(String callee) {
        // System.out.println("Callee: " + callee);
        if (curCaller.isPresent()) {
            cg.addCall(curCaller.get(), callee);
            curCaller = Optional.empty();
        }
    }

    public static void logCaller(String caller) {
        // System.out.println("Caller: " + caller);
        curCaller = Optional.of(caller);
    }

    public static class CFT implements ClassFileTransformer {
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            if (className.startsWith("ca/uwaterloo/cs846/exp")) {
                // limit our analyzer to certain packages in a whitelist (alternatively, you can set a blacklist to avoid analyzing certain packages, e.g., java/)
                ClassReader cr = new ClassReader(classfileBuffer);
                ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                DAClassVisitor cv = new DAClassVisitor(cw);
                cr.accept(cv, 0);
                return cw.toByteArray();
            } else {
                // return null means we don't transform this class
                return null;
            }
        }
    }

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new CFT());
    }
}
