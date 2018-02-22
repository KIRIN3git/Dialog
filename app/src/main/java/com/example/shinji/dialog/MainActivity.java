package com.example.shinji.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	ArrayList<SpotList> mSpotLists;
	ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// シンプル表示
		Button btn1 = (Button)findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder alertDlg = new AlertDialog.Builder(MainActivity.this);
				alertDlg.setTitle("ダイアログタイトル");
				alertDlg.setIcon(R.mipmap.ic_launcher_round); // アイコン
				alertDlg.setMessage("メッセージ");
				alertDlg.setPositiveButton(
						"OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								// OK ボタンクリック処理
							}
						});
				alertDlg.setNegativeButton(
						"Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								// Cancel ボタンクリック処理
							}
						});
				// 表示
				alertDlg.create().show();
			}
		});

		// テキスト入力
		Button btn2 = (Button)findViewById(R.id.button2);
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//テキスト入力を受け付けるビューを作成します。
				final EditText editView = new EditText(MainActivity.this);
				new AlertDialog.Builder(MainActivity.this)
						.setIcon(android.R.drawable.ic_dialog_info)
						.setTitle("テキスト入力ダイアログ")
						//setViewにてビューを設定します。
						.setView(editView)
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								//入力した文字をトースト出力する
								Toast.makeText(MainActivity.this,
										editView.getText().toString(),
										Toast.LENGTH_LONG).show();
							}
						})
						.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
							}
						})
						.show();
			}
		});


		// HTML形式入力
		Button btn3 = (Button)findViewById(R.id.button3);
		btn3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(MainActivity.this);
				TextView tv = new TextView(MainActivity.this);
				tv.setMovementMethod(LinkMovementMethod.getInstance()); //☆
				tv.setText(Html.fromHtml("aaaaaaaaaa<br /><a href=\"http://anitama.com/marunage/\">test</a><br />bbbbbbbbbbbbb"));
				dialog.setTitle("title");
				dialog.setView(tv);
				dialog.setCancelable(false);
				dialog.setPositiveButton(getString(R.string.app_name),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
							}
						});
				dialog.setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
							}
						});
				dialog.create();
				dialog.show();

			}
		});

		// XMLを利用したカスタムダイアログ
		Button btn4 = (Button)findViewById(R.id.button4);
		btn4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LayoutInflater inflater = (LayoutInflater)MainActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
				View layout = inflater.inflate(R.layout.dialog_custom,(ViewGroup)findViewById(R.id.layout_root));

				android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(MainActivity.this);
				TextView tv = new TextView(MainActivity.this);
				dialog.setView(tv);
				dialog.setTitle("title");
				dialog.setView(layout);

				((TextView) layout.findViewById(R.id.tv1)).setText("http://google.co.jp/");

				dialog.setCancelable(false);
				dialog.setPositiveButton(getString(R.string.app_name),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
							}
						});
				dialog.setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
							}
						});
				dialog.create();
				dialog.show();

			}
		});

		mSpotLists = new ArrayList<>();
		int[] icons = {
				R.mipmap.ic_launcher_round,
				R.mipmap.ic_launcher_round,
				R.mipmap.ic_launcher_round,
				R.mipmap.ic_launcher_round,
				R.mipmap.ic_launcher_round,
		};
		String[] ssid = {
				"aiueo12",
				"kakikukeko",
				"sashisuseso",
				"tachitsuteto",
				"naninuneno",
		};
		Integer[] priority = {
				1,
				2,
				3,
				4,
				5,
		};
		Boolean[] enable = {
				true,
				false,
				true,
				true,
				false
		};

		mListView = new ListView(this);

		for (int i = 0; i < icons.length; i++) {
			SpotList spotList = new SpotList(BitmapFactory.decodeResource(getResources(),icons[i]),ssid[i],priority[i],enable[i]);
			mSpotLists.add(spotList);
		}
		SpotListAdapter adapter = new SpotListAdapter(this, 0, mSpotLists);
		mListView.setAdapter(adapter);

		// リストビューのクリックイベントを取得
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Log.d("DEBUG_DATA", "onItemClick");

				String msg = position + "番目のアイテムがクリックされました";
				Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
			}
		});

		// リストビュー表示
		Button btn5 = (Button)findViewById(R.id.button5);
		btn5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder alertDlg = new AlertDialog.Builder(MainActivity.this);
				alertDlg.setTitle("リストビュー表示");

				// 2回目起動時に、親のビューが設定されてしまっているので削除する。
				ViewGroup parent = (ViewGroup)mListView.getParent();
				if ( parent != null ) {
					parent.removeView(mListView);
				}
				// ビューを設定
				alertDlg.setView(mListView);

				alertDlg.setPositiveButton(
						"OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								// OK ボタンクリック処理
							}
						});
				// 表示
				alertDlg.create().show();

			}
		});
	}

	public class SpotListAdapter extends ArrayAdapter<SpotList> {
		protected LayoutInflater inflater;
		private List<SpotList> spotLists; // ☆追加
		private int rowLayoutResourceId; // ☆追加

		private LayoutInflater layoutInflater;
		public SpotListAdapter(Context c, int id, ArrayList<SpotList> spotLists) {
			super(c, id, spotLists);
			this.spotLists = spotLists; // ☆追加
			this.layoutInflater = (LayoutInflater) c.getSystemService(
					Context.LAYOUT_INFLATER_SERVICE
			);
			this.rowLayoutResourceId = id; // ☆追加
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = layoutInflater.inflate(
						R.layout.wifi_list_item,
						parent,
						false
				);
			}

			final int p = position;

			// ☆追加
			View view = convertView;
			if (view == null) {
				view = inflater.inflate(R.layout.wifi_list_item, null);
			} else {
			}

			SpotList spotList = (SpotList) getItem(position);

			// ☆追加　view.
			((ImageView) view.findViewById(R.id.icon))
					.setImageBitmap(spotList.getIcon());
			// ☆追加　view.
			((TextView) view.findViewById(R.id.ssid))
					.setText(spotList.getSsid());
			// ☆追加　view.
			TextView priority = (TextView) view.findViewById(R.id.priority);

			priority.setText(String.valueOf(spotList.getPriority()));

			final String pri = String.valueOf(spotList.getPriority());

			priority.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Log.d("DEBUG_DATA", "p=" + String.valueOf(p) + ", priority=" + pri);
				}
			});

			Log.w( "DEBUG_DATA", "position = " + position);
			Log.w( "DEBUG_DATA", "spotList.getSsid() = " + spotList.getSsid());
			Log.w( "DEBUG_DATA", "spotList.getEnable() = " + spotList.getEnable());
			CheckBox enable = (CheckBox) view.findViewById(R.id.enable); // ☆追加　view.

			// 必ずsetChecked前にリスナを登録(convertView != null の場合は既に別行用のリスナが登録されている！)
			enable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
					Log.d("DEBUG_DATA", "p=" + String.valueOf(p) + ", isChecked=" + String.valueOf(isChecked));
				}
			});

			if(enable != null ){
				enable.setChecked(spotList.getEnable());
			}

			// return convertView;
			return view; // ☆変更
		}
	}
}
