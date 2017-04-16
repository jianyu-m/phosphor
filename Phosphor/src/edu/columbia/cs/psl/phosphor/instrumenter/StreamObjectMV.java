package edu.columbia.cs.psl.phosphor.instrumenter;

import edu.columbia.cs.psl.phosphor.Configuration;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * Created by jianyu on 4/7/17.
 */
public class StreamObjectMV extends MethodVisitor {
    StreamObjectMV(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itfc) {
        // ignore implict tracking for now
        if (!Configuration.IMPLICIT_TRACKING && owner.equals("sun/misc/Unsafe") && name.equals("putObjectWrapper")) {
//            System.out.println(desc);
            desc = "(Lsun/misc/Unsafe;Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Class;)V";
            super.visitMethodInsn(Opcodes.INVOKESTATIC,
                    Type.getInternalName(StreamObjectHelper.class),
                    name, desc, itfc);
            System.out.println("instrumenting Unsafe.putObjectWrapper");
            return;
        } else if (!Configuration.IMPLICIT_TRACKING && owner.equals("sun/misc/Unsafe") && name.equals("putObjectWrapper$$PHOSPHORTAGGED")) {
            if (Configuration.MULTI_TAINTING) {
                desc = "(Lsun/misc/Unsafe;Ljava/lang/Object;Ledu/columbia/cs/psl/phosphor/runtime/Taint;JLjava/lang/Object;Ljava/lang/Class;)V";
            } else {
                desc = "(Lsun/misc/Unsafe;Ljava/lang/Object;IJLjava/lang/Object;Ljava/lang/Class;)V";
            }
            System.out.println("instrumenting Unsafe.putObjectWrapper$$PHOSPHORTAGGED");
            super.visitMethodInsn(Opcodes.INVOKESTATIC,
                    Type.getInternalName(StreamObjectHelper.class),
                    name, desc, itfc);
            return;
        }
        super.visitMethodInsn(opcode, owner, name, desc, itfc);
    }
}
