package ca.uwaterloo.cs846;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SAClassVisitor extends ClassVisitor {

    private CallGraph cg;
    private String currentClassName;

    public SAClassVisitor(CallGraph cg) {
        super(Opcodes.ASM9);
        this.cg = cg;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.currentClassName = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
            String[] exceptions) {
        // create a customized method visitor to change the default behavior
        return new SAMethodVisitor(cg, currentClassName + "." + name + ":" + descriptor);
    }
}
