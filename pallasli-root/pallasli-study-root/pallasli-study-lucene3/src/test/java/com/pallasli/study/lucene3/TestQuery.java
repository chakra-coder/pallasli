package com.pallasli.study.lucene3;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class TestQuery {
	public static void main(String[] args) throws IOException, ParseException {
		String index = "C:\\index"; // 搜索的索引路径
		IndexReader reader = IndexReader
				.open(FSDirectory.open(new File(index)));
		IndexSearcher searcher = new IndexSearcher(reader);

		ScoreDoc[] hits = null;
		String queryString = "catalina"; // 搜索的关键词
		Query query = null;

		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
		try {
			QueryParser qp = new QueryParser(Version.LUCENE_35, "body",
					analyzer);
			query = qp.parse(queryString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (searcher != null) {
			System.out.println(query.toString());
			TopDocs results = searcher.search(query, 10); // 返回最多为10条记录
			hits = results.scoreDocs;
			System.out.println(hits.length);
			if (hits.length > 0) {
				System.out.println("找到:" + hits.length + " 个结果!");
			}
			searcher.close();
		}

	}

}
