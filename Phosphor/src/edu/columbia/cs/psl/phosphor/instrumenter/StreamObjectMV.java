package edu.columbia.cs.psl.phosphor.instrumenter;

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
        if (owner.equals("sun/misc/Unsafe") && name.equals("putObjectWrapper")) {
//            System.out.println(desc);
            desc = "(Lsun/misc/Unsafe;Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Class;)V";
            super.visitMethodInsn(Opcodes.INVOKESTATIC,
                    Type.getInternalName(StreamObjectHelper.class),
                    name, desc, itfc);
            System.out.println("found");
            return;
        } else if (owner.equals("sun/misc/Unsafe") && name.equals("putObjectWrapper$$PHOSPHORTAGGED")) {
            desc = "(Lsun/misc/Unsafe;Ljava/lang/Object;IJLjava/lang/Object;Ljava/lang/Class;)V";
            super.visitMethodInsn(Opcodes.INVOKESTATIC,
                    Type.getInternalName(StreamObjectHelper.class),
                    name, desc, itfc);
//            System.out.println("found-tag");
            return;
        }
        super.visitMethodInsn(opcode, owner, name, desc, itfc);
    }
}
