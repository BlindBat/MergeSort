package kz.blindmouse.algo.sorting

/**
 * Created by Kuan on 06.11.2016.
 */
import static org.codehaus.groovy.runtime.typehandling.NumberMath.*

public class MergeSort {
    private static long inversionsCount;
    static List<Integer> sublist(List<Integer> list, int x, int y) {
        return list.subList(x, y)
    }
    public static List<Integer> mergeSort(List<Integer> a) {
        if (a.size() == 1) {
            return a
        }
        def int n = a.size()
        def int mid = intdiv(n,2)+n%2

        def List<Integer> b = sublist(a,0, mid)
        if (b.size() >= 2)
            b = mergeSort(b)

        def List<Integer> c = sublist(a,mid, n)
        if (c.size() >= 2)
            c = mergeSort(c)
        def int i = 0
        def int j = 0
        def List<Integer> d = new ArrayList<Integer>(n)
        for (def int k = 0; k < n; k++) {
            if (c[j] == null || b[i] != null && b[i] < c[j]) {
                d[k] = b[i]
                i++
            } else {
                inversionsCount+=b.size()-i
                d[k] = c[j]
                j++
            }
        }
        return d
    }

    public static void main(String[] args) {
        int times = 5
        int avgTime = 0
        def inputD = new ArrayList<Integer>()
        new File('D:\\Downloads', 'IntegerArray.txt').eachLine { line ->
            //if (inputD.size() < 100)
            inputD.add(Integer.parseInt(line))
        }
        def outputD = new ArrayList<Integer>()
        //inputD=[1,3,5,2,4,6]
        times.times {
            inversionsCount=0;
            long mils = System.currentTimeMillis();
            outputD = mergeSort(inputD)
            println("${(System.currentTimeMillis() - mils)/1000} s")
            avgTime += (System.currentTimeMillis() - mils) / times
        }
        println("avgTime: ${avgTime/1000} s")
        //println("${(System.currentTimeMillis() - mils)/1000} s")
        println("inversionsCount: $inversionsCount")
        /*outputD.each { num ->
            println(num)
        }*/
    }
}