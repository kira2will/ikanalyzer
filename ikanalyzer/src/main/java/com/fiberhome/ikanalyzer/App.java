package com.fiberhome.ikanalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	App a = new App();
    	System.out.println(a.getClass() .getClassLoader().getResource(""));
    	ClassLoader.getSystemResourceAsStream("/");
    	String keyWord = "我今天去了阿尔巴斯苏木以及保定东风路，接着又去了上海莲花，最后又去了大同御西生产楼3层传输；SEO研究院是原创的SEO博客";  
        //创建IKAnalyzer中文分词对象  
        IKAnalyzer analyzer = new IKAnalyzer();  
        // 使用智能分词  
        analyzer.setUseSmart(true);  
        // 打印分词结果  
        try {  
        	System.out.println(slicing(analyzer, keyWord));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    
	public static List<String> slicing(IKAnalyzer analyzer, String text) {
		List<String> result = new ArrayList<String>();
		StringReader reader = null;
		TokenStream tokenStream = null;
		try {
			Configuration cfg = DefaultConfig.getInstance(); //加载词库
	        System.out.println(cfg.getExtDictionarys()); 
	        System.out.println(cfg.getQuantifierDicionary());
			System.out.println(cfg.toString());
			reader = new StringReader(text);
			tokenStream = analyzer.tokenStream("content", reader);
			tokenStream.getAttribute(CharTermAttribute.class);
			tokenStream.reset();
			CharTermAttribute charTermAttribute = tokenStream
					.getAttribute(CharTermAttribute.class);
			while (tokenStream.incrementToken()) {
				result.add(charTermAttribute.toString());
				// int startOffset = offsetAttribute.startOffset();
				// int endOffset = offsetAttribute.endOffset();
				// if((endOffset - startOffset) > 1){
				// String term = charTermAttribute.toString();
				// result.add(term);
				// }
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			reader.close();
		}
		return result;
	}
}
