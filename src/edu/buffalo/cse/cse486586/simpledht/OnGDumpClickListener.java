package edu.buffalo.cse.cse486586.simpledht;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class OnGDumpClickListener implements OnClickListener{

	private final TextView mTextView;
	private final ContentResolver mContentResolver;
	private final Uri mUri;
	public OnGDumpClickListener(TextView _tv, ContentResolver _cr){
		mTextView = _tv;
		mContentResolver = _cr;
		mUri = buildUri("content", "edu.buffalo.cse.cse486586.simpledht.provider");
	}
	private Uri buildUri(String scheme, String authority) {
		Uri.Builder uriBuilder = new Uri.Builder();
		uriBuilder.authority(authority);
		uriBuilder.scheme(scheme);
		return uriBuilder.build();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Cursor result = mContentResolver.query(mUri, null, "global", null, null);
		if(result==null){
			mTextView.append("There's Nothing in this AVD\n");
		}
		mTextView.append("Global Dump\n");
		while(result.moveToNext()){
			int key = result.getColumnIndex("key");
			int val = result.getColumnIndex("value");
			mTextView.append(result.getString(key)+"  "+result.getString(val)+"\n");
		}
		result.close();
	}
}