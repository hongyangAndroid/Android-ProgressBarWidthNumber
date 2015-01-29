# Android-ProgressBarWidthNumber
通过继承ProgressBar实现的两种风格的滚动条，代码简洁易懂。

# 效果图

## HorizontalProgressBarWithNumber
横向滚动条模仿了daimajia的滚动条风格。
![Sample Screenshots][1]
## RoundProgressBarWidthNumber
![Sample Screenshots][2]

# 用法

### 布局文件

	<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:zhy="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:padding="25dp" >
      
        <com.zhy.view.HorizontalProgressBarWithNumber
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="50dip"
            android:padding="5dp"
            android:progress="10"
            zhy:progress_text_color="#ff2903FC"
            zhy:progress_unreached_color="#ffBCB4E8" />


        <com.zhy.view.HorizontalProgressBarWithNumber
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="50dip"
            android:background="#44ff0000"
            android:padding="5dp"
            android:progress="70"
            zhy:progress_reached_bar_height="20dp"
            zhy:progress_text_color="#ffF53B03"
            zhy:progress_unreached_color="#00F7C6B7" />
	</LinearLayout>

	</ScrollView>

### Activity中
	package com.zhy.sample.progressbar;

	import com.zhy.view.HorizontalProgressBarWithNumber;
	import com.zhy.view.RoundProgressBarWidthNumber;

	import android.os.Bundle;
	import android.os.Handler;
	import android.app.Activity;

	public class MainActivity extends Activity {

		private HorizontalProgressBarWithNumber mProgressBar;
		private static final int MSG_PROGRESS_UPDATE = 0x110;

		private Handler mHandler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				int progress = mProgressBar.getProgress();
				mProgressBar.setProgress(++progress);
				if (progress >= 100) {
					mHandler.removeMessages(MSG_PROGRESS_UPDATE);
				}
				mHandler.sendEmptyMessageDelayed(MSG_PROGRESS_UPDATE, 100);
			};
		};

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			mProgressBar = (HorizontalProgressBarWithNumber) findViewById(R.id.id_progressbar01);
			mHandler.sendEmptyMessage(MSG_PROGRESS_UPDATE);

		}

	}



#关于我

[我的博客地址][3]

[1]: https://github.com/hongyangAndroid/Android-ProgressBarWidthNumber/blob/master/sample_zhy_progressbar/Screenshot_.gif
[2]: https://github.com/hongyangAndroid/Android-ProgressBarWidthNumber/blob/master/sample_zhy_progressbar/Screenshot2.png
[3]: http://blog.csdn.net/lmj623565791
