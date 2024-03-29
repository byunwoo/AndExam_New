package exam.andexam_new;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class C21_DownloadManager extends Activity {
	DownloadManager mDm;
	long mId = 0;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c21_downloadmanager);
		
		mDm = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
	}

	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.btnqueue:
			Uri uri = Uri.parse("http://www.winapi.co.kr/data/child2.jpg");
			DownloadManager.Request req = new DownloadManager.Request(uri);
			req.setTitle("테스트 다운로드");
			req.setDescription("이미지 파일을 다운로드 받습니다.");
			req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
					DownloadManager.Request.NETWORK_MOBILE);
			mId = mDm.enqueue(req);

			IntentFilter filter = new IntentFilter();
			filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
			registerReceiver(mDownComplete, filter);
			break;
		}
	}

	public void onPause() {
		super.onPause();
		if (mId != 0) {
			unregisterReceiver(mDownComplete);
		}
	}

	BroadcastReceiver mDownComplete = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(context, "다운로드 완료", 
					Toast.LENGTH_LONG).show();
		}
	};
}
