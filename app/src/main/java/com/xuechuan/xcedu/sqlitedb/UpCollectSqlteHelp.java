package com.xuechuan.xcedu.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xuechuan.xcedu.base.DataMessageVo;
import com.xuechuan.xcedu.db.DbHelp.DatabaseContext;
import com.xuechuan.xcedu.utils.DbQueryUtil;
import com.xuechuan.xcedu.vo.SqliteVo.CollectTableSqliteVo;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: ErrorSqlteHelp.java
 * @Package com.xuechuan.xcedu.sqlitedb
 * @Description: 更新收藏表
 * @author: YFL
 * @date: 2018/12/25 22:36
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018/12/25 星期二
 * 注意：本内容仅限于学川教育有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class UpCollectSqlteHelp {
    private Context mContext;
    private static volatile UpCollectSqlteHelp _instance = null;
    private final DbQueryUtil mDbQueryUtil;
    private final SQLiteDatabase mSqLiteDatabase;

    private UpCollectSqlteHelp(Context context) {
        this.mContext = context;
        mSqLiteDatabase = createtable();
        mDbQueryUtil = DbQueryUtil.get_Instance();
    }

    public static UpCollectSqlteHelp getInstance(Context context) {
        if (_instance == null) {
            synchronized (UpCollectSqlteHelp.class) {
                if (_instance == null) {
                    _instance = new UpCollectSqlteHelp(context);
                }
            }
        }
        return _instance;
    }

    private SQLiteDatabase createtable() {
        DatabaseContext context = new DatabaseContext(mContext);
        UserInfomOpenHelp userInfomOpenHelp = UserInfomOpenHelp.get_Instance(context);
        return userInfomOpenHelp.getWritableDatabase();
    }

    private boolean empty() {
        if (mSqLiteDatabase == null)
            return true;
        if (mSqLiteDatabase.isReadOnly())
            return true;
        return false;
    }
   //添加数据
    public void addCollectSqliteVo(CollectTableSqliteVo sqliteVo) {
        if (empty()) return;
        ContentValues values = getContentValues(sqliteVo);
        mSqLiteDatabase.insert(DataMessageVo.USER_QUESTION_COLLECT_TABLE, null, values);
    }
  //删除数据
    public void delectItem(int id) {
        if (empty()) return;
        mSqLiteDatabase.delete(DataMessageVo.USER_QUESTION_COLLECT_TABLE, "id=?",
                new String[]{String.valueOf(id)});
    }

    private ContentValues getContentValues(CollectTableSqliteVo sqliteVo) {
        ContentValues values = new ContentValues();
        values.put("question_id", sqliteVo.getQuestion_id());
        values.put("collectable", sqliteVo.getCollectable());
        values.put("questiontype", sqliteVo.getQuestiontype());
        values.put("chapterid", sqliteVo.getChapterid());
        values.put("time", sqliteVo.getTime());
        values.put("courseid", sqliteVo.getCourseid());
        return values;
    }

    private CollectTableSqliteVo getCollectVo(DbQueryUtil mDbQueryUtil) {
        CollectTableSqliteVo vo = new CollectTableSqliteVo();
        int id = mDbQueryUtil.queryInt("id");
        int question_id = mDbQueryUtil.queryInt("question_id");
        int chapterid = mDbQueryUtil.queryInt("chapterid");
        int courseid = mDbQueryUtil.queryInt("courseid");
        int questiontype = mDbQueryUtil.queryInt("questiontype");
        int collectable = mDbQueryUtil.queryInt("collectable");
        String time = mDbQueryUtil.queryString("time");
        vo.setId(id);
        vo.setChapterid(chapterid);
        vo.setCollectable(collectable);
        vo.setCourseid(courseid);
        vo.setQuestion_id(question_id);
        vo.setQuestiontype(questiontype);
        vo.setTime(time);
        return vo;


    }

}
