package com.pan.learning.io.file;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * search current folders with regex filter
 * <br>目录过滤器, java io, File类, <<think in java>>
 * @version 1.0
 * @author  Pan Yilin
 *
 */
public class DirList {

    public static void main(String args[]) {

        File f = new File(".");
        String[] files = null;

        if (args.length == 0) {
            files = f.list();
        }else{
            Pattern pattern = Pattern.compile(args[0]);
            files = f.list((dir, name) -> pattern.matcher(new File(name).getName()).matches());
        }

        Arrays.sort(files, Comparator.comparing(Object::toString));
        Arrays.stream(files).forEach(System.out::println);
    }


    private static class AlphabeticComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            return o1.toString().compareTo(o2.toString());
        }
    }
}
