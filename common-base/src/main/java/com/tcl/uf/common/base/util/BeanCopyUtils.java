package com.tcl.uf.common.base.util;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 使用Dozer来copy对象，可copy复杂类型的对象，例如对象中的类，List都能copy
 * PropertyUtils和BeanUtils的功能基本一致，唯一的区别是：BeanUtils在对Bean赋值时会进行类型转化，
 * 而PropertyUtils不会对任何类型进行转化，如果类型不同则会抛出异常！
 * <p>
 * 十万次以上的效率dozer和beanutils差不多，次数少时服务器有大把资源，本方法也不会成为性能瓶颈
 *
 * @author dengyuanheng
 */
public class BeanCopyUtils {

    public interface CopyMethod<T, D> {
        /**
         * 组装对象时运行的方法，自由编写代码，copy属性名不一样的字段
         *
         * @param source
         * @param dest
         */
        void doMethod(T source, D dest);
    }

    private static DozerBeanMapper beanMapper = new DozerBeanMapper();

    public static <S, D> D map(S source, Class<D> destClass) {
        return map(source, destClass, null);
    }

    public static <S, D> D map(S source, Class<D> destClass, CopyMethod<S, D> copyMethod) {
        if (source == null) {
            return null;
        }
        D dest = beanMapper.map(source, destClass);
        if (copyMethod != null) {
            copyMethod.doMethod(source, dest);
        }
        return dest;
    }

    public static <S, D> void map(S source, D dest) {
        if (source != null) {
            beanMapper.map(source, dest);
        }
    }

    public static <T, D> List<D> mapList(List<T> sourceList, Class<D> destClass) {
        return mapList(sourceList, destClass, null);
    }

    public static <T, D> List<D> mapList(List<T> sourceList, Class<D> destClass, CopyMethod<T, D> copyMethod) {
        List<D> destList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(sourceList)) {
            return destList;
        }
        for (T orig : sourceList) {
            destList.add(map(orig, destClass, copyMethod));
        }
        return destList;
    }

}
