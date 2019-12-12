package com.ego.search.service;

import java.io.IOException;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

import com.ego.pojo.TbItem;

public interface TbItemService {
	
	/**
	 * 初始化
	 * @throws SolrServerException
	 * @throws IOException
	 */
	void init() throws SolrServerException, IOException;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	Map<String,Object> selByQuery(String query,int page,int rows) throws SolrServerException, IOException; 
	
	/**
	 * 新增
	 * @param map
	 * @param desc
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	int add(Map<String, Object> map, String desc) throws SolrServerException, IOException;
}
