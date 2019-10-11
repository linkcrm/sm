package com.owen.util;


import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
/**
 * Created by tengj on 2017/4/11.
 */
public class Page {
    //涓�椤垫樉绀虹殑璁板綍鏁�
    private int numPerPage;
    //璁板綍鎬绘暟
    private int totalRows;
    //鎬婚〉鏁�
    private int totalPages;
    //褰撳墠椤电爜
    private int currentPage;
    //璧峰琛屾暟
    private int startIndex;
    //缁撴潫琛屾暟
    private int lastIndex;
    //缁撴灉闆嗗瓨鏀綥ist
    private List<Map<String, Object>> resultList;


    /**鍒嗛〉鏋勯�犲嚱鏁�
     * @param sql 鍖呭惈绛涢�夋潯浠剁殑sql锛屼絾涓嶅寘鍚垎椤电浉鍏崇害鏉燂紝濡俶ysql鐨刲imit
     * @param currentPage 褰撳墠椤�
     * @param numPerPage 姣忛〉璁板綍鏁�
     * @param jdbcTemplate jdbcTemplate瀹炰緥
     */
    public Page(String sql,int currentPage,int numPerPage,JdbcTemplate jdbcTemplate){
        if(jdbcTemplate == null){
            throw new IllegalArgumentException("Page.jdbcTemplate is null");
        }else if(sql == null || sql.equals("")){
            throw new IllegalArgumentException("Page.sql is empty");
        }
        //璁剧疆姣忛〉鏄剧ず璁板綍鏁�
        setNumPerPage(numPerPage);
        //璁剧疆瑕佹樉绀虹殑椤垫暟
        setCurrentPage(currentPage);
        //璁＄畻鎬昏褰曟暟
        StringBuffer totalSQL = new StringBuffer(" SELECT count(*) FROM ( ");
        totalSQL.append(sql);
        totalSQL.append(" ) totalTable ");
        //鎬昏褰曟暟
        setTotalRows(jdbcTemplate.queryForObject(totalSQL.toString(),Integer.class));
        //璁＄畻鎬婚〉鏁�
        setTotalPages();
        //璁＄畻璧峰琛屾暟
        setStartIndex();
        //璁＄畻缁撴潫琛屾暟
        setLastIndex();
        System.out.println("lastIndex="+lastIndex);
        //浣跨敤mysql鏃剁洿鎺ヤ娇鐢╨imits
        StringBuffer paginationSQL = new StringBuffer();
        paginationSQL.append(sql);
        paginationSQL.append(" limit " + startIndex + "," + lastIndex);
        //瑁呭叆缁撴灉闆�
        setResultList(jdbcTemplate.queryForList(paginationSQL.toString()));
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public List<Map<String, Object>> getResultList() {
        return resultList;
    }

    public void setResultList(List<Map<String, Object>> resultList) {
        this.resultList = resultList;
    }

    public int getTotalPages() {
        return totalPages;
    }

    //璁＄畻鎬婚〉鏁�
    public void setTotalPages() {
        if(totalRows % numPerPage == 0){
            this.totalPages = totalRows / numPerPage;
        }else{
            this.totalPages = (totalRows / numPerPage) + 1;
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex() {
        this.startIndex = (currentPage - 1) * numPerPage;
    }

    public int getLastIndex() {
        return lastIndex;
    }


    //璁＄畻缁撴潫鏃跺�欑殑绱㈠紩
    public void setLastIndex() {
        System.out.println("totalRows="+totalRows);///////////
        System.out.println("numPerPage="+numPerPage);///////////
        if( totalRows < numPerPage){
            this.lastIndex = totalRows;
        }else if((totalRows % numPerPage == 0) || (totalRows % numPerPage != 0 && currentPage < totalPages)){
            this.lastIndex = currentPage * numPerPage;
        }else if(totalRows % numPerPage != 0 && currentPage == totalPages){//鏈�鍚庝竴椤�
            this.lastIndex = totalRows ;
        }
    }
}
