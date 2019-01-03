package com.xuechuan.xcedu.ui.bank.newbank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuechuan.xcedu.R;
import com.xuechuan.xcedu.adapter.bank.ErrorTextAdapter;
import com.xuechuan.xcedu.base.BaseActivity;
import com.xuechuan.xcedu.mvp.presenter.ErrorTextPresenter;
import com.xuechuan.xcedu.sqlitedb.CollectTableSqliteHelp;
import com.xuechuan.xcedu.sqlitedb.DoMockBankSqliteHelp;
import com.xuechuan.xcedu.sqlitedb.ErrorSqlteHelp;
import com.xuechuan.xcedu.sqlitedb.QuestionSqliteHelp;
import com.xuechuan.xcedu.ui.bank.AnswerActivity;
import com.xuechuan.xcedu.utils.DialogUtil;
import com.xuechuan.xcedu.vo.ErrorOrColloctVo;
import com.xuechuan.xcedu.vo.SqliteVo.DoBankSqliteVo;
import com.xuechuan.xcedu.vo.TaglistsBeanVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyErrorTextActivity
 * @Package com.xuechuan.xcedu.ui.bank
 * @Description: 我的错题/收藏(新的)
 * @author: L-BackPacker
 * @date: 2018/5/3 20:09
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018/5/3
 */
