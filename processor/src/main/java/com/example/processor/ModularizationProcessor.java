package com.example.processor;

import com.example.annotation.Action;
import com.example.annotation.Provider;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

@AutoService(Processor.class)
public class ModularizationProcessor extends AbstractProcessor {

    private Elements elementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        // 规定需要处理的注解
        return Collections.singleton(Provider.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Provider.class);
        int i = 0;
        // 判断是否Class

        MethodSpec.Builder bindViewMethodSpecBuilder = MethodSpec.methodBuilder("register")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(TypeName.VOID);

        for (Element element : elements) {
            String provider = "provider" + i;
            String packageName = getPackageName(element);//获取类名
            TypeElement typeElement = getTypeElement(element);

            List<? extends Element> members = elementUtils.getAllMembers(typeElement);

            bindViewMethodSpecBuilder.addStatement(String.format("com.example.modularization.Provider %s = new com.example.modularization.Provider()", provider));
            bindViewMethodSpecBuilder.addStatement(String.format("%s.setPackageName(\"%s\")", provider, packageName));
            bindViewMethodSpecBuilder.addStatement(String.format("%s.setProviderName(\"%s\")", provider, typeElement.getAnnotation(Provider.class).value()));
            for (Element item : members) {
                Action action = item.getAnnotation(Action.class);
                if (action != null) {
                    bindViewMethodSpecBuilder.addStatement(String.format("%s.registerAction(new com.example.modularization.Action(\"%s\",\"%s\"))", provider, action.value(), getMethodName(item)));
                }
            }

            bindViewMethodSpecBuilder.addStatement(String.format("com.example.modularization.Router.getInstance().registerProvider(%s)", provider));

            i++;
        }

        TypeSpec typeSpec = TypeSpec.classBuilder("LonerModularization")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(bindViewMethodSpecBuilder.build())
                .build();
        JavaFile javaFile = JavaFile.builder("apt.modularization", typeSpec).build();

        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }

    /**
     * 获取注解所在类的包名
     *
     * @param element
     * @return
     */
    private String getPackageName(Element element) {
        PackageElement packageElement = (PackageElement) element.getEnclosingElement();
        return packageElement.getQualifiedName().toString();
    }

    /**
     * 获取注解所在类的类名
     *
     * @param element
     * @return
     */
    private String getClassName(Element element) {
        TypeElement typeElement = (TypeElement) element;
        return typeElement.getSimpleName().toString();
    }

    /**
     * 获取注解所在类的全类名
     *
     * @param element
     * @return
     */
    private String getPackageClassName(Element element) {
        TypeElement typeElement = (TypeElement) element;
        return typeElement.getQualifiedName().toString();
    }

    /**
     * 获取typeElement
     *
     * @param element
     * @return
     */
    private TypeElement getTypeElement(Element element) {
        TypeElement typeElement = (TypeElement) element;
        return typeElement;
    }

    /**
     * 获取方法名
     *
     * @param element
     * @return
     */
    private String getMethodName(Element element) {
        String tmp = "";
        if (element instanceof ExecutableElement) {
            ExecutableElement variableElement = (ExecutableElement) element;
            tmp = variableElement.getSimpleName().toString();
        }
        if (element instanceof VariableElement) {
            TypeElement variableElement = (TypeElement) element;
            tmp = variableElement.getSimpleName().toString();
        }
        return tmp;
    }
}
