# Introduction

Modularization is a modular scheme according to http://blog.spinytech.com/2016/12/28/android_modularization/
but with more simplific using method.

# Getting Started

-  Add Module processor and modularization into your project(Upload JCENTER not yet)：

-  Call in build.gradle：

Project build.gradle add code:

``` java
dependencies {
        ...
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
        }
```

Module build.gradle add code(targetModuleName is your module name):

``` java
apply plugin: 'com.neenbedankt.android-apt'

dependencies {
    ...
    compile project(path: ':processor')
    apt project(':processor')
}

apt {
    arguments {
        targetModuleName 'MainModule'
    }
}
```
-  Definition in code：

You can define Provider and Action anywhere.Different Module must not have simple Provide.
``` java
@Provider("loner")
public class DemoUtil {

    @Action("isBlank")
    public String isBlank(String s) {
        Log.e("loner", "Demo util is or not blank");
        return s+"loner maindemo hello";
    }

}
```
-  Use Action in code：

AppApplication add code:

``` java
Modularization.init("MainDemo","MainModule",...);
```
Method init(String ...) is your Module Name.

Use other Module method:
``` java
Modularization.excute("demo3", "isBlank3", String.class);
```
Params first is your Provider name,second is your Action name,last is the method params.




