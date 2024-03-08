package ru.efremov.booklist.domain

data class BookItem(
    val title: String,      //书籍标题
    val author: String,     //作者
    val sbn: String,
    val bookname: String,   //书名
    val publishtime: String,   //出版年份
    var id: String,
    var enabled: Boolean
) {

    companion object {

        const val UNDEFINED_ID = ""
    }
}
