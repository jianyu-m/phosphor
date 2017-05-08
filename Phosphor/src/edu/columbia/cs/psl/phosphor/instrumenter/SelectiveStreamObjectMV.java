package edu.columbia.cs.psl.phosphor.instrumenter;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * Created by jianyu on 5/8/17.
 */
public class SelectiveStreamObjectMV extends MethodVisitor {
    SelectiveStreamObjectMV(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        if (owner.startsWith("sun/misc/Unsafe") && name.startsWith("getCastObject")) {
            System.out.println("get Cast " + name);
            super.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(StreamObjectHelper.class), "getCastObject",
                    "(Lsun/misc/Unsafe;Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;", false);
        } else {
            super.visitMethodInsn(opcode, owner, name, desc, itf);
        }
    }
}
