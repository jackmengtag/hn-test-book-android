package ru.efremov.booklist.model;

//import com.qzy.laobiao.common.base.BaseModel;

import java.util.List;

import ru.efremov.booklist.entity.HnBook;

public class SearchModel extends BaseModel {
    public  Searchbody body;
//"body":{"currentPage":0,"maxResults":10,"totalPages":0,"totalRecords":0,"records":[]},"state":200,"msg":"请求成功"}
    public Searchbody getbody() {
        return body;///AgreementModel
//       return "";"body":{"currentPage":0,"maxResults":10,"totalPages":0,"totalRecords":0,"records":[]},"state":200,"msg":"请求成功"}
    }
    public class Searchbody {
        public int currentPage;//0
        public int maxResults;//0
        public int totalPages;//0
        public int totalRecords;//0
        public  List<HnBook> records;
        public List<HnBook> getlist() {
            return records;
        }


    }
    private Searchbody getBody() {
        return body;
    }
    private void setBody(Searchbody body) {
        this.body = body;
    }
//    String msg=getMsg();
//    String state=getState();
//	 "msg": "string",
//  "state": 0
//    public SearchModel() {
//        body=new Searchbody();
//    }
    //定义 输出返回数据 的方法
    public void show() {

        System.out.println(getMsg());
        System.out.println(getState()+"State");
//        List<Searchbody.> records=getbody().getlist();
//        System.out.println(records.size()+"size");
//        for(int i=0;i<records.size();i++)
//        {
//            System.out.println(records.get(i).accountName);
//            System.out.println(records.get(i).nickName);
//        }
    }

    }

