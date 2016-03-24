package org.archive.crawler.frontier;

import org.archive.crawler.datamodel.CandidateURI;
import org.archive.crawler.framework.CrawlController;

public class ELFHashQueueAssignmentPolicy extends QueueAssignmentPolicy{

	@Override
	public String getClassKey(CrawlController controller, CandidateURI cauri) {
		// TODO Auto-generated method stub
		return this.ELFHash(cauri.getUURI().toString(), 50) + "";
	}
	//取模number是因为一般情况下Heritrix开number个线程，对应number个不同的URI处理队列 number=50
	public int ELFHash(String str, int number) {
	    int hash = 0;
	    long x = 0l;
	    char[] array = str.toCharArray(); 
	    for (int i = 0; i < array.length; i++) {
	        hash = (hash << 4) + array[i];
	        if ((x = (hash & 0xF0000000L)) != 0) {
	            hash ^= (x >> 24);
	            hash &= ~x;
	        }
	    }
	    int result = (hash & 0x7FFFFFFF) % number;
	    return result;
	}
}
