package ru.efremov.booklist.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;

/**
 * @Description: 书籍
 * @Author: jeecg-boot
 * @Date:   2024-03-09
 * @Version: V1.0
 */
public class HnBook implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/

    // "主键"
    private String id;
	/**创建人*/
    //  "创建人"
    private String createBy;
	/**创建日期*/

    //  "创建日期"
    private String createTime;



	/**标题*/
    private String title;
	/**作者*/
    private String author;
	/**出版年份*/
    private String publishtime;
	/**SBN*/
    private String sbn;
	/**书名*/
    private String bookname;
}
