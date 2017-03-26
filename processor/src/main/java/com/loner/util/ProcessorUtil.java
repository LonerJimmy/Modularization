package com.loner.util;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

/**
 * Created by loner on 2017/3/24.
 */

public class ProcessorUtil {

    /**
     * 获取注解所在类的包名
     *
     * @param element
     * @return
     */
    public static String getPackageName(Element element) {
        PackageElement packageElement = (PackageElement) element.getEnclosingElement();
        return packageElement.getQualifiedName().toString();
    }

    /**
     * 获取注解所在类的类名
     *
     * @param element
     * @return
     */
    public static String getClassName(Element element) {
        TypeElement typeElement = (TypeElement) element;
        return typeElement.getSimpleName().toString();
    }

    /**
     * 获取注解所在类的全类名
     *
     * @param element
     * @return
     */
    public static String getPackageClassName(Element element) {
        TypeElement typeElement = (TypeElement) element;
        return typeElement.getQualifiedName().toString();
    }

    /**
     * 获取typeElement
     *
     * @param element
     * @return
     */
    public static TypeElement getTypeElement(Element element) {
        TypeElement typeElement = (TypeElement) element;
        return typeElement;
    }

    /**
     * 获取方法名
     *
     * @param element
     * @return
     */
    public static String getMethodName(Element element) {
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
