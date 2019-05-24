package com.example.zhujie;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;
import android.widget.Toast;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: TestDemo
 * @Package: com.example.zhujie
 * @ClassName: TestDef
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2019/5/24 16:37
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/24 16:37
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TestDef {
    public static final int MALE = 0;
    public static final int FEMALE = 1;

    public static final String ONE = "one";
    public static final String TWO = "two";

    private @Sex int mmSex;

    @Sex
    public int getMmSex() {
        return mmSex;
    }

    public void setMmSex(@Sex int mmSex) {
        this.mmSex = mmSex;
    }

    private @NumString String numString;

    @NumString
    public String getNumString() {
        return numString;
    }

    /**
     * 报错，没有定义 NumString 可以是参数注解
     *
     * 错误: 注释类型不适用于该类型的声明
     * */
    public void setNumString(@NumString String numString) {
        this.numString = numString;
    }

    @IntDef({
            MALE,
            FEMALE
    })
    @Target({
            ElementType.PARAMETER,
            ElementType.FIELD,
            ElementType.METHOD,
    }) //表示注解作用范围，参数注解，成员注解，方法注解
    @Retention(RetentionPolicy.SOURCE)
    public @interface Sex{

    }

    @StringDef({
            ONE,
            TWO
    })
    @Target({
            ElementType.FIELD,
            ElementType.METHOD,
    }) //表示注解作用范围，成员注解，方法注解
    @Retention(RetentionPolicy.SOURCE)
    public @interface NumString{

    }
}
