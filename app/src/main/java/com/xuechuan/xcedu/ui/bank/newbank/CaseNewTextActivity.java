package com.xuechuan.xcedu.ui.bank.newbank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xuechuan.xcedu.R;
import com.xuechuan.xcedu.adapter.bank.CaseAnswerAdapter;
import com.xuechuan.xcedu.base.BaseActivity;
import com.xuechuan.xcedu.base.DataMessageVo;
import com.xuechuan.xcedu.fragment.GmReadTwoFragment;
import com.xuechuan.xcedu.sqlitedb.DoBankSqliteHelp;
import com.xuechuan.xcedu.sqlitedb.DoLogProgressSqliteHelp;
import com.xuechuan.xcedu.sqlitedb.DoLogSqlteHelp;
import com.xuechuan.xcedu.sqlitedb.DoMockBankSqliteHelp;
import com.xuechuan.xcedu.sqlitedb.ErrorSqlteHelp;
import com.xuechuan.xcedu.sqlitedb.QuestionSqliteHelp;
import com.xuechuan.xcedu.sqlitedb.UpDoBankSqlteHelp;
import com.xuechuan.xcedu.sqlitedb.UserInfomDbHelp;
import com.xuechuan.xcedu.utils.DialogUtil;
import com.xuechuan.xcedu.utils.EncryptionUtil;
import com.xuechuan.xcedu.utils.GmReadColorManger;
import com.xuechuan.xcedu.utils.GmTextUtil;
import com.xuechuan.xcedu.utils.T;
import com.xuechuan.xcedu.vo.CaseCardVo;
import com.xuechuan.xcedu.vo.SqliteVo.DoBankSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.DoLogProgreeSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.ErrorSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.QuestionSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.UserInfomSqliteVo;
import com.xuechuan.xcedu.weight.CommonPopupWindow;
import com.xuechuan.xcedu.weight.ReaderViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: TextActivity
 * @Package com.xuechuan.xcedu.ui.bank
 * @Description: 简答题
 * @author: L-BackPacker
 * @date: 2018.12.05 下午 3:21
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018.12.05
 */
