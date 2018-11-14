package com.zhaoman.manny_ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Author:zhaoman
 * Date:2018/11/12
 * Description:
 */
public class DatabaseManager  {
    private DaoSession mDaoSession=null;
    private UserProfileDao mDao=null;



    private void initDao(Context context){

        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();

    }
    private DatabaseManager() {
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    public final UserProfileDao getDao() {
        return mDao;
    }
}