public class MyCollectTextActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 错题或收藏
     */
    private static final String ERRORCOLNUMBER = "errorcol";
    /**
     * 问题di
     */
    private static final String MQUESTION = "question";
    private ImageView mIvBMore;
    private TextView mTvErrorNumber;
    private Button mBtnGoDoText;
    private RecyclerView mRlvErrorList;
    private ErrorTextPresenter mPresenter;
    private Context mContext;
    /**
     * 科目id
     */
    private static String COURESID = "couresid";
    /**
     * 题干类型
     */
    private static String TEXTTYPE = "texttype";
    public static String ERRTYPE = "err";
    public static String FAVTYPE = "fav";
    private String mCouresid;
    private AlertDialog mDialog;
    private String mType;
    private TextView mTvErrorText;
    private LinearLayout mLlErrorHear;
    //类型内容
    private String content;
    private String mNumber;
    private String mQuestion;
    private ErrorSqlteHelp mErrorSqlteHelp;
    private CollectTableSqliteHelp mCollectHelp;
    private QuestionSqliteHelp mSqliteHelp;
    private DoMockBankSqliteHelp mDoMockHelp;
    private Object allData;

    /**
     * @param context
     * @param Couresid 科目
     * @param number   数量
     * @return
     */
    public static Intent newInstance(Context context, String Couresid, String number) {
        Intent intent = new Intent(context, MyCollectTextActivity.class);
        intent.putExtra(COURESID, Couresid);
        intent.putExtra(ERRORCOLNUMBER, number);
        return intent;
    }

    /*   @Override
       protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_error_text);
           initView();
       }
   */
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_error_text);
        if (getIntent() != null) {
            mCouresid = getIntent().getStringExtra(COURESID);
            mType = getIntent().getStringExtra(TEXTTYPE);
            mNumber = getIntent().getStringExtra(ERRORCOLNUMBER);
        }
        initView();
        initUtils();
        initData();
        getAllData();
    }

    private void initUtils() {
        //收藏表
        mCollectHelp = CollectTableSqliteHelp.get_Instance(mContext);
        //本地题库
        mSqliteHelp = QuestionSqliteHelp.get_Instance(mContext);
        //本地数据库
        mDoMockHelp = DoMockBankSqliteHelp.get_Instance(mContext);
    }

    private void initData() {
        content = getString(R.string.MyCollor);
        mLlErrorHear.setBackgroundResource(R.mipmap.ic_col_bg);
        mDialog = DialogUtil.showDialog(mContext, "", getStringWithId(R.string.loading));
        mTvErrorText.setText(content);
        mTvErrorNumber.setText(mNumber);

    }

    public Object getAllData() {
        if (mType.equals(ERRTYPE)) {
            ArrayList<DoBankSqliteVo> list = mDoMockHelp.queryErrorListWithCourseId(mCouresid);

        } else if (mType.equals(FAVTYPE)) {

        }

        return allData;
    }

  /*  @Override
    public void ErrorSuccess(String con) {
        if (mDialog != null&&mDialog.isShowing()) {
            mDialog.dismiss();
        }
        L.e("我的错题结果" + con);
        Gson gson = new Gson();
        ErrorNewVo vo = gson.fromJson(con, ErrorNewVo.class);
        if (vo.getStatus().getCode() == 200) {
            if (vo.getData().getTaglists() != null) {
                bindAdapter(vo.getData().getTaglists());
            }
        } else {
            T.showToast(mContext,getStringWithId(R.string.net_error));
            L.e( vo.getStatus().getMessage());
//            T.showToast(mContext, vo.getStatus().getMessage());
        }
    }*/


    private void bindAdapter(List<TaglistsBeanVo> details) {
        setGridLayoutManger(mContext, mRlvErrorList, 1);
        ErrorTextAdapter adapter = new ErrorTextAdapter(mContext, details);
        mRlvErrorList.setAdapter(adapter);
        adapter.setClickListener(new ErrorTextAdapter.onItemClickListener() {
            @Override
            public void onClickListener(Object obj, int position) {
                TaglistsBeanVo vo = (TaglistsBeanVo) obj;
                Intent intent = AnswerActivity.newInstance(mContext, mCouresid, String.valueOf(vo.getTagid()), mType, vo.getRnum());
                startActivity(intent);

            }
        });

    }

    /* @Override
     public void ErrorError(String con) {
         if (mDialog != null&&mDialog.isShowing()) {
             mDialog.dismiss();
         }
         T.showToast(mContext,getStringWithId(R.string.net_error));
     }
 */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /*   @Override
       public void ErrOrColNumberSuccess(String con) {
           Gson gson = new Gson();
           ErrorOrColloctVo vo = gson.fromJson(con, ErrorOrColloctVo.class);
           if (vo.getStatus().getCode() == 200) {
               bindErrorOrCollortData(vo.getData());
           } else {
               T.showToast(mContext,getStringWithId(R.string.net_error));
               L.e(vo.getStatus().getMessage());
           }
       }
   */
    private void bindErrorOrCollortData(ErrorOrColloctVo.DataBean data) {
        if (mType.equals(ERRTYPE)) {
            mTvErrorNumber.setText(data.getError() + "");
            if (data.getError() == 0) {
                mBtnGoDoText.setClickable(false);
                mBtnGoDoText.setBackgroundResource(R.drawable.btn_errortext_no_bg);
            } else {
                mBtnGoDoText.setClickable(true);
                mBtnGoDoText.setBackgroundResource(R.drawable.btn_errortext_go_bg);
            }
        } else if (mType.equals(FAVTYPE)) {
            mTvErrorNumber.setText(data.getFavorite() + "");
            if (data.getFavorite() == 0) {
                mBtnGoDoText.setClickable(false);
                mBtnGoDoText.setBackgroundResource(R.drawable.btn_errortext_no_bg);
            } else {
                mBtnGoDoText.setClickable(true);
                mBtnGoDoText.setBackgroundResource(R.drawable.btn_errortext_go_bg);
            }
        }

    }

    /*  @Override
      public void ErrOrColNumberError(String con) {
          if (mDialog != null&&mDialog.isShowing()) {
              mDialog.dismiss();
          }
          T.showToast(mContext,getStringWithId(R.string.net_error));
      }
  */
    private void initView() {
        mIvBMore = (ImageView) findViewById(R.id.iv_b_more);
        mTvErrorNumber = (TextView) findViewById(R.id.tv_error_number);
        mBtnGoDoText = (Button) findViewById(R.id.btn_go_do_text);
        mRlvErrorList = (RecyclerView) findViewById(R.id.rlv_error_list);
        mContext = this;
        mBtnGoDoText.setOnClickListener(this);
        mTvErrorText = (TextView) findViewById(R.id.tv_error_text);
        mTvErrorText.setOnClickListener(this);
        mLlErrorHear = (LinearLayout) findViewById(R.id.ll_error_hear);
        mLlErrorHear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go_do_text:
                break;
        }
    }


}
