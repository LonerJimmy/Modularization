package com.loner.processor;

import com.google.auto.service.AutoService;
import com.loner.annotation.Action;
import com.loner.annotation.Provider;
import com.loner.util.ProcessorUtil;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
public class ModularizationProcessor extends AbstractProcessor {

    private Elements elementUtils;
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        // 规定需要处理的注解
        return Collections.singleton(Provider.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Provider.class);

        if (elements.isEmpty()) {
            return false;
        }
        JavaFileObject javaFileObject = null;
        try {
            javaFileObject = filer.createSourceFile("com.loner.Modularization");

            Writer writer = javaFileObject.openWriter();
            writer.write("package com.loner;\n\n");

            writer.write(String.format("public final class %s {\n", "Modularization"));
            writer.write("public void register() {\n");

            int i = 0;

            for (Element element : elements) {

                String provider = "provider" + i;
                String packageClassName = ProcessorUtil.getPackageClassName(element);
                TypeElement typeElement = ProcessorUtil.getTypeElement(element);
                String providerValue = typeElement.getAnnotation(Provider.class).value();

                List<? extends Element> members = elementUtils.getAllMembers(typeElement);

                writer.write(String.format("com.loner.modularization.Provider %s = new com.loner.modularization.Provider();\n", provider));
                writer.write(String.format("%s.setProviderName(\"%s\");\n", provider, providerValue));

                for (Element item : members) {
                    Action action = item.getAnnotation(Action.class);
                    if (action != null) {
                        writer.write(String.format("%s.registerAction(new com.loner.modularization.Action(\"%s\",\"%s\",\"%s\"));\n", provider, action.value(), ProcessorUtil.getMethodName(item), packageClassName));
                    }
                }

                writer.write(String.format("com.loner.modularization.Router.getInstance().registerProvider(%s);\n\n", provider));

                i++;
            }

            writer.write("  }\n" + "}");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }


}
