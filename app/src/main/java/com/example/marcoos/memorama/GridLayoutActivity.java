package com.example.marcoos.memorama;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.Arrays;


public class GridLayoutActivity extends Activity {

	protected static final String EXTRA_RES_ID = "POS";
	
	private ArrayList<Integer> Flores = new ArrayList<Integer>(
			Arrays.asList(R.drawable.image1, R.drawable.image3,
					R.drawable.image2, R.drawable.image2, R.drawable.image3,
					R.drawable.image5, R.drawable.image1, R.drawable.image4
					, R.drawable.image4, R.drawable.image6, R.drawable.image5
					, R.drawable.image6));
	private ArrayList<Integer> imgOculta = new ArrayList<Integer>(
			Arrays.asList(R.drawable.icon,R.drawable.icon,R.drawable.icon
					,R.drawable.icon,R.drawable.icon,R.drawable.icon
					,R.drawable.icon,R.drawable.icon,R.drawable.icon
					,R.drawable.icon,R.drawable.icon,R.drawable.icon));

	private int cont=0;
	private int anterior=0;
	private int fotoant=0;
	private int anterior2=0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final GridView gridview = (GridView) findViewById(R.id.gridview);

		gridview.setAdapter(new ImageAdapter(this, imgOculta));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
					if(cont==0){
						anterior=position;
						fotoant=Flores.get(position);
						imgOculta.set(position,Flores.get(position));
						gridview.deferNotifyDataSetChanged();
						gridview.setAdapter(new ImageAdapter(getApplicationContext(), imgOculta));
						cont++;
					}
					else if(cont==1){
						anterior2=position;
						if(fotoant == Flores.get(position)){
							imgOculta.set(position,Flores.get(position));
							gridview.deferNotifyDataSetChanged();
							gridview.setAdapter(new ImageAdapter(getApplicationContext(), imgOculta));
							cont++;
						}
						else{
							imgOculta.set(anterior2,Flores.get(anterior2));
							gridview.deferNotifyDataSetChanged();
							gridview.setAdapter(new ImageAdapter(getApplicationContext(), imgOculta));
							Handler sd = new Handler();
							sd.postDelayed(new Runnable() {
								@Override
								public void run() {
									imgOculta.set(anterior,R.drawable.icon);
									imgOculta.set(anterior2,R.drawable.icon);
									gridview.deferNotifyDataSetChanged();
									gridview.setAdapter(new ImageAdapter(getApplicationContext(), imgOculta));
								}
								},500);

							cont++;
						}
					}
					if(cont==2){
						cont=0;
					}
			}
		});

	}
	public void boton(View view){
		imgOculta = new ArrayList<Integer>(
				Arrays.asList(R.drawable.icon,R.drawable.icon,R.drawable.icon
						,R.drawable.icon,R.drawable.icon,R.drawable.icon
						,R.drawable.icon,R.drawable.icon,R.drawable.icon
						,R.drawable.icon,R.drawable.icon,R.drawable.icon));
		final GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this, imgOculta));
	}
}