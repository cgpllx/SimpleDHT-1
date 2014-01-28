package edu.buffalo.cse.cse486586.simpledht;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

public class SimpleDhtProvider extends ContentProvider {
	public static final String path = "/data/data/edu.buffalo.cse.cse486586.simpledht/files";
	//private fileUtil helper = new fileUtil();
	final String KEY_FIELD = "key";
    final String VALUE_FIELD = "value";
	String[] ColumnNames = new String[]{"key","value"};
	HashMap<String,String> local0 = new HashMap<String,String>();
	String[] r = new String[2];
    String[] avd0 = new String[2];
    HashMap<String,String> local2 = new HashMap<String,String>();
	String[] avd1 = new String[2];
	String[] avd2 = new String[2];
	HashMap<String,String> local1 = new HashMap<String,String>();
	int count = 0;
	int a =1;
	
	HashMap<String,String> global = new HashMap<String,String>();
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues cv) {
		// TODO Auto-generated method stub
		TelephonyManager tel = (TelephonyManager)getContext().getSystemService(Context.TELEPHONY_SERVICE);
		final String portStr = tel.getLine1Number().substring(tel.getLine1Number().length() - 4);
		ContentValues sender = new ContentValues();
		sender = cv;
		String name = sender.getAsString(KEY_FIELD);
    	String value = sender.getAsString(VALUE_FIELD);
    	global.put(name, value);
//    	if(portStr.equals("5554"))
//    		global1.put(name, value);
//    	else if(portStr.equals("5556"))
//    		global2.put(name, value);
//    	else
//    		global3.put(name, value);
		try {
			Socket send = new Socket("10.0.2.2",11108);
			PrintWriter pw = new PrintWriter(send.getOutputStream());
			pw.print(name+":"+value+"\n");
			pw.flush();
			send.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uri;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		TelephonyManager tel = (TelephonyManager)getContext().getSystemService(Context.TELEPHONY_SERVICE);
		final String portStr = tel.getLine1Number().substring(tel.getLine1Number().length() - 4);
		if(portStr.equals("5554")){
			//Log.e("Launched", "It is Starting");
			new initializer().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, portStr);
		}
		else{
			new newNodeUpdate().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, portStr);
			new newNodeJoin().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, portStr);
		}
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		// TODO Auto-generated method stub
		// = new String[]{selection,global.get(selection)};
		TelephonyManager tel = (TelephonyManager)getContext().getSystemService(Context.TELEPHONY_SERVICE);
		final String portStr = tel.getLine1Number().substring(tel.getLine1Number().length() - 4);
