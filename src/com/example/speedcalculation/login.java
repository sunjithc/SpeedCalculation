package com.example.speedcalculation;

import java.util.List;

import com.google.android.maps.MapActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class login extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);	
		
		Intilize();
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	Button BtnSubmit,BtnReg;
	Context Main;
	TextView TxtUsername,TxtPass;
	String UserName, Pass;
	
	

	void Intilize()
	{
		Main = this;
		
		TxtUsername = (TextView) this.findViewById (R.id.editText2);
		TxtPass = (TextView) this.findViewById (R.id.editText1);
		
		BtnSubmit = (Button)this.findViewById (R.id.btnLogin);
		BtnSubmit_Listenerr();
	}
	
	
	void BtnSubmit_Listenerr()
	{		
		BtnSubmit.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				
				// TODO Auto-generated method stub
				if(Login())
				{
					Intent intent = new Intent(getApplicationContext() , MainActivity.class);
					startActivity(intent);
				}
			}
		});		
	}

	
	boolean Login()
	{
		UserName = TxtUsername.getText().toString();
		Pass = TxtPass.getText().toString();
		
		
		if(UserName.equals("") || Pass.equals(""))
		{
			Toast.makeText (Main, "Fields are Empty", Toast.LENGTH_SHORT).show();
			return false;
		}
		else
		{
	        	if(TxtUsername.getText().toString().equals("pass") && TxtPass.getText().toString().equals("user"))
	        	{
	        		return true;
	        	}

	        Toast.makeText (Main, "Invalid UserName and Password", Toast.LENGTH_SHORT).show();
	        	
		}
		return false;
	}
}