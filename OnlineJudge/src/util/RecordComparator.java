package util;

import java.util.Comparator;

import vo.Record;

public class RecordComparator implements Comparator {
    public int compare(Object o1, Object o2) {
    	Record s1 = (Record) o1;
    	Record s2 = (Record) o2;
    	if (s1.getSubmittime() > s2.getSubmittime())
    		return -1;
    	return 1;
    }
}