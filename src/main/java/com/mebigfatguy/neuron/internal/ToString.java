/*
 * neuron - a neural network
 * Copyright (C) 2017 Dave Brosius
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations
 * under the License.
 */
package com.mebigfatguy.neuron.internal;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ToString {

    /**
     * holds objects that have already been converted to string to avoid infinite loops in the toString generation
     */
    private static class VisitedInfo {
        Set<Integer> visited = new HashSet<>();
        int count = 0;
    }

    private static final ThreadLocal<VisitedInfo> visited = new ThreadLocal<VisitedInfo>() {

        @Override
        protected VisitedInfo initialValue() {
            return new VisitedInfo();
        }
    };

    private ToString() {
    }

    public static String build(Object o, String... ignoredFields) {
        VisitedInfo vi = visited.get();
        try {
            vi.count++;
            return generate(o, (ignoredFields == null) ? null : Arrays.<String> asList(ignoredFields), vi.visited);
        } finally {
            if (--vi.count == 0) {
                vi.visited.clear();
            }
        }
    }

    private static String generate(Object o, Collection<String> ignoredFields, Set<Integer> visitedObjects) {

        StringBuilder sb = new StringBuilder(100);
        Class<?> cls = o.getClass();
        Integer identityHC = Integer.valueOf(System.identityHashCode(o));
        sb.append(cls.getSimpleName()).append('[').append(identityHC).append("]{");

        if (!visitedObjects.contains(identityHC)) {
            try {
                visitedObjects.add(identityHC);
                String sep = "";
                for (Field f : cls.getDeclaredFields()) {
                    String fieldName = f.getName();
                    if (!f.isSynthetic() && !fieldName.contains("$") && ((ignoredFields == null) || !ignoredFields.contains(fieldName))) {
                        sb.append(sep);
                        sep = ", ";
                        sb.append(fieldName).append('=');
                        try {
                            f.setAccessible(true);
                            Object value = f.get(o);
                            if (value == null) {
                                sb.append((String) null);
                            } else if (value.getClass().isArray()) {
                                sb.append(Arrays.toString((Object[]) value));
                            } else {
                                sb.append(value);
                            }
                        } catch (SecurityException e) {
                            sb.append("*SECURITY_EXCEPTION*");
                        }
                    }
                }
            } catch (IllegalAccessException | RuntimeException e) {
                // if we get an exception show as much as we can get
            }
        }

        sb.append('}');
        return sb.toString();
    }
}