package org.archive.crawler.postprocessor;

import org.archive.crawler.datamodel.CandidateURI;

public class CustomScheduler extends FrontierScheduler {
	 private static final long serialVersionUID = 1L;
	public CustomScheduler(String name) {
		super(name);
	}

	protected void schedule(CandidateURI caUri) {
		String uri=caUri.toString();
//		主页http://221.212.153.203/tax/ww/index.html
		//网页URL中包含221.212.153.203才下载
		if(uri.contains("221.212.153.203")){
			System.out.println(uri);
			getController().getFrontier().schedule(caUri);
		}
	}
}
