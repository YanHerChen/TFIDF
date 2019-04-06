package main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class TFIDF {
	private HashMap<String, Integer> tf;
	private HashMap<String, Integer> df;
	private HashMap dttemp;
	private double doc_size;

	TFIDF(){
		 tf = new HashMap<String, Integer>();
		 df = new HashMap<String, Integer>();
		 dttemp = new HashMap();
	}

	public ArrayList<String[]> TFIDF(String[] document) {
		doc_size = document.length;

		for(String sentence:document) {
			String[] spl = sentence.split("	");
			for(String st:spl) {
				tf(st);
				df(st);
			}
		}

		return TFIDF_cal();
	}
	
	public ArrayList<String[]> TFIDF(ArrayList<String> document) {
		doc_size = document.size();
		
		for(String sentence:document) {
			String[] spl = sentence.split("	");
			for(String st:spl) {
				tf(st);
				df(st);
			}
		}
        
        	return TFIDF_cal();
	}

	private void tf(String st) {
		if (tf.containsKey(st)) {
			String temp = String.valueOf(tf.get(st));
			int value = Integer.valueOf(temp);
			value++;
			tf.put(st, value);
		} else {
			tf.put(st, 1);
		}
	}

	private void df(String st) {
		if (dttemp.containsKey(st)) {
		} else if (df.containsKey(st)) {
			String temp = String.valueOf(df.get(st));
			int value = Integer.valueOf(temp);
			value++;
			df.put(st, value);
			dttemp.put(st, 1);
		} else {
			df.put(st, 1);
			dttemp.put(st, 1);
		}
	}
	
	private ArrayList<String[]> TFIDF_cal() {
		double WordSize = tf.size();
		double SentenceSize = doc_size;
		ArrayList<String[]> tfidf_list = new ArrayList<String[]>();
		int tfi, dfi;
		double TFCount, DFCount, TFIDF;
		DecimalFormat Dectf = new DecimalFormat("#.#######");//取小數7位
		DecimalFormat Decdf = new DecimalFormat("#.###");//取小數三位
		String temp[];
		
		for (Object key : tf.keySet()) {
			tfi = Integer.valueOf(tf.get(key));
			dfi = Integer.valueOf(df.get(key));

			TFCount = tfi / WordSize;
			DFCount = Math.log(SentenceSize / dfi);
			TFIDF = TFCount * DFCount;
			
			temp = new String[6];
			temp[0] = String.valueOf(key);
			temp[1] = tfi+"";
			temp[2] = dfi+"";
			temp[3] = Dectf.format(TFCount);
			temp[4] = Decdf.format(DFCount);
			temp[5] = Dectf.format(TFIDF);
			
			tfidf_list.add(temp);
		}
		
		return tfidf_list;
	}
}
