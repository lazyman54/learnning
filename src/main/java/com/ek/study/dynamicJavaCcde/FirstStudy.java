package com.ek.study.dynamicJavaCcde;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/6/22
 */
public class FirstStudy {

    private JavaCompiler javaCompiler;
    private StandardJavaFileManager stdManager;

    static final String SINGLE_JAVA = "/* a single java class to one file */  "
            + "package com.ek.study;                                            "
            + "public class UserProxy {     "
            + "    boolean _dirty = false;                                    "
            + "    public void setId(String id) {                             "
            + "        setDirty(true);                                        "
            + "    }                                                          "
            + "    public void setName(String name) {                         "
            + "        setDirty(true);                                        "
            + "    }                                                          "
            + "    public void setCreated(long created) {                     "
            + "        setDirty(true);                                        "
            + "    }                                                          "
            + "    public void setDirty(boolean dirty) {                      "
            + "        this._dirty = dirty;                                   "
            + "    }                                                          "
            + "    public boolean isDirty() {                                 "
            + "        return this._dirty;                                    "
            + "    }                                                          "
            + "}                                                              ";

    public FirstStudy() {
        javaCompiler = ToolProvider.getSystemJavaCompiler();
        stdManager = javaCompiler.getStandardFileManager(null, null, Charset.defaultCharset());
    }

    public static void main(String[] args) {
    }

    public Map<String, byte[]> compiler(String fileName, String javaSource) {
        MemoryJavaFileManager manager = new MemoryJavaFileManager(stdManager);
        JavaFileObject simpleJavaFileObject = manager.makeStringSource(fileName, javaSource);
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, manager, null, null, null, Collections.singletonList(simpleJavaFileObject));
        boolean result = task.call();
        if (!result) {
            throw new RuntimeException("Compilation failed.");
        }
        return manager.getClassBytes();

    }

}

class MemoryJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {

    final Map<String, byte[]> classBytes = new HashMap<String, byte[]>();

    public MemoryJavaFileManager(JavaFileManager fileManager) {
        super(fileManager);
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {

        if (kind == JavaFileObject.Kind.CLASS) {
            return new MemoryOutputJavaFileObject(className);
        }
        return super.getJavaFileForOutput(location, className, kind, sibling);
    }

    @Override
    public void flush() throws IOException {
        super.flush();
    }

    @Override
    public void close() throws IOException {
        classBytes.clear();
        super.close();
    }

    JavaFileObject makeStringSource(String name, String code) {
        return new MemoryInputJavaFileObject(name, code);
    }

    public Map<String, byte[]> getClassBytes() {
        return new HashMap<String, byte[]>(this.classBytes);
    }


    class MemoryInputJavaFileObject extends SimpleJavaFileObject {

        final String code;

        MemoryInputJavaFileObject(String name, String code) {
            super(URI.create("string:///" + name), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharBuffer getCharContent(boolean ignoreEncodingErrors) {
            return CharBuffer.wrap(code);
        }
    }

    class MemoryOutputJavaFileObject extends SimpleJavaFileObject {
        final String name;

        MemoryOutputJavaFileObject(String name) {
            super(URI.create("string:///" + name), Kind.CLASS);
            this.name = name;
        }

        @Override
        public OutputStream openOutputStream() {
            return new FilterOutputStream(new ByteArrayOutputStream()) {
                @Override
                public void close() throws IOException {
                    out.close();
                    ByteArrayOutputStream bos = (ByteArrayOutputStream) out;
                    classBytes.put(name, bos.toByteArray());
                }
            };
        }
    }

}