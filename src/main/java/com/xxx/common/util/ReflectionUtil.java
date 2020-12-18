package com.xxx.common.util;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * 扫描包
 *
 * @author Ian
 * @date 2020/12/17
 */
public final class ReflectionUtil {

    public static Reflections getReflections(String packageAddress) {
        List<String> packageAddressList;
        if (packageAddress.contains(",")) {
            packageAddressList = Arrays.asList(packageAddress.split(","));
        } else if (packageAddress.contains(";")) {
            packageAddressList = Arrays.asList(packageAddress.split(";"));
        } else {
            packageAddressList = Collections.singletonList(packageAddress);
        }
        return getReflections(packageAddressList);
    }

    public static Reflections getReflections(List<String> packageAddresses) {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        FilterBuilder filterBuilder = new FilterBuilder();
        packageAddresses.forEach(str -> {
            configurationBuilder.addUrls(ClasspathHelper.forPackage(str.trim()));
            filterBuilder.includePackage(str.trim());
        });
        configurationBuilder.filterInputsBy(filterBuilder);
        return new Reflections(configurationBuilder);
    }
}