//		HashMap<String,String> temp = new HashMap<String,String>();
		if(selection.equals("local")){
			fileReader fr = new fileReader();
			MatrixCursor mc = fr.read();
			return mc;
		}
		else if(selection.equals("global")){
//			if(count==0){
//				new querySender().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 11108);
//				while(a==1){}
//				a=1;
//				return mc0;
//			}
//			else if(count==1){
//				if(avd0[0].equals("5556")){
//					new querySender().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 11108);
//					while(a==1){}
//					a=1;
//					new querySender().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 11112);
//					while(a==1){}
//					a=1;
//					MatrixCursor mc = new MatrixCursor(ColumnNames);
//					mc0.moveToFirst();
//					while(mc0.moveToNext()){
//						int key = mc0.getColumnIndex("key");
//						int val = mc0.getColumnIndex("value");
//						String[] set = new String[2];
//						set[0] = mc0.getString(key);
//						set[1] = mc0.getString(val);
//						mc.addRow(set);
//					}
//					mc1.moveToFirst();
//					while(mc1.moveToNext()){
//						int key = mc1.getColumnIndex("key");
//						int val = mc1.getColumnIndex("value");
//						String[] set = new String[2];
//						set[0] = mc1.getString(key);
//						set[1] = mc1.getString(val);
//						mc.addRow(set);
//					}
//					mc.close();
//					return mc;
//				}
//				else{
//					new querySender().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 11108); 
//					while(a==1){}
//					a=1;
//					new querySender().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 11116);
//					while(a==1){}
//					a=1;
//					MatrixCursor mc = new MatrixCursor(ColumnNames);
//					mc0.moveToFirst();
//					while(mc0.moveToNext()){
//						int key = mc0.getColumnIndex("key");
//						int val = mc0.getColumnIndex("value");
//						String[] set = new String[2];
//						set[0] = mc0.getString(key);
//						set[1] = mc0.getString(val);
//						mc.addRow(set);
//					}
//					mc2.moveToFirst();
//					while(mc2.moveToNext()){
//						int key = mc2.getColumnIndex("key");
//						int val = mc2.getColumnIndex("value");
//						String[] set = new String[2];
//						set[0] = mc2.getString(key);
//						set[1] = mc2.getString(val);
//						mc.addRow(set);
//					}
//					mc.close();
//					return mc;
//				}
//			}
//			else{
//				new querySender().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 11108);
//				while(a==1){}
//				a=1;
//				new querySender().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 11112);
//				while(a==1){}
//				a=1;
//				new querySender().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 11116);	
//				while(a==1){}
//				a=1;
//				MatrixCursor mc = new MatrixCursor(ColumnNames);
//				mc0.moveToFirst();
//				while(mc0.moveToNext()){
//					int key = mc0.getColumnIndex("key");
//					int val = mc0.getColumnIndex("value");
//					String[] set = new String[2];
//					set[0] = mc0.getString(key);
//					set[1] = mc0.getString(val);
//					mc.addRow(set);
//				}
//				mc1.moveToFirst();
//				while(mc1.moveToNext()){
//					int key = mc1.getColumnIndex("key");
//					int val = mc1.getColumnIndex("value");
//					String[] set = new String[2];
//					set[0] = mc1.getString(key);
//					set[1] = mc1.getString(val);
//					mc.addRow(set);
//				}
//				mc2.moveToFirst();
//				while(mc2.moveToNext()){
//					int key = mc2.getColumnIndex("key");
//					int val = mc2.getColumnIndex("value");
//					String[] set = new String[2];
//					set[0] = mc2.getString(key);
//					set[1] = mc2.getString(val);
//					mc.addRow(set);
//				}
//				mc.close();
//				return mc;
//			}
			HashMap<String,String> hm = new HashMap<String,String>();
			if(global.isEmpty() && local0.isEmpty() && local1.isEmpty())
				hm = local2;
			else if(global.isEmpty() && local0.isEmpty() && local2.isEmpty())
				hm = local1;
			else if(global.isEmpty() && local1.isEmpty() && local2.isEmpty())
				hm = local0;
			else
				hm = global;
			MatrixCursor mat = new MatrixCursor(ColumnNames);
			String[] set = new String[2];
			for(String key:hm.keySet()){
				set[0] = key;
				set[1] = hm.get(key);
				mat.addRow(set);
			}
			return mat;
		}
		else{
			r[0] = selection;
			r[1] = global.get(selection);
			MatrixCursor m = new MatrixCursor(ColumnNames);
			m.addRow(r);
			return m;
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	private String genHash(String input) throws NoSuchAlgorithmException {
		MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
		byte[] sha1Hash = sha1.digest(input.getBytes());
		Formatter formatter = new Formatter();
		for (byte b : sha1Hash) {
			formatter.format("%02x", b);
		}
		return formatter.toString();
	}
	private class initializer extends AsyncTask<String,Void,Void>{
		String predecessor,successor;
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			Log.e("checker", "In 5554");
			int port = 0;
			try {
				ServerSocket s = new ServerSocket(10000);
				//System.out.println("Server Socket Created");
				while(true){
					Socket incoming = s.accept();
					BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
					String inputLine = in.readLine();
					String substr = inputLine.substring(3);
					if(inputLine.startsWith("req") && substr.equals("5556")){
						Log.e("Connection check","Connection with 5556 established");
						if(count==0){
							avd0[0] = avd0[1] = predecessor = successor = "5556";
							avd1[0] = avd1[1] = "5554";
							count+=1;
							new valueSender().executeOnExecutor(THREAD_POOL_EXECUTOR, 11112, avd1[0], avd1[1]);
						}
						else{
							avd0[0] = predecessor = "5556";
							avd1[0] = "5558";
							avd1[1] = "5554";
							avd2[1] = "5556";
							count+=1;
							new valueSender().executeOnExecutor(THREAD_POOL_EXECUTOR, 11112, avd1[0], avd1[1]);
							new valueSender().executeOnExecutor(THREAD_POOL_EXECUTOR, 11116, avd2[0], avd2[1]);
						}	
					}
					else if(inputLine.startsWith("req") && substr.equals("5558")){
						Log.e("Connection check","Connection with 5558 established");
						if(count==0){
							avd0[0] = avd0[1] = predecessor = successor = "5558";
							avd2[0] = avd2[1] = "5554";					
							count+=1;
							new valueSender().executeOnExecutor(THREAD_POOL_EXECUTOR, 11116, avd2[0], avd2[1]);
						}
						else{
							avd0[1] = successor = "5558";
							avd1[0] = "5558";
							avd2[0] = "5554";
							avd2[1] = "5556";			
							count+=1;
							new valueSender().executeOnExecutor(THREAD_POOL_EXECUTOR, 11116, avd2[0], avd2[1]);
							new valueSender().executeOnExecutor(THREAD_POOL_EXECUTOR, 11112, avd1[0], avd1[1]);
						}
					}
//					else if(inputLine.contains("query")){
//						if(count==0){
//							fileReader fr = new fileReader();
//							mc0 = fr.read();
//							a=0;
//							//notify();
//							//return mc;
//						}
//					}
					else{
						String[] keyval = new String[2];
						keyval = inputLine.split(":");
						String hash = genHash(keyval[0]);
						if(count==0){
							fileWriter file = new fileWriter(path,keyval);
							file.write();
							local0.put(keyval[0], keyval[1]);
						}
						else if(count==1 && avd0[0].equals("5558")){
							if(hash.compareTo(genHash("5554"))<=0 || hash.compareTo(genHash(predecessor))>0){
								fileWriter file = new fileWriter(path,keyval);
								file.write();
								local0.put(keyval[0], keyval[1]);
							}
							else{
								Socket socket = new Socket("10.0.2.2",11116);
					    		PrintWriter pw = new PrintWriter(socket.getOutputStream());
					    		pw.print(keyval[0]+":"+keyval[1]+"\n");
					    		pw.flush();
					    		socket.close();
							}
						}
						else{
							if(hash.compareTo(genHash("5554"))<=0 && hash.compareTo(genHash(predecessor))>0){
								fileWriter file = new fileWriter(path,keyval);
								file.write();
								local0.put(keyval[0], keyval[1]);
							}
							else{
								if(successor.equals("5556"))
									port = 11112;
								else
									port = 11116;
								Socket socket = new Socket("10.0.2.2",port);
					    		PrintWriter pw = new PrintWriter(socket.getOutputStream());
					    		pw.print(keyval[0]+":"+keyval[1]+"\n");
					    		pw.flush();
					    		socket.close();
							}
						}
					}
				}	

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
	private class valueSender extends AsyncTask<Object,Void,Void>{

		@Override
		protected Void doInBackground(Object... arg0) {
			// TODO Auto-generated method stub
				Socket sender;
				int port = (Integer)arg0[0];
				try {
					sender = new Socket("10.0.2.2",port);
					PrintWriter pw = new PrintWriter((sender.getOutputStream()));
					pw.print("val"+(String)arg0[1]+(String)arg0[2]+"\n");
					pw.flush();
					sender.close();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return null;
		}
		
	}
	private class newNodeJoin extends AsyncTask<String,Void,Void>{
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			if(params[0].equals("5556")){
				Log.e("checker","In 5556");
				try {
					Socket sender = new Socket("10.0.2.2",11108);
					PrintWriter pw = new PrintWriter(sender.getOutputStream());
					pw.print("req5556\n");
					pw.flush();
					sender.close();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(params[0].equals("5558")){
				Log.e("checker","In 5558");
				try {
					Socket sender = new Socket("10.0.2.2",11108);
					PrintWriter pw = new PrintWriter(sender.getOutputStream());
					pw.println("req5558\n");
					pw.flush();
					sender.close();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			return null;
		}

	}
	
	private class newNodeUpdate extends AsyncTask<String,Void,Void>{

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			if(params[0].equals("5556")){
				String predecessor = null,successor = null;
				try {
					ServerSocket server = new ServerSocket(10000);
					while(true){
						Socket client = server.accept();
						BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
						String inputLine = br.readLine();
						if(inputLine.startsWith("val")){
							predecessor = inputLine.substring(3, 7);
							successor = inputLine.substring(7, 11);
							Log.e("checker5556", predecessor);
							Log.e("checker5556",successor);
						}
//						else if(inputLine.contains("query")){
//							fileReader fr = new fileReader();
//							//mc1 = fr.read();
//							a=0;
//							//notify();
//						}
						else{
							//Log.e("checker","Unimplemented");
							String[] keyval = new String[2];
							keyval = inputLine.split(":");
							String hash = genHash(keyval[0]);
							if(hash.compareTo(genHash("5556"))<=0 || hash.compareTo(genHash(predecessor))>0){
								fileWriter file = new fileWriter(path,keyval);
								file.write();
								local1.put(keyval[0], keyval[1]);
							}
							else{
								Socket s = new Socket("10.0.2.2",11108);
					    		PrintWriter pw = new PrintWriter(s.getOutputStream());
					    		pw.print(keyval[0]+":"+keyval[1]+"\n");
					    		pw.flush();
					    		s.close();
							}
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(params[0].equals("5558")){
				String predecessor = null,successor = null;
				try {
					ServerSocket server = new ServerSocket(10000);
					while(true){
						Socket client = server.accept();
						BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
						String inputLine = br.readLine();
						if(inputLine.startsWith("val")){
							predecessor = inputLine.substring(3, 7);
							successor = inputLine.substring(7, 11);
							Log.e("checker5558", predecessor);
							Log.e("checker5558",successor);
						}
//						else if(inputLine.contains("query")){
//							fileReader fr = new fileReader();
//							//mc2 = fr.read();
//							a=0;
//							//notify();
//						}
						else{
							//Log.e("checker","Unimplemented");
							String[] keyval = new String[2];
							keyval = inputLine.split(":");
							String hash = genHash(keyval[0]);
							if(hash.compareTo(genHash("5558"))<=0 && hash.compareTo(genHash(predecessor))>0){
								fileWriter file = new fileWriter(path,keyval);
								file.write();
								local2.put(keyval[0], keyval[1]);
							}
							else{
								Socket s = new Socket("10.0.2.2",11112);
								PrintWriter pw = new PrintWriter(s.getOutputStream());
								pw.print(keyval[0]+":"+keyval[1]+"\n");
								pw.flush();
								s.close();
							}
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		
	}
	private class fileWriter{
		final String path;
		String key,val;
		fileWriter(String path,String[] keyval){
			this.path = path;
			key = keyval[0];
			val = keyval[1];
		}
		void write(){
			File file = new File(path,key);
			FileWriter fw = null;
			File f = new File(path);
			if(!f.exists())
				f.mkdirs();
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try{
				fw = new FileWriter(file.getAbsoluteFile());
			}
			catch(Exception e){
				Log.e("inserterror", "can't open the file", e);
			}
			BufferedWriter out = new BufferedWriter(fw);
			try{
				out.write(val);
				out.close();
			}
			catch(Exception e){
				Log.e("inserterror", "can't write to the file", e);
			}

		}
	}
	private class fileReader{
		MatrixCursor mc = new MatrixCursor(ColumnNames);
		String[] res = new String[2];
		MatrixCursor read(){
			File f = new File(path);
			for(File file : f.listFiles()){
				FileReader fr = null;
				try {
					fr = new FileReader(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BufferedReader in = new BufferedReader(fr);
				int c;
				char[] buf = new char[128];
				StringBuffer fileData = new StringBuffer();
				try {
					while((c=in.read(buf)) != -1){
						String readData = String.valueOf(buf, 0, c);
						fileData.append(readData);
					}
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				res[0] = file.getName();
				res[1] = fileData.toString();
				mc.addRow(res);
			}
			return mc;
		}
	}
	private class querySender extends AsyncTask<Integer,Void,Void>{

		@Override
		protected Void doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			int port = params[0];
			try {
				Socket client = new Socket("10.0.2.2",port);
				PrintWriter pw = new PrintWriter(client.getOutputStream());
				pw.print("query\n");
				pw.flush();
				client.close();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
	}
}