public class CaseNewTextActivity /*extends BaseActivity implements View.OnClickListener, GmReadTwoFragment.notification {
    *//**
     * 章节id
     *//*
    private static String CHAPTER_ID = "com.xuechuan.xcedu.ui.bank.CHAPTER_ID";
    *//**
     *  科目
     *//*
    private static String COUERSID = "com.xuechuan.xcedu.ui.bank.COUERSID";
    private Context mContext;

    private int mChapter_Id;
    private String mCouers_id;
    private DoBankSqliteHelp mDoBankHelp;
    //第一个数据
    private ArrayList<CaseCardVo> mMainDataLists;
    //第二个数据
    private ArrayList<CaseCardVo> mFuDataLists;
    private GmReadTwoFragment mReadFragment;
    private ImageView mIvTextBarBack;
    private ImageView mIvTextBarTimeImg;
    private TextView mActivityTitleText;
    private LinearLayout mLlTextBarTitle;
    private ImageView mIvTextBarDelect;
    private ImageView mIvTextBarMore;
    private LinearLayout mLlTextTitleBar;
    private View mVGmReadLine;
    private ImageView mShadowView;
    private FrameLayout mFlContentLayoutOne;
    private TextView mTvTextCollect;
    private ImageView mIvTextMenu;
    private TextView mTvTextQid;
    private TextView mTvTextAllqid;
    private TextView mTvTextShare;
    private LinearLayout mLiTextNavbar;
    private LinearLayout mLlNewtextBar;
    private LinearLayout mSlidingLayout;
    private GmTextUtil mTextUtil;
    private CommonPopupWindow mPopAnswer;
    private GmReadColorManger mColorManger;
    private DoLogProgressSqliteHelp mDoLogProgressSqliteHelp;
    private DialogUtil mDialogUtil;
    private View mVGmBarLine;
    private DoLogSqlteHelp mDoLoghelp;
    private ArrayList<CaseCardVo> mCardLists = new ArrayList<>();
    private DoMockBankSqliteHelp mDoMockBankSqliteHelp;
    private UserInfomDbHelp mInfomdbhelp;
    private UpDoBankSqlteHelp mUpDoBankSqlteHelp;
    private ErrorSqlteHelp mErrorSqlteHelp;
    private QuestionSqliteHelp mSqliteHelp;
    private TextView mTvEmpty;
    private ReaderViewPager mReaderViewPagerone;
    private ReaderViewPager mReaderViewPagertwo;
    private CaseCardVo mCaseCardVo;
    //判断是否主管题
    private boolean mainquestion = true;

    *//**
     *  * @param id        章节id
     * @param mCouersid 科目id
     * @param context
     * @param id
     * @param mCouersid
     * @return
     *//*

    public static Intent start_Intent(Context context, int id, String mCouersid) {
        Intent intent = new Intent(context, CaseNewTextActivity.class);
        intent.putExtra(CHAPTER_ID, id);
        intent.putExtra(COUERSID, mCouersid);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_text);
        initView();
    }


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_new_text);
        if (getIntent() != null) {
            //章节id
            mChapter_Id = getIntent().getIntExtra(CHAPTER_ID, -1);
            //科目id
            mCouers_id = getIntent().getStringExtra(COUERSID);

        }
        initView();
        initUtils();
        mMainDataLists = getLists();
        if (mMainDataLists == null || mMainDataLists.isEmpty()) {
            mFlContentLayoutOne.setVisibility(View.GONE);
            mTvEmpty.setVisibility(View.VISIBLE);
            return;
        } else {
            mTvEmpty.setVisibility(View.GONE);
            mFlContentLayoutOne.setVisibility(View.VISIBLE);
        }
        mTvTextShare.setVisibility(View.GONE);
        //初始化翻页效果
        initReadViewPagerOne();
        //显示下表
        initData(0);
        //显示对话框
        doShowDialogEvent();

    }

    *//**
     * 显示继续答题对话框
     *//*
    private void doShowDialogEvent() {
        if (mDoLogProgressSqliteHelp != null) {
            if (mMainDataLists != null && !mMainDataLists.isEmpty()) {
                final DoLogProgreeSqliteVo look = mDoLogProgressSqliteHelp.findLookWithTidChapterId(
                        mChapter_Id, Integer.parseInt(mCouers_id), DataMessageVo.ORDER_THREE);
                if (look == null) return;
                mDialogUtil.showContinueDialog(mContext, look.getNumber());
                mDialogUtil.setContinueClickListener(new DialogUtil.onContincueClickListener() {
                    @Override
                    public void onCancelClickListener() {
                        if (mReaderViewPagerone != null) {
                            mReaderViewPagerone.setCurrentItem(0, true);
                            mDoLogProgressSqliteHelp.deleteLookItem(look.getId());
                        }
                    }

                    @Override
                    public void onNextClickListener() {
                        if (mReaderViewPagerone != null)
                            mReaderViewPagerone.setCurrentItem(Integer.parseInt(look.getNumber()) - 1, true);
                    }
                });
            }
        }


    }

    private void initUtils() {
        mTextUtil = GmTextUtil.get_Instance(mContext);
        mDoBankHelp = DoBankSqliteHelp.get_Instance(mContext);
        //观看记录表
        mDoLogProgressSqliteHelp = DoLogProgressSqliteHelp.get_Instance(mContext);
        mSqliteHelp = QuestionSqliteHelp.get_Instance(mContext);
        mDialogUtil = DialogUtil.getInstance();
        //用户做题记录表（去重的）
        mDoLoghelp = DoLogSqlteHelp.getInstance(mContext);
        //用户做题记录表（记录）
        mDoMockBankSqliteHelp = DoMockBankSqliteHelp.get_Instance(mContext);
        //用户信息表
        mInfomdbhelp = UserInfomDbHelp.get_Instance(mContext);
        //上传用户表
        mUpDoBankSqlteHelp = UpDoBankSqlteHelp.getInstance(mContext);
        //错题表
        mErrorSqlteHelp = ErrorSqlteHelp.getInstance(mContext);
    }


    private void initData(int index) {
        mTvTextAllqid.setText(String.valueOf(mMainDataLists.size()));
        mTvTextQid.setText(String.valueOf(++index));
    }

    //找到用户做题数据
    private ArrayList<DoBankSqliteVo> findAllDoDatas() {
        ArrayList<DoBankSqliteVo> list = new ArrayList<>();
        //查询用户数据
        ArrayList<DoBankSqliteVo> doBankSqliteVos = mDoBankHelp.finDAllUserDoText();
        if (doBankSqliteVos == null || doBankSqliteVos.isEmpty()) return list;
        for (int i = 0; i < mMainDataLists.size(); i++) {
            CaseCardVo caseCardVo = mMainDataLists.get(i);
            if (caseCardVo.getList() != null && !caseCardVo.getList().isEmpty()) {
                ArrayList<CaseCardVo> lists = caseCardVo.getList();
                for (int k = 0; k < lists.size(); k++) {
                    CaseCardVo bean = lists.get(k);
                    QuestionSqliteVo vo = bean.getVo();
                    for (int l = 0; l < doBankSqliteVos.size(); l++) {
                        DoBankSqliteVo sqliteVo = doBankSqliteVos.get(l);
                        if (vo.getQuestion_id() == sqliteVo.getQuestion_id()) {
                            list.add(sqliteVo);
                        }
                    }
                }
            }
        }
        return list;
    }

    private int prePosition1 = -1;
    private int curPosition1;

    private void initReadViewPagerOne() {
        mReaderViewPagerone.setAdapter(new GmFragmentAdpater(getSupportFragmentManager(), mContext, mMainDataLists, mCouers_id));
        mReaderViewPagerone.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mShadowView.setTranslationX(mReaderViewPagerone.getWidth() - positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                curPosition1 = position;
                prePosition1 = curPosition1;
                initData(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private int prePosition2 = -1;
    private int curPosition2;

    private void initReadViewPagerTwo() {
        mReaderViewPagerone.setAdapter(new GmFragmentAdpater(getSupportFragmentManager(), mContext, mFuDataLists, mCouers_id));
        mReaderViewPagerone.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mShadowView.setTranslationX(mReaderViewPagerone.getWidth() - positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                curPosition2 = position;
                prePosition2 = curPosition2;
                initData(position);
            }

            private boolean mIsScrolled;                   //  viewpager是否处于惯性滑动

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        mIsScrolled = false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        mIsScrolled = true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (!mIsScrolled) {
//                            Log.e("===", "执行");
                            if (prePosition2 == 0) {//从左到右
                                changerMainView();
                            } else if (prePosition2 == mFuDataLists.size() - 1) {//从右到左
                                changerFuView();
                            }
                        }
                        mIsScrolled = true;
                        break;
                }
            }


        });

    }

    //向前滑动
    private void changerMainView() {
        if (prePosition1 == 0) {
            T.showToast(mContext, "已经是第一道题");
            return;
        }
        doMainFuView(true, false);
        mReaderViewPagerone.setCurrentItem(curPosition1 - 1, true);
    }

    //向后滑动
    private void changerFuView() {
        if (prePosition1 == mMainDataLists.size() - 1) {
            T.showToast(mContext, "已经是最后一道题");
            return;
        }
        doMainFuView(true, false);
        mReaderViewPagerone.setCurrentItem(curPosition1 + 1, true);
    }

    *//**
     * @param main 主界面
     * @param fu   副界面
     *//*

    private void doMainFuView(boolean main, boolean fu) {
        mReaderViewPagerone.setVisibility(main ? View.VISIBLE : View.GONE);
        mReaderViewPagertwo.setVisibility(fu ? View.VISIBLE : View.GONE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_text_menu://菜单
                showAnswerCardLayout();
                break;
            case R.id.iv_text_bar_more://更多
                if (mReadFragment != null) {
                    mReadFragment.showGmSetting();
                }
                break;
            case R.id.iv_text_bar_back://返回
                this.finish();
                break;


        }
    }


    private void initView() {
        mContext = this;
        mIvTextBarBack = (ImageView) findViewById(R.id.iv_text_bar_back);
        mIvTextBarBack.setOnClickListener(this);
        mIvTextBarTimeImg = (ImageView) findViewById(R.id.iv_text_bar_time_img);
        mIvTextBarTimeImg.setOnClickListener(this);
        mActivityTitleText = (TextView) findViewById(R.id.activity_title_text);
        mActivityTitleText.setOnClickListener(this);
        mLlTextBarTitle = (LinearLayout) findViewById(R.id.ll_text_bar_title);
        mLlTextBarTitle.setOnClickListener(this);
        mIvTextBarDelect = (ImageView) findViewById(R.id.iv_text_bar_delect);
        mIvTextBarDelect.setOnClickListener(this);
        mIvTextBarMore = (ImageView) findViewById(R.id.iv_text_bar_more);
        mIvTextBarMore.setOnClickListener(this);
        mLlTextTitleBar = (LinearLayout) findViewById(R.id.ll_text_title_bar);
        mLlTextTitleBar.setOnClickListener(this);
        mVGmReadLine = (View) findViewById(R.id.v_gm_read_line);
        mVGmReadLine.setOnClickListener(this);
        mShadowView = (ImageView) findViewById(R.id.shadowView);
        mShadowView.setOnClickListener(this);
        mFlContentLayoutOne = (FrameLayout) findViewById(R.id.fl_content_layout_one);
        mFlContentLayoutOne.setOnClickListener(this);
        mTvTextCollect = (TextView) findViewById(R.id.tv_text_collect);
        mTvTextCollect.setOnClickListener(this);
        mIvTextMenu = (ImageView) findViewById(R.id.iv_text_menu);
        mIvTextMenu.setOnClickListener(this);
        mTvTextQid = (TextView) findViewById(R.id.tv_text_qid);
        mTvTextQid.setOnClickListener(this);
        mTvTextAllqid = (TextView) findViewById(R.id.tv_text_allqid);
        mTvTextAllqid.setOnClickListener(this);
        mTvTextShare = (TextView) findViewById(R.id.tv_text_share);
        mTvTextShare.setOnClickListener(this);
        mLiTextNavbar = (LinearLayout) findViewById(R.id.li_text_navbar);
        mLiTextNavbar.setOnClickListener(this);
        mLlNewtextBar = (LinearLayout) findViewById(R.id.ll_newtext_bar);
        mLlNewtextBar.setOnClickListener(this);
        mSlidingLayout = (LinearLayout) findViewById(R.id.sliding_layout);
        mSlidingLayout.setOnClickListener(this);
        mVGmBarLine = (View) findViewById(R.id.v_gm_bar_line);
        mVGmBarLine.setOnClickListener(this);
        mTvEmpty = (TextView) findViewById(R.id.tv_empty);
        mTvEmpty.setOnClickListener(this);
        mReaderViewPagerone = (ReaderViewPager) findViewById(R.id.readerViewPager);
        mReaderViewPagerone.setOnClickListener(this);
        mReaderViewPagertwo = (ReaderViewPager) findViewById(R.id.readerViewPagertwo);
        mReaderViewPagertwo.setOnClickListener(this);
    }

    public ArrayList<CaseCardVo> getLists() {
        ArrayList<QuestionSqliteVo> list = mSqliteHelp.queryAllQuesitonWithCouresid(mCouers_id);
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                QuestionSqliteVo vo = list.get(i);
                String d = EncryptionUtil.D(vo.getQuestion());
                vo.setQuestionStr(d);
                String keyword = EncryptionUtil.D(vo.getKeywords());
                vo.setKeywordStr(keyword);
                String explain = EncryptionUtil.D(vo.getExplained());
                vo.setExplainedStr(explain);
            }
        }
        ArrayList<CaseCardVo> mCardLists = new ArrayList<>();
        mTextUtil.doDataListEvent(mCardLists, list);
        return mCardLists;

    }

    @Override
    public void saveUserDoLog(DoBankSqliteVo vo) {
        if (vo==null)return;
        mDoBankHelp.addDoBankItem(vo);

    }

    @Override
    public DoBankSqliteVo getUserDoLog(int quesiton_id) {
        if (mDoBankHelp == null) return null;
        DoBankSqliteVo vo = mDoBankHelp.queryWQid(quesiton_id);
        return vo;
    }

    @Override
    public void deleteUserDolog(int quesiton_id) {
        mDoBankHelp.deleteBankWithQuestid(quesiton_id);
    }

    *//**
     * 下一题
     *//*
    @Override
    public void doRightGo() {
    }

    @Override
    public void changerColor(GmReadColorManger colorManger) {
        this.mColorManger = colorManger;
        mLiTextNavbar.setBackgroundColor(colorManger.getmLayoutBgColor());
        mTvTextCollect.setTextColor(colorManger.getmTextFuColor());
        mTvTextShare.setTextColor(colorManger.getmTextFuColor());
        mTvTextQid.setTextColor(colorManger.getmTextRedColor());
        mTvTextAllqid.setTextColor(colorManger.getmTextRedColor());
        mLlTextTitleBar.setBackgroundColor(colorManger.getmLayoutBgColor());
        mActivityTitleText.setTextColor(colorManger.getmTextTitleColor());
        mLlTextTitleBar.setBackgroundColor(colorManger.getmLayoutBgColor());
        mVGmReadLine.setBackgroundColor(colorManger.getmCutLineColor());
        //标题
        mLlTextTitleBar.setBackgroundColor(colorManger.getmLayoutBgColor());
        mVGmBarLine.setBackgroundColor(colorManger.getmCutLineColor());

    }

    //用户错题表
    @Override
    public void doErrorLog(ErrorSqliteVo vo) {

    }

    @Override
    public void changerSmallQuestion(int  postion) {
        doMainFuView(false, true);
        CaseCardVo vo = mMainDataLists.get(postion);
        if (vo.getList() == null || vo.getList().isEmpty()) {
            return;
        }
        mFuDataLists=vo.getList();;
    }

    @Override
    public void changerMainQuesiton() {
      doMainFuView(true,false);
    }

    @Override
    public void doEventHine(boolean isShow) {
        mLlNewtextBar.setVisibility(isShow ? View.GONE : View.VISIBLE);
    }

    @Override
    public boolean getMainQuesiton() {
        return mainquestion;
    }


    @Override
    public int getFuPostionList() {
        if (mFuDataLists==null||mFuDataLists.isEmpty())
            return 0;
        return mFuDataLists.size();
    }

    @Override
    public QuestionSqliteVo getQuestionVo(int id) {
        return null;
    }


    @Override
    public DoBankSqliteVo queryUserData(int qustion_id) {
        if (mDoBankHelp == null) return null;
        return mDoBankHelp.queryWQid(qustion_id);
    }

    public class GmFragmentAdpater extends FragmentStatePagerAdapter {

        private final String mCourseid;
        private Context mContext;
        private List<?> mListDatas;


        public GmFragmentAdpater(FragmentManager fm, Context mContext, List<?> mListDatas, String coursid) {
            super(fm);
            this.mListDatas = mListDatas;
            this.mContext = mContext;
            this.mCourseid = coursid;
        }

        @Override
        public Fragment getItem(int position) {
            if (mListDatas.size() == 0) {
                mReadFragment = GmReadTwoFragment.newInstance(null, position, mCourseid);
            } else {
                mCaseCardVo = (CaseCardVo) mListDatas.get(position);

                mReadFragment = GmReadTwoFragment.newInstance(mCaseCardVo, position, mCourseid);
            }
            return mReadFragment;
        }

        @Override
        public int getCount() {
            if (mListDatas.size() == 0) {
                return 1;
            } else
                return mListDatas.size();
        }
    }
    *//**
     * 设置答题卡布局
     *//*
    private void showAnswerCardLayout() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;
        mPopAnswer = new CommonPopupWindow(this, R.layout.pop_case_item_answer, ViewGroup.LayoutParams.MATCH_PARENT, (int) (screenHeight * 0.7)) {
            private LinearLayout mLlGmCardLayout;
            private RecyclerView mRlvCasenewText;
            private Button mBtnPopAnswerSumbit;
            private TextView mTvTextPopRegression;
            private TextView mTvTextPopError;
            private TextView mTvTextPopRight;
            private TextView mTvPopCount;
            private TextView mTvLine;
            private TextView mTvPopNew;

            @Override
            protected void initView() {
                View view = getContentView();
                mLlGmCardLayout = (LinearLayout) view.findViewById(R.id.ll_gm_card_layout);
                mRlvCasenewText = (RecyclerView) view.findViewById(R.id.rlv_casenew_text);
                mBtnPopAnswerSumbit = (Button) view.findViewById(R.id.btn_pop_answer_sumbit);
                mTvTextPopRegression = (TextView) view.findViewById(R.id.tv_text_pop_regression);
                mTvTextPopError = view.findViewById(R.id.tv_text_pop_error);
                mTvTextPopRight = view.findViewById(R.id.tv_text_pop_right);
                mTvPopCount = view.findViewById(R.id.tv_pop_count);
                mTvLine = view.findViewById(R.id.tv_line);
                mTvPopNew = view.findViewById(R.id.tv_pop_new);
            }

            @Override
            protected void initEvent() {
                if (mColorManger != null) {
                    mTvPopNew.setTextColor(mColorManger.getmTextTitleColor());
                    mTvPopCount.setTextColor(mColorManger.getmTextTitleColor());
                    mTvTextPopRight.setTextColor(mColorManger.getmTextFuColor());
                    mTvTextPopRegression.setTextColor(mColorManger.getmTextFuColor());
                    mTvLine.setTextColor(mColorManger.getmTextTitleColor());
                    mLlGmCardLayout.setBackgroundColor(mColorManger.getmLayoutBgColor());
                }
                bindGridViewAdapter();
            }

            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance = getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        mTextUtil.setBackgroundAlpha(1f, CaseNewTextActivity.this);
                    }
                });
            }

            private void bindGridViewAdapter() {
                setGridLayoutManger(mContext, mRlvCasenewText, 1);
                CaseAnswerAdapter adapter = new CaseAnswerAdapter(mContext, mMainDataLists);
                mRlvCasenewText.setAdapter(adapter);
            }
        };
        mPopAnswer.showAtLocation(mSlidingLayout, Gravity.BOTTOM, 0, 0);
        mTextUtil.setBackgroundAlpha(0.5f, CaseNewTextActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCardLists = null;
        doDoText();
        if (mDoBankHelp != null) {
            mDoBankHelp.delelteTable();
        }
    }

    private void doDoText() {
        if (curPosition2 == 0) return;
        if (mCaseCardVo != null && prePosition1 != -1) {
            UserInfomDbHelp help = UserInfomDbHelp.get_Instance(mContext);
            UserInfomSqliteVo vo = help.findUserInfomVo();
            DoLogProgreeSqliteVo sqliteVo = new DoLogProgreeSqliteVo();
            sqliteVo.setChapterid(mChapter_Id);
            sqliteVo.setKid(Integer.parseInt(mCouers_id));
            sqliteVo.setTextid(mCaseCardVo.getVo().getQuestion_id());
            sqliteVo.setNumber(String.valueOf(prePosition1 + 1));
            sqliteVo.setUserid(String.valueOf(vo.getSaffid()));
            sqliteVo.setAllNumber(String.valueOf(mMainDataLists.size()));
            sqliteVo.setType(DataMessageVo.ORDER_THREE);
            mDoLogProgressSqliteHelp.addDoLookItem(sqliteVo);
        }
    }*/
{}