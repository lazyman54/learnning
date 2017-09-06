package com.ek.study.javaSource;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaUnit;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.source.ParameterSource;

import java.util.List;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/9/6
 */
public class JavaSource {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String javaSource = "package com.dafy.base.gateway.data.api;\n" +
                "\n" +
                "import java.util.Map;\n" +
                "\n" +
                "/**\n" +
                " * <p>获取一些配置项值的接口</p>\n" +
                " *\n" +
                " * @author lazyman\n" +
                " * @version v1.0\n" +
                " * @date 2017/8/7\n" +
                " */\n" +
                "public interface IConfigRpc {\n" +
                "\n" +
                "    String getValue(String key);\n" +
                "\n" +
                "    Map<String, Object> getValues(String key, String... otherKeys);\n" +
                "\n" +
                "}\n";


        System.out.println(String[].class);
        System.out.println(Integer[].class);
        System.out.println(int[].class);
        System.out.println(Long[].class);
        System.out.println(long[].class);
        System.out.println(double[].class);

        //Class<?> aClass = Class.forName("class [Ljava.lang.String;");
        //String[] instance = (String[])aClass.newInstance();
        //System.out.println(instance.length);
        JavaUnit javaUnit = Roaster.parseUnit(javaSource);
        JavaInterfaceSource interfaceSource = javaUnit.getGoverningType();

        System.out.println(interfaceSource);

        System.out.println(interfaceSource.getQualifiedName());

        List<MethodSource<JavaInterfaceSource>> methods = interfaceSource.getMethods();
        for (MethodSource<JavaInterfaceSource> method : methods) {
            System.out.println(method.getName());
            List<ParameterSource<JavaInterfaceSource>> parameters = method.getParameters();

            for (ParameterSource<JavaInterfaceSource> parameter : parameters) {
                System.out.println("*******************parameters****************");
                System.out.println(parameter.isVarArgs());
                System.out.print(parameter.getType().getQualifiedName());
                System.out.print(" ");
                System.out.println(parameter.getName());
            }
        }

        System.out.println(interfaceSource.getMethods());


    }
}
