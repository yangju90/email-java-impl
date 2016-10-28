package indi.mat.search;

import java.util.Date;

import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SentDateTerm;

public class DateSearch {
	
	public DateSearch(){
		
	}
	
	//从last到目前时间的搜索
	public SearchTerm pastToNow(Date last){		
		 	SearchTerm comparisonTermGe = new SentDateTerm(ComparisonTerm.GE, last);  
	        SearchTerm comparisonTermLe = new SentDateTerm(ComparisonTerm.LE, new Date());  
	        SearchTerm comparisonAndTerm = new AndTerm(comparisonTermGe, comparisonTermLe);
			return comparisonAndTerm; 
	}

}
