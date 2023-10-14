package br.com.matheuseduardo.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeanUtils;


public class Utils {
  public static void copyNonNullProperties(Object src, Object target) {
    BeanUtils.copyProperties(src, target, getNullProperty(src));
  }

  public static String[] getNullProperty(Object source) {
    final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
    PropertyDescriptor[] pds = wrappedSource.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<String>();
    for (PropertyDescriptor pd : pds) {
      Object srcValue = wrappedSource.getPropertyValue(pd.getName());
      if (srcValue == null) emptyNames.add(pd.getName());
    }
    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }
  
}
