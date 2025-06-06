package SeniorStudy;

import java.lang.annotation.*;

/**
 * @ClassName AnnotationTest
 * @Description
 * @Author SDY
 * @Date 2023/8/20 17:15
 **/
@MyAnnotation(value = "sdy")
public class AnnotationTest {
    public static void main(String[] args) {
        Class<AnnotationTest> annotationTestClass = AnnotationTest.class;
        Annotation annotation = annotationTestClass.getAnnotation(MyAnnotation.class);
        MyAnnotation m = (MyAnnotation) annotation;
        String info = m.value();
        System.out.println(info);
    }

}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MyAnnotation{
    String value() default  "auguigu";
}
